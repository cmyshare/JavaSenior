package com.atguigu.java;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

/**
 * @author cmy
 * @version 1.0
 * @date 2022/7/14 0014 16:44
 * @description AIO异步非阻塞IO模式测试类
 */
public class IO_AioTest {
        public static void main(String[] args) throws IOException {
            // aioReadFile();
            // aioWriteFile();
            aioWriteFile_2();
        }

        public static void aioReadFile() throws IOException {
            Path path = Paths.get("D:\\out.txt");
            try (AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                Future<Integer> read = fileChannel.read(byteBuffer, 0);
                while (!read.isDone()) {
                    System.out.println("reading");
                }
                System.out.println(read.isDone());
                System.out.println(byteBuffer);
                byte[] array = byteBuffer.array();
                System.out.println(array.length);

                System.out.println(new String(array, "UTF-8"));
            }
        }

        public static void aioWriteFile() throws IOException {
            Path path = Paths.get("D:\\outaio.txt");
            try (AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE);) {
                ByteBuffer byteBuffer = ByteBuffer.wrap("我就想写点东西".getBytes("UTF-8"));
                /**
                 * 设置capacity position limit 在读写模式下 capacity相同 都是设置的缓冲区大小 limit读是缓存中实际数据多少 写模式下=capacity
                 * 因此此处就position重要 表示设置bytebuffer从头开始写
                 *
                 * byteBuffer.flip();
                 */
                Future<Integer> write = fileChannel.write(byteBuffer, 0);
                while (!write.isDone()) {
                    System.out.println("writing");
                }
                System.out.println(write.isDone());
                System.out.println(new String(byteBuffer.array(), "UTf-8"));
            }
        }

        public static void aioWriteFile_2() throws IOException {
            Path path = Paths.get("D:\\outaio2.txt");
            try (AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE);) {
                ByteBuffer byteBuffer = ByteBuffer.wrap("我就想写点东西".getBytes("UTF-8"));
                /**
                 * 设置capacity position limit 在读写模式下 capacity相同 都是设置的缓冲区大小 limit读是缓存中实际数据多少 写模式下=capacity
                 * 因此此处就position重要 表示设置bytebuffer从头开始写
                 *
                 * byteBuffer.flip();
                 */
                fileChannel.write(byteBuffer, 0, "anything is ok here", new CompletionHandler<Integer, String>() {
                    @Override
                    public void completed(Integer result, String attachment) {
                        System.out.println(result + "/" + attachment);
                        System.out.println(byteBuffer);
                    }

                    @Override
                    public void failed(Throwable exc, String attachment) {
                        System.out.println(byteBuffer);
                    }
                });
                //这里没法获取write对象 所以不能像上面一样判断是否结束 所以让线程睡一会
                Thread.sleep(5050);
                System.out.println(new String(byteBuffer.array(), "UTf-8"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

}
