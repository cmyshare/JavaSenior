package com.atguigu.java;

import java.io.*;
import java.nio.charset.Charset;

/**
 * @author cmy
 * @version 1.0
 * @date 2022/7/14 0014 16:44
 * @description BIO同步阻塞I/O模式测试类
 */
public class IO_BioTest {
        public static void main(String[] args) throws IOException, ClassNotFoundException {
            readFileByIO();
        }

        public static void readFileByIO() throws IOException, ClassNotFoundException {
            Person user = new Person("小松", 20);
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\tempFile.txt"));
                 ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\tempFile.txt"));) {
                oos.writeObject(user.toString().getBytes("UTF-8"));
                System.out.println(user.toString());
                System.out.println(Charset.defaultCharset().displayName());
                System.out.println(ois.readObject());
            }
        }
}
