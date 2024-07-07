package com.cmyshare.java5;

import java.util.concurrent.*;

/**
 * @author cmy
 * @version 1.0
 * @date 2023/9/17 22:40
 * @description 异步编程工具类CompletableFuture
 */
public class CompletableFutureTest {

    /**
     * 参考链接
     * https://juejin.cn/post/6970558076642394142#heading-11
     * https://juejin.cn/post/6914962224029106190#heading-7
     * https://cloud.tencent.com/developer/article/2276734
     */

    /**
     * ThreadPoolExecutor创建线程池
     */
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(
             5,
            200,
            10,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(100000),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    /**
     * CompletableFuture异步编程
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        System.out.println("main....start....");

        /**
         * CompletableFuture-runAsync：简单创建无返回值的异步任务
         * runAsync：执行CompletableFuture任务，没有返回值。
         */
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            //任务内容
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
        }, executor);
        Void unused = future.get();
        System.out.println("方法完成后的感知"+unused);

        System.out.println("******************************异常处理whenComplete、exceptionally**********************************");

        /**
         * CompletableFuture-supplyAsync：方法完成后的感知whenComplete、exceptionally
         * exceptionally：某个任务执行异常时，执行的回调方法;并且有抛出异常作为参数，传递到回调方法。
         * whenComplete：某个任务执行完成后，执行的回调方法，无返回值；并且whenComplete方法返回的CompletableFuture的result是上个任务的结果。
         * handle：某个任务执行完成后，执行回调方法，并且是有返回值的;并且handle方法返回的CompletableFuture的result是回调方法执行的结果。
         */
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 0;
            System.out.println("运行结果：" + i);
            return i;
        }, executor).whenComplete((res,excption)->{
            //whenComplete监听，虽然能得到异常信息，但是没法修改返回数据。
            System.out.println("异步任务成功完成了...结果是："+res+";异常是:"+excption);
        }).exceptionally(throwable -> {
            //exceptionally监听，感知异常，同时返回默认值
            System.out.println("Exception"+throwable.getMessage());
            return 10;
        });
        //获取异步任务返回值
        Integer integer1 = future1.get();
        System.out.println("方法完成后的感知1"+integer1);

        System.out.println("******************************方法执行完成后的处理handle**********************************");

        /**
         * CompletableFuture-supplyAsync：方法执行完成后的处理handle
         * supplyAsync：执行CompletableFuture任务，支持返回值
         */
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 4;
            System.out.println("运行结果：" + i);
            return i;
        }, executor).handle((res, thr) -> {
            if (res != null) {
                return res * 2;
            }
            if (thr != null) {
                return 0;
            }
            return 0;
        });
        //获取异步任务返回值
        Integer integer2 = future2.get();
        System.out.println("方法完成后的感知2"+integer2);

        System.out.println("******************************线程串行化**********************************");

        /**
         * CompletableFuture线程串行化：
         * 1）、thenRun：不能获取到上一步的执行结果，无返回值
         *  .thenRunAsync(() -> {
         *             System.out.println("任务2启动了...");
         *         }, executor);
         * 2）、thenAcceptAsync;能接受上一步结果，但是无返回值
         * 3）、thenApplyAsync：;能接受上一步结果，有返回值
         */
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 4;
            System.out.println("运行结果：" + i);
            return i;
        }, executor).thenApplyAsync(res -> {
            System.out.println("任务2启动了..." + res);
            return "Hello " + res;
        }, executor);
        String s = future3.get();
        System.out.println("输出最后返回值3"+s);

        System.out.println("******************************两任务组合都要完成**********************************");

        /**
         * 两任务组合 - 都要完成
         * thenCombine：组合两个future，获取两个future的返回结果，并返回当前任务的返回值
         * thenAcceptBoth：组合两个future，获取两个future任务的返回结果，然后处理任务，没有返回值。
         * runAfterBoth：组合两个future，不需要获取两个future的结果，只需两个future处理完任务后，处理该任务。
         */
        CompletableFuture<Object> future4 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1线程：" + Thread.currentThread().getId());
            int i = 10 / 4;
            System.out.println("任务1结束：" );
            return i;
        }, executor);
        Object o = future4.get();
        System.out.println("输出最后返回值4"+o);

        CompletableFuture<Object> future5 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务2线程：" + Thread.currentThread().getId());
            try {
                //任务2睡眠3000毫秒
                Thread.sleep(3000);
                System.out.println("任务2结束：" );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
        }, executor);
        Object o1 = future5.get();
        System.out.println("输出最后返回值5"+o1);

        //任务组合runAfterBothAsync
        future4.runAfterBothAsync(future5,()->{
            System.out.println("任务3开始...");
        },executor);


        //任务组合thenAcceptBothAsync
        future4.thenAcceptBothAsync(future5,(f1,f2)->{
            System.out.println("任务3开始...之前的结果："+f1+"--》"+f2);
        },executor);

        //任务组合thenCombineAsync
        CompletableFuture<String> future6 = future4.thenCombineAsync(future5, (f1, f2) -> {
            System.out.println("任务组合thenCombineAsync========"+f1 + "：" + f2 + " -> Haha");
            return f1 + "：" + f2 + " -> Haha";
        }, executor);

        System.out.println("******************************两任务组合任一完成**********************************");

        /**
         * 两个任务，只要有一个完成，我们就执行任务3
         * runAfterEitherAsync：不感知结果，自己没有返回值
         * acceptEitherAsync：感知结果，自己没有返回值
         * applyToEitherAsync：感知结果，自己有返回值
         */
        future4.runAfterEitherAsync(future5,()->{
            System.out.println("任务3开始...之前的结果：");
        },executor);
        future4.acceptEitherAsync(future5,(res)->{
            System.out.println("任务3开始...之前的结果："+res);
        },executor);
        CompletableFuture<String> future7 = future4.applyToEitherAsync(future5, res -> {
            System.out.println("任务3开始...之前的结果：" + res);
            return res.toString() + "->哈哈";
        }, executor);


        System.out.println("******************************多任务组合**********************************");

        /**
         * 多任务组合：处理任务中的并发读并发写，大幅度提升性能
         * allOf： 等待所有任务完成
         * anyOf： 只要有一个任务完成
         */
        CompletableFuture<String> futureImg = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询商品的图片信息");
            return "输出异常"+10/0;
        }, executor).exceptionally(res->{
            //手动抛出业务异常
            System.out.println("allOf手动抛出异常"+res);
            throw new IllegalStateException("手动抛出异常");
        });

        CompletableFuture<String> futureAttr = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询商品的属性");
            return "黑色+256G";
        }, executor);

        CompletableFuture<String> futureDesc = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("查询商品介绍");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "华为";
        }, executor);

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futureImg, futureAttr, futureDesc);
        //CompletableFuture<Object> anyOf = CompletableFuture.anyOf(futureImg, futureAttr, futureDesc);
        //CompletableFuture的get()方法是阻塞的，如果使用它来获取异步调用的返回值，需要添加超时时间
        allOf.get(5, TimeUnit.SECONDS);//阻塞等待所有结果完成，也可以用join

        //System.out.println("main....end...."+futureImg.get()+"=>"+futureAttr.get()+"=>"+futureDesc.get());
        System.out.println("main....end...." + allOf.get());
    }
}
