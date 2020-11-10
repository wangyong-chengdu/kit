package cd.wangyong.tool.circuitbreaker;

/**
 * 熔断器状态
 * @author andy
 * @since 2020/11/9
 */
public enum CBState {
    /**
     * 关闭
     */
    CLOSED,
    /**
     * 打开
     */
    OPEN,
    /**
     * 半开
     */
    HALF_OPEN;
}
