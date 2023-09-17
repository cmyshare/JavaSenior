package com.cmyshare.java1;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * 实现TCP的网络编程
 * 练习2：客户端发送文件给服务端，服务端将文件保存在本地。
 * 这里涉及到的异常，应该使用try-catch-finally处理
 *
 */
public class TCPTest2 {

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
        //5.关闭资源
        fis.close();
        os.close();
        socket.close();
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
        //6.关闭资源
        fos.close();
        is.close();
        socket.close();
        ss.close();
    }
}
