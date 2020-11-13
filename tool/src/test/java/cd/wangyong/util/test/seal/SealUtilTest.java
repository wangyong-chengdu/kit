package cd.wangyong.util.test.seal;

import java.awt.Color;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import cd.wangyong.util.seal.SealConfig;
import cd.wangyong.util.seal.SealUtil;
import cd.wangyong.util.seal.valueobj.SealCircle;
import cd.wangyong.util.seal.valueobj.SealFont;

/**
 * @author andy
 * @since 2020/11/13
 */
@RunWith(JUnit4.class)
public class SealUtilTest {

    @Test
    public void testBuildSeal() throws Exception {
        // 主文字
        SealFont mainFont = new SealFont();
        mainFont.setBold(true);
        mainFont.setFontFamily("楷体");
        mainFont.setMarginSize(10);
        mainFont.setFontText("成都玉盘餐饮管理有限责任公司");
//        mainFont.setFontText("四川青山长源科技有限公司");
        mainFont.setFontSize(35);
        mainFont.setFontSpace(32.0);
        // 副文字
        SealFont viceFont = new SealFont();
        viceFont.setBold(true);
        viceFont.setFontFamily("宋体");
        viceFont.setMarginSize(5);
        viceFont.setFontText("5101608867801");
        viceFont.setFontSize(13);
        viceFont.setFontSpace(15.0);
        // 中心文字
        SealFont centerFont = new SealFont();
        centerFont.setBold(true);
        centerFont.setFontFamily("宋体");
        centerFont.setFontText("★");
        centerFont.setFontSize(140);

        // 外边线形状:圆形 粗细、半径
        SealCircle borderOuterCycle = new SealCircle(8, 140, 140);
//        // 内边线形状:圆形 粗细、半径
//        SealCircle borderInnerCycle = new SealCircle(3, 135, 135);
//        // 内边线形状:圆形 粗细、半径
//        SealCircle innerCycle = new SealCircle(3, 105, 105);

        SealConfig config = SealConfig.build()
                .setMainFont(mainFont)
                .setViceFont(viceFont)
                .setCenterFont(centerFont)
                .setImageSize(300)
                .setBackgroundColor(Color.RED)
                .setBorderCircle(borderOuterCycle)
//                .setBorderInnerCircle(borderInnerCycle)
//                .setInnerCircle(innerCycle)
                ;
        SealUtil.buildAndStoreSeal(config, "/home/andy/下载/a/公章22.png");
    }

}
