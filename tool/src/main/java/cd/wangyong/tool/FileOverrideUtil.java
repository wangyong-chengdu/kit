package cd.wangyong.tool;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public final class FileOverrideUtil {

    /**
     * 批量替换
     * @param directory 路径名
     * @param filterSuffix 文件后缀名
     * @param pattern 正则表达式
     * @param toReplace 替换的文本内容
     */
    public static void batchReplace(File directory, String filterSuffix, Pattern pattern, String toReplace) {
        Arrays.stream(Objects.requireNonNull(directory.listFiles()))
                .filter(File::isFile)
                .filter(f -> f.getName().endsWith(filterSuffix))
                .forEach(f -> FileOverrideUtil.replace(f, pattern, toReplace));
    }

    /**
     * 文本替换
     * @param textFile 待替换文本文件
     * @param pattern 正则表达式
     * @param toReplace 替换的文本内容
     */
    public static void replace(File textFile, Pattern pattern, String toReplace) {
        try {
            String content = FileUtils.readFileToString(textFile, Charset.defaultCharset());
            Matcher matcher = pattern.matcher(content);
            if (matcher.find()) FileUtils.writeStringToFile(textFile, matcher.replaceAll(toReplace), Charset.defaultCharset());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
