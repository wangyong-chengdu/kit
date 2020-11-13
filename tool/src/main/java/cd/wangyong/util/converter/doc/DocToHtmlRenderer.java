package cd.wangyong.util.converter.doc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import fr.opensagres.poi.xwpf.converter.core.ImageManager;
import fr.opensagres.poi.xwpf.converter.xhtml.IContentHandlerFactory;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLOptions;


/**
 * Doc 转 Html
 */
public class DocToHtmlRenderer {

    /**
     * 忽略无用的样式
     */
    private boolean ignoreStylesIfUnused = false;

    /**
     * 忽略页眉和页脚
     */
    private boolean omitHeaderFooterPages = false;

    /**
     * 设置分段
     */
    private boolean fragment = false;

    /**
     * 设置缩紧
     */
    private Integer indent;

    /**
     * 设置图片解析
     */
    private ImageManager imageManager;

    /**
     * 设置内容处理
     */
    private IContentHandlerFactory contentHandlerFactory;


    private DocToHtmlRenderer() {

    }

    public static DocToHtmlRenderer getRenderer() {
        return new DocToHtmlRenderer();
    }


    public DocToHtmlRenderer ignoreStylesIfUnused(boolean ignoreStylesIfUnused) {
        this.ignoreStylesIfUnused = ignoreStylesIfUnused;
        return this;
    }

    public DocToHtmlRenderer omitHeaderFooterPages(boolean omitHeaderFooterPages) {
        this.omitHeaderFooterPages = omitHeaderFooterPages;
        return this;
    }

    public DocToHtmlRenderer fragment(boolean fragment) {
        this.fragment = fragment;
        return this;
    }

    public DocToHtmlRenderer indent(Integer indent) {
        this.indent = indent;
        return this;
    }

    public DocToHtmlRenderer imageManager(ImageManager imageManager) {
        this.imageManager = imageManager;
        return this;
    }

    public DocToHtmlRenderer contentHandlerFactory(IContentHandlerFactory contentHandlerFactory) {
        this.contentHandlerFactory = contentHandlerFactory;
        return this;
    }

    /**
     * docx 转 html
     *
     * @param docxContent word byte array
     * @return 转换后的byte数组
     */
    public byte[] render(byte[] docxContent) {
        try {
            XWPFDocument document = new XWPFDocument(new ByteArrayInputStream(docxContent));
            XHTMLOptions options = XHTMLOptions.create()
                    .setImageManager(imageManager)
                    .setContentHandlerFactory(contentHandlerFactory)
                    .indent(indent)
                    .setIgnoreStylesIfUnused(ignoreStylesIfUnused)
                    .setOmitHeaderFooterPages(omitHeaderFooterPages)
                    .setFragment(fragment);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            XHTMLConverter.getInstance()
                    .convert(document, byteArrayOutputStream, options);

            byteArrayOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        }
        catch (Exception exception) {
            throw new RuntimeException("convert doc to html error", exception);
        }
    }

}
