package com.atguigu.java;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * RandomAccessFile的使用
 * 1.RandomAccessFile直接继承于java.lang.Object类，实现了DataInput和DataOutput接口
 * 2.RandomAccessFile既可以作为一个输入流，又可以作为一个输出流
 *
 * 3.如果RandomAccessFile作为输出流时，写出到的文件如果不存在，则在执行过程中自动创建。
 *   如果写出到的文件存在，则会对原有文件内容进行覆盖。（默认情况下，从头覆盖）
 *
 * 4.可以通过相关的操作，移动操作开始位置指针，实现RandomAccessFile“插入”数据的效果
 *   通过seek()方法将操作指针移动到指定位置，实现RandomAccessFile可应用于文件断点下载
 */
public class RandomAccessFileTest {

    /**
     * RandomAccessFile输入输出流复制图片
     */
    @Test
    public void test1() {
        //1.实例化RandomAccessFile、File
        RandomAccessFile raf1 = null;
        RandomAccessFile raf2 = null;
        try {
            //输入流，mode方式为r只读
            raf1 = new RandomAccessFile(new File("爱情与友情.jpg"),"r");
            //输出流，mode方式为rw可读可写
            raf2 = new RandomAccessFile(new File("爱情与友情1.jpg"),"rw");
            //2.读取写入字节数组
            byte[] buffer = new byte[1024];
            int len;
            //循环遍历读取
            while((len = raf1.read(buffer)) != -1){
                //写入数据
                raf2.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //3.关闭流
            if(raf1 != null){
                try {
                    raf1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(raf2 != null){
                try {
                    raf2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * RandomAccessFile输入输出流向文本写入数据
     */
    @Test
    public void test2() throws IOException {
        //定义RandomAccessFile输入输出流rw
        RandomAccessFile raf1 = new RandomAccessFile("hello.txt","rw");
        //将指针调到角标为3的位置，避免覆盖
        raf1.seek(3);
        //默认会从开头覆盖内容
        raf1.write("xyz".getBytes());
        //关闭流
        raf1.close();

    }

    /**
     * RandomAccessFile输入输出流实现数据的插入效果
     */
    @Test
    public void test3() throws IOException {
        //定义RandomAccessFile输入输出流
        RandomAccessFile raf1 = new RandomAccessFile("hello.txt","rw");
        //将指针调到角标为3的位置
        raf1.seek(3);

        //保存指针3后面的所有数据到StringBuilder中
        //StringBuilder builder = new StringBuilder((int) new File("hello.txt").length());
        //byte[] buffer = new byte[20];
        //int len;
        //while((len = raf1.read(buffer)) != -1){
        //    builder.append(new String(buffer,0,len)) ;
        //}

        //思考：将StringBuilder替换为ByteArrayOutputStream字节数组输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[20];
        int len;
        while((len = raf1.read(buffer)) != -1){
            baos.write(buffer,0,len);
        }

        //调回指针，写入“xyz”
        raf1.seek(3);
        //写入xyz到RandomAccessFile输入输出流
        raf1.write("xyz".getBytes());
        //当前指针为4，将StringBuilder中的数据写入到文件中，直接覆盖写入
        //raf1.write(builder.toString().getBytes());
        raf1.write(baos.toByteArray());
        //关闭流
        baos.close();
        raf1.close();
    }
}
