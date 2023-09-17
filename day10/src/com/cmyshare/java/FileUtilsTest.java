package com.cmyshare.java;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * IO工具类FileUtils的使用
 */
public class FileUtilsTest {

    public static void main(String[] args) {
        File srcFile = new File("day10\\爱情与友情.jpg");
        File destFile = new File("day10\\爱情与友情2.jpg");

        try {
            //copyFile底层使用了文件字节输入输出流FileInputStream和FileOutputStream、
            //还使用了NIO文件输入输出管道FileChannel。
            FileUtils.copyFile(srcFile,destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
