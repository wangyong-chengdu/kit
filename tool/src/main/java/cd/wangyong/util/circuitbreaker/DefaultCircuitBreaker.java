package cd.wangyong.util.circuitbreaker;

import static cd.wangyong.util.circuitbreaker.CBException.INVOKE_CB;
import static cd.wangyong.util.circuitbreaker.CBException.REENTRY_CB;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * 熔断器门面
 * @author andy
 * @since 2020/11/9
 */
public class DefaultCircuitBreaker implements CircuitBreaker {
    private static final Logger logger = LoggerFactory.getLogger(DefaultCircuitBreaker.class);

    /**
     * 接口熔断配置
     */
    private final Map<Class<?>, InterfaceCBConfig> interfaceCBConfigMap;

    /**
     * 是否启用
     */
    private volatile boolean enabled = false;

    /**
     * 方法的熔断器状态
     */
    private Map<String, CBState> methodCBStateMap = new ConcurrentHashMap<>();

    /**
     * 监控统计信息，接口的失败率统计
     */
    private Map<String, CBMonitor> methodMonitorMap = new ConcurrentHashMap<>();

    public DefaultCircuitBreaker(Map<Class<?>, InterfaceCBConfig> interfaceCBConfigMap) {
        this.interfaceCBConfigMap = interfaceCBConfigMap;
    }

    @Override
    public void enable() {
        this.enabled = true;
        logger.debug("Enable circuit breaker.");
    }

    @Override
    public CBState getState(Class<?> clazz, String methodName) {
        return methodCBStateMap.get(methodName);
    }

    @Override
    public <T> T apply(Class<?> clazz, String methodName, Callable<T> rpcCaller, Callable<T> replaceCaller) throws Exception {
        // 熔断器未启动，直接调用
        if (!enabled) {
            return rpcCaller.call();
        }

        // 无配置说明没有降级方案，直接调用
        InterfaceCBConfig interfaceCBConfig = interfaceCBConfigMap.get(clazz);
        if (interfaceCBConfig == null) {
            logger.debug("Interface circuit breaker not exist. interface:" + clazz.getSimpleName());
            return rpcCaller.call();
        }

        Assert.hasLength(methodName, "MethodName is empty.");
        CBConfig cbConfig = interfaceCBConfig.getMethodConfig().get(methodName);
        if (cbConfig == null) {
            logger.debug("Interface circuit breaker not exist. interface:" + clazz.getSimpleName());
            return rpcCaller.call();
        }

        CBState cbState = methodCBStateMap.get(methodName);
        CBMonitor cbMonitor = methodMonitorMap.get(methodName);

        T result = null;

        // 正常情况熔断器关闭，通过rpc调用获取执行结果、然后判断是否触发熔断
        if (cbState == CBState.CLOSED) {
            try {
                result = invokeRpcMethod(rpcCaller, cbMonitor, cbConfig);
                if (result != null) return result;

                if (cbMonitor.computeFailures() >= cbConfig.getFailureThreshold()
                        || cbMonitor.computeAvailability() >= cbConfig.getAvailabilityThreshold()) {
                    methodCBStateMap.put(methodName, CBState.OPEN);
                    cbMonitor.recordCBOpenTime(System.currentTimeMillis());
                }
            } catch (Throwable t) {
                throw new CBException(INVOKE_CB, "Rpc call fail invoke circuit breaker open.", t);
            }
        }

        // 熔断器打开状态，判断是否到达恢复期
        else if (cbState == CBState.OPEN) {
            // 超出熔断周期，则进入恢复期
            if (cbMonitor.isOutCBPeriod(System.currentTimeMillis())) {
                try {
                    result = invokeRpcMethod(rpcCaller, cbMonitor, cbConfig);
                    methodCBStateMap.put(methodName, CBState.HALF_OPEN);
                    return result;
                } catch (Throwable t) {
                    // 重置熔断时间
                    cbMonitor.resetBreakTime();
                    throw new CBException(REENTRY_CB, "Rpc call fail reentry circuit breaker.", t);
                }
            }

            return replaceCaller.call();
        }

        // 熔断器半打开状态（恢复期）、判断是否连续N次调用成功
        else {
            try {
                result = invokeRpcMethod(rpcCaller, cbMonitor, cbConfig);
                if (cbMonitor.computeSuccess() >= cbConfig.getSuccessThreshold()) {
                    methodCBStateMap.put(methodName, CBState.CLOSED);
                    return result;
                }
            } catch (Throwable t) {
                if (cbMonitor.isOutRecoveryPeriod(System.currentTimeMillis())) {
                    methodCBStateMap.put(methodName, CBState.OPEN);
                    cbMonitor.resetBreakTime();
                    throw new CBException(REENTRY_CB, "Rpc call fail reentry circuit breaker.", t);
                }
            }
        }
        return result;
    }

    public <T> T invokeRpcMethod(Callable<T> rpcCaller, CBMonitor cbMonitor, CBConfig cbConfig) throws Exception {
        try {
            T result = rpcCaller.call();
            cbMonitor.recordSuccess();
            return result;
        } catch (Throwable t) {
            logger.debug("Rpc call throws exception.", t);

            // 异常是否在白名单中，如果在则直接返回
            if (cbConfig.getExceptionWhitePapers().contains(t)) {
                logger.debug("Throwable in white paper, white paper:{}", cbConfig.getExceptionWhitePapers(), t);
                return rpcCaller.call();
            }

            // 记录失败
            cbMonitor.recordFailure();
            throw t;
        }
    }
}