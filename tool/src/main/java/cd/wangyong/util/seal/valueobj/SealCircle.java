package cd.wangyong.util.seal.valueobj;

/**
 * 圆形（含椭圆）
 * @author andy
 * @since 2020/11/13
 */
public class SealCircle {

    /**
     * 线宽度
     */
    private final Integer lineSize;
    /**
     * 半径
     */
    private final Integer width;
    /**
     * 半径
     */
    private final Integer height;

    public SealCircle(Integer lineSize, Integer width, Integer height) {
        this.lineSize = lineSize;
        this.width = width;
        this.height = height;
    }

    public Integer getLineSize() {
        return lineSize;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }
}
