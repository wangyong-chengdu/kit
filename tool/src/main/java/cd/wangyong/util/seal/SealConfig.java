package cd.wangyong.util.seal;

import java.awt.Color;

import cd.wangyong.util.seal.valueobj.SealCircle;
import cd.wangyong.util.seal.valueobj.SealFont;

/**
 * 印章配置
 * @author andy
 * @since 2020/11/13
 */
public class SealConfig {

    /**
     * 主文字
     */
    private SealFont mainFont;
    /**
     * 副文字
     */
    private SealFont viceFont;
    /**
     * 抬头文字
     */
    private SealFont titleFont;
    /**
     * 中心文字
     */
    private SealFont centerFont;
    /**
     * 边线圆
     */
    private SealCircle borderCircle;
    /**
     * 内边线圆
     */
    private SealCircle borderInnerCircle;
    /**
     * 内线圆
     */
    private SealCircle innerCircle;
    /**
     * 背景色，默认红色
     */
    private Color backgroundColor = Color.RED;
    /**
     * 图片输出尺寸，默认300
     */
    private Integer imageSize = 300;

    private SealConfig() {
    }

    public static SealConfig build() {
        return new SealConfig();
    }

    public SealConfig setMainFont(SealFont mainFont) {
        this.mainFont = mainFont;
        return this;
    }

    public SealConfig setViceFont(SealFont viceFont) {
        this.viceFont = viceFont;
        return this;
    }

    public SealConfig setTitleFont(SealFont titleFont) {
        this.titleFont = titleFont;
        return this;
    }

    public SealConfig setCenterFont(SealFont centerFont) {
        this.centerFont = centerFont;
        return this;
    }

    public SealConfig setBorderCircle(SealCircle borderCircle) {
        this.borderCircle = borderCircle;
        return this;
    }

    public SealConfig setBorderInnerCircle(SealCircle borderInnerCircle) {
        this.borderInnerCircle = borderInnerCircle;
        return this;
    }

    public SealConfig setInnerCircle(SealCircle innerCircle) {
        this.innerCircle = innerCircle;
        return this;
    }

    public SealConfig setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public SealConfig setImageSize(Integer imageSize) {
        this.imageSize = imageSize;
        return this;
    }

    public SealFont getMainFont() {
        return mainFont;
    }

    public SealFont getViceFont() {
        return viceFont;
    }

    public SealFont getTitleFont() {
        return titleFont;
    }

    public SealFont getCenterFont() {
        return centerFont;
    }

    public SealCircle getBorderCircle() {
        return borderCircle;
    }

    public SealCircle getBorderInnerCircle() {
        return borderInnerCircle;
    }

    public SealCircle getInnerCircle() {
        return innerCircle;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Integer getImageSize() {
        return imageSize;
    }
}
