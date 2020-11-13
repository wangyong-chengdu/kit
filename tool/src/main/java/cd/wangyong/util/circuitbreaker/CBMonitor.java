package cd.wangyong.util.circuitbreaker;

import cd.wangyong.util.metric.Monitor;

/**
 * @author andy
 * @since 2020/11/10
 */
public interface CBMonitor extends Monitor {
    /**
     * 记录熔断器触发打开时间
     * @param currentTimeMillis 单位毫秒
     */
    void recordCBOpenTime(long currentTimeMillis);

    /**
     * 时间是否在熔断周期之外
     * @param currentTimeMillis 单位毫秒
     */
    boolean isOutCBPeriod(long currentTimeMillis);

    /**
     * 重置熔断时间
     */
    void resetBreakTime();

    /**
     * 是否超出恢复期
     * @param currentTimeMillis 单位毫秒
     */
    boolean isOutRecoveryPeriod(long currentTimeMillis);
}
