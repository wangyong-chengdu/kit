package cd.wangyong.util.test.converter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import cd.wangyong.util.converter.doc.DocToHtmlRenderer;

@RunWith(JUnit4.class)
public class DocToHtmlRendererTest {

    @Test
    public void docToHtml() throws Exception {

        String name = "document";

        Path docPath = Paths.get("/home/andy/文档/过户/刘洪鉴/稳定就业证明模板 (1).docx");
        Path targetHtmlPath = Paths.get("/home/andy/文档/过户/刘洪鉴/稳定就业证明模板.html");

        System.out.println("convert doc: [" + docPath.toString() + "] to html: [" + targetHtmlPath.toString() + "]");

        byte[] docContent = Files.readAllBytes(docPath);
        byte[] targetHtmlContent = DocToHtmlRenderer.getRenderer()
                .fragment(true)
                .ignoreStylesIfUnused(true)
                .omitHeaderFooterPages(true)
                .render(docContent);

        Files.write(targetHtmlPath, targetHtmlContent, StandardOpenOption.CREATE);
    }
}
