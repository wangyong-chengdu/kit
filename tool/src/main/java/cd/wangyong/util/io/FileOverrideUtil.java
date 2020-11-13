package cd.wangyong.util.io;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public final class FileOverrideUtil {
    private static final AtomicInteger counter = new AtomicInteger(0);

    private static final ExecutorService executorService = new ThreadPoolExecutor(4, 8,
            100, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(2000), new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setName("File-Override-Thread-" + counter.getAndIncrement());
                    return thread;
                }},
            new ThreadPoolExecutor.CallerRunsPolicy());

    /**
     * 异步批量替换
     * @param directory 路径名
     * @param filterSuffix 文件后缀名
     * @param pattern 正则表达式
     * @param toReplace 替换的文本内容
     */
    public static void batchReplaceAsync(File directory, String filterSuffix, Pattern pattern, String toReplace) {
        executorService.submit(() -> batchReplace(directory, filterSuffix, pattern, toReplace));
    }

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
        System.out.println(Thread.currentThread().getName() + "-" + directory.getName() + " Done.");
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

    @Override
    protected void finalize() throws Throwable {
        if (!executorService.isShutdown())
            executorService.shutdown();
        super.finalize();
    }
}
