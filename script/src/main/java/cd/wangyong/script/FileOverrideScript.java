package cd.wangyong.script;

import java.io.File;
import java.util.regex.Pattern;

import cd.wangyong.tool.FileOverrideUtil;

public class FileOverrideScript {
    public static void main(String[] args) {
        FileOverrideUtil.batchReplace(new File("/home/andy/文档/Redis核心技术与实战"), ".html",
                Pattern.compile("<script .+_files/main.js\"></script>"), "");
    }
}
