package cd.wangyong.tool.circuitbreaker;

import java.util.concurrent.Callable;

/**
 * 熔断器
 * @author andy
 * @since 2020/11/9
 */
public interface CircuitBreaker {

    /**
     * 打开熔断器
     */
    void enable();

    /**
     * 熔断器状态
     */
    CBState getState(Class<?> clazz, String methodName);

    /**
     * 应用熔断器
     * @param methodName 方法名
     * @param rpcCaller rpc请求Caller
     * @param replaceCaller 兜底Caller
     */
    <T> T apply(Class<?> clazz, String methodName, Callable<T> rpcCaller, Callable<T> replaceCaller) throws Exception;
}
