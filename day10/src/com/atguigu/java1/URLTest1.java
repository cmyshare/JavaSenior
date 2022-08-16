package com.atguigu.java1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * URL网络编程，实现从Tomcat服务器端下载图片数据，类似网络爬虫
 */
public class URLTest1 {

    public static void main(String[] args) {
        //定义HTTP协议URL连接
        HttpURLConnection urlConnection = null;
        //输入流
        InputStream is = null;
        //文件字节输出流
        FileOutputStream fos = null;
        try {
            //实例化URL对象
            URL url = new URL("http://localhost:8080/examples/beauty.jpg");
            //拿到连接
            urlConnection = (HttpURLConnection) url.openConnection();
            //获取连接
            urlConnection.connect();
            //拿到输入流
            is = urlConnection.getInputStream();
            //定义文件字节输出流，main方法对应当前工程
            fos = new FileOutputStream("day10\\beauty3.jpg");
            //定义存储空间
            byte[] buffer = new byte[1024];
            int len;
            //读取文件
            while((len = is.read(buffer)) != -1){
                //存入文件
                fos.write(buffer,0,len);
            }
            System.out.println("下载完成");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //关闭资源
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //关闭资源
            if(urlConnection != null){
                //断开连接
                urlConnection.disconnect();
            }
        }
    }
}
