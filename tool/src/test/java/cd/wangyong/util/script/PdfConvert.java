package cd.wangyong.util.script;

import cd.wangyong.util.converter.pdf.PdfUtil;

/**
 * @author andy
 * @since 2020/10/14
 */
public class PdfConvert {

    public static void main(String[] args) {
//        PdfUtil.convertToWord("/home/andy/文档/消息队列高手课", "消息队列期中测试题目答案及解析-极客时间.pdf", "/home/andy/文档/消息队列高手课");
        PdfUtil.convertToWordOfText("/home/andy/文档/消息队列高手课", "消息队列期中测试题目答案及解析-极客时间.pdf");
    }
}
