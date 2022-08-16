package com.atguigu.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * @author cmy
 * @version 1.0
 * @date 2022/7/14 0014 16:45
 * @description NIO块式IO测试类 支持阻塞与非阻塞模式
 */
public class IO_NioTest {
        public static void main(String[] args) throws IOException {
            nioReadFile();
            nioWriteFile();
        }

        public static void nioReadFile() throws IOException {
            String path = "D:\\tempFile.txt";
            try (FileInputStream fileInputStream = new FileInputStream(new File(path)); FileChannel channel = fileInputStream.getChannel();) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(100);

                int len = -1;
                while ((len = channel.read(byteBuffer)) != 0    ) {
                    byteBuffer.clear();
                    byte[] array = byteBuffer.array();
                    System.out.write(array, 0, array.length);
                    System.out.println("读取长度："+len);
                }
            }
        }

        public static void nioWriteFile() throws IOException {
            String path = "D:\\out.txt";
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            FileChannel channel = fileOutputStream.getChannel();
            ByteBuffer byteBuffer = Charset.forName("UTF-8").encode("使用nio存储到文件");
            int len = 0;
            while ((len = channel.write(byteBuffer)) != 0) {
                System.out.println("写入长度:" + len);
            }
        }
}
