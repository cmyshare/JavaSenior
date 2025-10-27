package com.cmyshare.exer;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @version 1.0
 * @Author cmy
 * @Date 2024/6/6 16:39
 * @desc
 */
public class hello2 {
    public static void main(String[] args) {
        // 创建一个Random对象
        Random random = new Random();

        // 创建一个大小为500的数组来存放随机数
        int[] numbers = new int[500];

        // 填充数组
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(1000); // 生成0到999之间的随机整数
        }

        // 创建 ThreadPoolExecutor 线程池，核心线程数和最大线程数都设为 5
        ExecutorService executor = Executors.newFixedThreadPool(5);

        CompletableFuture<?>[] futures = new CompletableFuture[5];
        // 启动 5 个线程
        for (int i = 0; i < 5; i++) {
            final int threadId = i;
            futures[i] = CompletableFuture.runAsync(() -> {
                int printCount = 0;
                long startMinuteTime = System.currentTimeMillis();
                for (int num : numbers) {
                    long printStartTime = System.currentTimeMillis();
                    System.out.println("线程 " + threadId + " 打印数字: " + num);
                    long printEndTime = System.currentTimeMillis();
                    long printElapsedTime = printEndTime - printStartTime;
                    System.out.println(threadId + "200个打印一次耗时：" + printElapsedTime);
                    // todo 单次调用控制
                    //// 若打印耗时小于 1.7 秒，补足到 1.7 秒
                    //if (printElapsedTime < 1700) {
                    //    try {
                    //        Thread.sleep(1700 - printElapsedTime);
                    //    } catch (InterruptedException e) {
                    //        Thread.currentThread().interrupt();
                    //    }
                    //}
                    printCount++;
                    long totalElapsedTime = System.currentTimeMillis() - startMinuteTime;
                    // todo 分钟级别调用控制
                    // 若一分钟内打印次数达到 100 次，等待到一分钟结束
                    if (totalElapsedTime < 60000 && printCount >= 100) {
                        try {
                            System.out.println(threadId + "若一分钟内打印次数达到 100 次，等待到一分钟结束");
                            Thread.sleep(60000 - totalElapsedTime);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        printCount = 0;
                        startMinuteTime = System.currentTimeMillis();
                    }
                }
            }, executor);
        }

        // 等待所有任务完成
        CompletableFuture.allOf(futures).join();

        // 关闭线程池
        executor.shutdown();
    }
}
