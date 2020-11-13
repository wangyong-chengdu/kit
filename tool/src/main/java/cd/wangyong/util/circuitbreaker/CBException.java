package cd.wangyong.util.circuitbreaker;

/**
 * @author andy
 * @since 2020/11/10
 */
public class CBException extends RuntimeException {
    /**
     * 触发熔断
     */
    public static final int INVOKE_CB = 1;

    /**
     * 再次进入熔断
     */
    public static final int REENTRY_CB = 2;



    private int code;

    public CBException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
