package com.cmyshare.java;

import org.junit.Test;

import java.io.*;

/**
 * 测试FileInputStream和FileOutputStream的使用
 * 结论：
 * 1. 对于文本文件(.txt,.java,.c,.cpp)，使用字符流处理
 * 2. 对于非文本文件(.jpg,.mp3,.mp4,.avi,.doc,.ppt,...)，使用字节流处理
 */
public class FileInputOutputStreamTest {

    /**
     * 将非文本文件内容读入程序中，并输出到控制台
     * 使用FileInputStream继承InputStream的重载read方法
     * 注意：使用字节流FileInputStream处理文本文件，可能出现乱码
     */
    @Test
    public void testFileInputStream() {
        FileInputStream fis = null;
        try {
            //1.造文件
            File file = new File("hello.txt");
            //2.造流
            fis = new FileInputStream(file);
            //3.读数据
            byte[] buffer = new byte[5];
            int len;//记录每次读取的字节的个数
            while((len = fis.read(buffer)) != -1){
                //放入String
                String str = new String(buffer,0,len);
                System.out.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fis != null){
                //4.关闭资源
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 实现对图片的复制操作
     * 使用FileOutputStream继承OutputStream的重载write方法
     * 将非文本文件，一块一块地输出到目标文件。
     */
    @Test
    public void testFileInputOutputStream()  {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //定义输入file、输出file
            File srcFile = new File("爱情与友情.jpg");
            File destFile = new File("爱情与友情2.jpg");
            //定义输入、输出
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);
            //复制的过程
            byte[] buffer = new byte[5];
            int len;
            while((len = fis.read(buffer)) != -1){
                fos.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 指定路径下文件的复制
     * @param srcPath
     * @param destPath
     */
    public void copyFile(String srcPath,String destPath){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            File srcFile = new File(srcPath);
            File destFile = new File(destPath);
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);
            //复制的过程 字节大小不影响时间，只影响消耗内存
            byte[] buffer = new byte[1024];
            int len;
            while((len = fis.read(buffer)) != -1){
                fos.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos != null){
                //
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 注意：字节流可用于文本文件复制，不在内存看。字符流不可用于非文本文件复制。
     */
    @Test
    public void testCopyFile(){
        //开始时间
        long start = System.currentTimeMillis();

        //绝对文件路径
        //String srcPath = "C:\\Users\\Administrator\\Desktop\\01-视频.avi";
        //String destPath = "C:\\Users\\Administrator\\Desktop\\02-视频.avi";
        //在类方法下，相较于当前工程。
        String srcPath = "hello.txt";
        String destPath = "hello3.txt";
        //文件复制
        copyFile(srcPath,destPath);

        //结束时间
        long end = System.currentTimeMillis();
        System.out.println("复制操作花费的时间为：" + (end - start));
    }
}
