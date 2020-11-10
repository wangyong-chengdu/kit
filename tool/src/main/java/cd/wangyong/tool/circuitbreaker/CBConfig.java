package cd.wangyong.tool.circuitbreaker;

import java.util.Set;

/**
 * 熔断配置
 * @author andy
 * @since 2020/11/9
 */
public final class CBConfig {
    private final String name;
    private final boolean enabled;

    /**
     * 熔断周期：默认10s
     */
    private final long period;

    /**
     * 恢复周期：默认5s
     */
    private final long recoveryPeriod;

    /**
     * 连续失败次数阈值：连续失败高于指定阈值则触发熔断
     */
    private final int failureThreshold;

    /**
     * 可用率阈值，可用率低于阈值则触发熔断
     */
    private final int availabilityThreshold;

    /**
     * 恢复期连续成功调用次数
     */
    private final int successThreshold;

    /**
     * 异常白名单
     */
    private final Set<Class<? extends Throwable>> exceptionWhitePapers;

    /**
     * 异常黑名单
     */
    private final Set<Class<? extends Throwable>> exceptionBlackPapers;

    public CBConfig(String name, boolean enabled,
                    long period, long recoveryPeriod,
                    int failureThreshold, int availabilityThreshold,
                    int successThreshold,
                    Set<Class<? extends Throwable>> exceptionWhitePapers,
                    Set<Class<? extends Throwable>> exceptionBlackPapers) {
        this.name = name;
        this.enabled = enabled;
        this.period = period;
        this.recoveryPeriod = recoveryPeriod;
        this.failureThreshold = failureThreshold;
        this.availabilityThreshold = availabilityThreshold;
        this.successThreshold = successThreshold;
        this.exceptionWhitePapers = exceptionWhitePapers;
        this.exceptionBlackPapers = exceptionBlackPapers;
    }

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public long getPeriod() {
        return period;
    }

    public long getRecoveryPeriod() {
        return recoveryPeriod;
    }

    public int getFailureThreshold() {
        return failureThreshold;
    }

    public int getAvailabilityThreshold() {
        return availabilityThreshold;
    }

    public Set<Class<? extends Throwable>> getExceptionWhitePapers() {
        return exceptionWhitePapers;
    }

    public Set<Class<? extends Throwable>> getExceptionBlackPapers() {
        return exceptionBlackPapers;
    }

    public int getSuccessThreshold() {
        return successThreshold;
    }
}
