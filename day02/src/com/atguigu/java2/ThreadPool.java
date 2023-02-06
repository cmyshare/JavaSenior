package com.atguigu.java2;

import java.util.concurrent.*;

/**
 * 创建线程的方式四：使用线程池 由Executors工具类创建
 *
 * 好处：
 * 1.提高响应速度（减少了创建新线程的时间）
 * 2.降低资源消耗（重复利用线程池中线程，不需要每次都创建）
 * 3.便于线程管理
 *      corePoolSize：核心池的大小
 *      maximumPoolSize：最大线程数
 *      keepAliveTime：线程没有任务时最多保持多长时间后会终止
 *
 * 面试题：创建多线程有几种方式？四种！
 */

class NumberThread implements Runnable{

    @Override
    public void run() {
        for(int i = 0;i <= 100;i++){
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}

class NumberThread1 implements Runnable{

    @Override
    public void run() {
        for(int i = 0;i <= 100;i++){
            if(i % 2 != 0){
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}

public class ThreadPool {

    public static void main(String[] args) {

        //1. 提供指定线程数量的线程池 由Executors工具类创建
        ExecutorService service = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor service1 = (ThreadPoolExecutor) service;
        //设置线程池的属性
        /**
         * corePoolSize：表示当前线程池的核心线程数大小，即最小线程数(初始化线程数)，线程池会维护当前数据的线程在线程池中，即使这些线程一直处于闲置状态，也不会被销毁；
         * maximumPoolSize：表示线程池中允许的最大线程数；后文中会详细讲解
         * keepAliveTime ：表示空闲线程的存活时间，当线程池中的线程数量大于核心线程数且线程处于空闲状态，那么在指定时间后，这个空闲线程将会被销毁，从而逐渐恢复到稳定的核心线程数数量；
         * unit：当前unit表示的是keepAliveTime存活时间的计量单位，通常使用TimeUnit.SECONDS秒级；
         * workQueue：任务工作队列；后文会结合maximumPoolSize一块来讲
         * threadFactory：线程工厂，用于创建新线程以及为线程起名字等
         * handler：拒绝策略，即当任务过多无法及时处理时所需采取的策略；
         */
        //输出对象的运行时类的Class对象
        System.out.println(service.getClass());
        //核心池大小
        service1.setCorePoolSize(15);
        //线程没任务终止时间
        service1.setKeepAliveTime(10, TimeUnit.MINUTES);
        //最大线程数
        service1.setMaximumPoolSize(150);
        //任务工作队列
        //FixedThreadPool和SingleThreadExecutor: 允许请求的队列长度为Integer.MAX_VALUE
        BlockingQueue<Runnable> queue = service1.getQueue();
        System.out.println(queue.size());
        //自定义线程工厂ThreadFactoryBuilder 可默认DefaultThreadFactory
        //service1.setThreadFactory(new ThreadFactoryBuilder().setNameFormat("AppName_FutureTask-%d").setDaemon(true).build());
        //拒绝策略
        service1.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        //2.执行指定的线程的操作。需要提供实现Runnable接口或Callable接口实现类的对象
        service.execute(new NumberThread());//适合适用于Runnable
        service.execute(new NumberThread1());//适合适用于Runnable
        //service.submit(Callable callable);//适合使用于Callable
        //3.关闭连接池
        service.shutdown();
    }

}