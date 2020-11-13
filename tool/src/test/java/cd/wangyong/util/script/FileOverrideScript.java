package cd.wangyong.util.script;

import java.io.File;
import java.util.regex.Pattern;

import cd.wangyong.util.io.FileOverrideUtil;

public class FileOverrideScript {
    public static void main(String[] args) {
        FileOverrideUtil.batchReplaceAsync(new File("/home/andy/文档/MySQL实战45讲"), ".html",
                Pattern.compile("<script .+_files/main.js\"></script>"), "");
//
//        FileOverrideUtil.batchReplaceAsync(new File("/home/andy/文档/分布式技术原理与算法解析"), ".html",
//                Pattern.compile("<script .+_files/main.js\"></script>"), "");

//        FileOverrideUtil.batchReplaceAsync(new File("/home/andy/文档/RPC实战与核心原理"), ".html",
//                Pattern.compile("<script .+_files/main.js\"></script>"), "");

//        FileOverrideUtil.batchReplaceAsync(new File("/home/andy/文档/消息队列高手课"), ".html",
//                Pattern.compile("<script .+_files/main.js\"></script>"), "");
//
//        FileOverrideUtil.batchReplaceAsync(new File("/home/andy/文档/后端存储实战课"), ".html",
//                Pattern.compile("<script .+_files/main.js\"></script>"), "");
//
//        FileOverrideUtil.batchReplaceAsync(new File("/home/andy/文档/后端技术面试38讲"), ".html",
//                Pattern.compile("<script .+_files/main.js\"></script>"), "");
//
//        FileOverrideUtil.batchReplaceAsync(new File("/home/andy/文档/高并发系统设计40问"), ".html",
//                Pattern.compile("<script .+_files/main.js\"></script>"), "");
//
//        FileOverrideUtil.batchReplaceAsync(new File("/home/andy/文档/分布式协议与算法实战"), ".html",
//                Pattern.compile("<script .+_files/main.js\"></script>"), "");
//
//        FileOverrideUtil.batchReplaceAsync(new File("/home/andy/文档/分布式技术原理与算法解析"), ".html",
//                Pattern.compile("<script .+_files/main.js\"></script>"), "");
//
//        FileOverrideUtil.batchReplaceAsync(new File("/home/andy/文档/性能测试实战30讲"), ".html",
//                Pattern.compile("<script .+_files/main.js\"></script>"), "");
//
//        FileOverrideUtil.batchReplaceAsync(new File("/home/andy/文档/性能优化必知必会"), ".html",
//                Pattern.compile("<script .+_files/main.js\"></script>"), "");
//
//        FileOverrideUtil.batchReplaceAsync(new File("/home/andy/文档/数据结构与算法之美"), ".html",
//                Pattern.compile("<script .+_files/main.js\"></script>"), "");
//
//        FileOverrideUtil.batchReplaceAsync(new File("/home/andy/文档/趣谈Linux操作系统"), ".html",
//                Pattern.compile("<script .+_files/main.js\"></script>"), "");
//
//        FileOverrideUtil.batchReplaceAsync(new File("/home/andy/文档/深入浅出计算机组成原理"), ".html",
//                Pattern.compile("<script .+_files/main.js\"></script>"), "");
//
//        FileOverrideUtil.batchReplaceAsync(new File("/home/andy/文档/深入剖析Kubernetes"), ".html",
//                Pattern.compile("<script .+_files/main.js\"></script>"), "");
//
//        FileOverrideUtil.batchReplaceAsync(new File("/home/andy/文档/DDD实战课"), ".html",
//                Pattern.compile("<script .+_files/main.js\"></script>"), "");
//
//        FileOverrideUtil.batchReplaceAsync(new File("/home/andy/文档/Java并发编程实战"), ".html",
//                Pattern.compile("<script .+_files/main.js\"></script>"), "");
//
//        FileOverrideUtil.batchReplaceAsync(new File("/home/andy/文档/Kafka核心技术与实战"), ".html",
//                Pattern.compile("<script .+_files/main.js\"></script>"), "");
//
//        FileOverrideUtil.batchReplaceAsync(new File("/home/andy/文档/Kafka源码解读"), ".html",
//                Pattern.compile("<script .+_files/main.js\"></script>"), "");
//
//        FileOverrideUtil.batchReplaceAsync(new File("/home/andy/文档/设计模式之美"), ".html",
//                Pattern.compile("<script .+_files/main.js\"></script>"), "");

        while (Thread.activeCount() > 0) {
            Thread.yield();
        }
    }
}
