package cd.wangyong.tool.metric;

/**
 * 监控统计
 * @author andy
 * @since 2020/11/9
 */
public interface Monitor {
    /**
     * 记录失败
     */
    void recordFailure();

    /**
     * 记录成功
     */
    void recordSuccess();

    /**
     * 计算连续失败次数
     */
    int computeFailures();

    /**
     * 计算可用性
     */
    int computeAvailability();

    /**
     * 计算连续成功次数
=     */
    int computeSuccess();
}
