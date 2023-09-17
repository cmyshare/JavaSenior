package com.cmyshare.java1;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现TCP的网络编程
 * 练习3：从客户端发送文件给服务端，服务端保存到本地。并返回“发送成功”给客户端。
 * 并关闭相应的连接。
 *
 */
public class TCPTest3 {

    //客户端，这里涉及到的异常，应该使用try-catch-finally处理
    @Test
    public void client() throws IOException {
        //1.创建Socket
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"),9090);
        //2.获取输出流
        OutputStream os = socket.getOutputStream();
        //3.获取输入流
        FileInputStream fis = new FileInputStream(new File("beauty.jpg"));
        //4.读取数据，输出数据
        byte[] buffer = new byte[1024];
        int len;
        while((len = fis.read(buffer)) != -1){
            os.write(buffer,0,len);
        }

        //因为fis.read()方法是阻塞式，客服端什么时候上传完没有指示，服务器端什么时候接收完也没有指示，都不能停止。
        //在客户端调用shutdownOutput方法，关闭socket数据输出
        socket.shutdownOutput();

        //5.接收来自于服务器端的数据，并显示到控制台上
        InputStream is = socket.getInputStream();
        //定义字节数组输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bufferr = new byte[20];
        int len1;
        //接收服务器反馈数据
        while((len1 = is.read(buffer)) != -1){
            baos.write(buffer,0,len1);
        }
        //输出反馈结果
        System.out.println(baos.toString());

        //6.关闭资源
        fis.close();
        os.close();
        socket.close();
        baos.close();
    }

    //服务器端，这里涉及到的异常，应该使用try-catch-finally处理
    @Test
    public void server() throws IOException {
        //1.获取服务器端ServerSocket
        ServerSocket ss = new ServerSocket(9090);
        //2.获取客户端的Socket
        Socket socket = ss.accept();
        //3.获取客户端的输入流
        InputStream is = socket.getInputStream();
        //4.创建输出流
        FileOutputStream fos = new FileOutputStream(new File("beauty1.jpg"));
        //5.读取数据，输出数据
        byte[] buffer = new byte[1024];
        int len;
        while((len = is.read(buffer)) != -1){
            fos.write(buffer,0,len);
        }
        System.out.println("图片传输完成");

        //6.服务器端给予客户端反馈
        OutputStream os = socket.getOutputStream();
        os.write("你好，美女，照片我已收到，非常漂亮！".getBytes());

        //7.关闭资源
        fos.close();
        is.close();
        socket.close();
        ss.close();
        os.close();
    }
}
