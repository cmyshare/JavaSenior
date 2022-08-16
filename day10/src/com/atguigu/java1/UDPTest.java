package com.atguigu.java1;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDP协议的网络编程
 */
public class UDPTest {

    //发送端，只管发不管连接和接收端是否接收
    @Test
    public void sender() throws IOException {
        //通过数据报套接字DatagramSocket发送
        DatagramSocket socket = new DatagramSocket();
        //数据准备
        String str = "我是UDP方式发送的导弹";
        byte[] data = str.getBytes();
        //指明对方地址端口号
        InetAddress inet = InetAddress.getLocalHost();
        //DatagramPacket对象封装UDP数据报
        DatagramPacket packet = new DatagramPacket(data,0,data.length,inet,9090);
        //发出UDP数据报
        socket.send(packet);
        //关闭资源
        socket.close();

    }
    //接收端
    @Test
    public void receiver() throws IOException {
        //通过数据报套接字DatagramSocket接收,指定监听的端口9090
        DatagramSocket socket = new DatagramSocket(9090);
        byte[] buffer = new byte[100];
        //DatagramPacket对象接收UDP数据报
        DatagramPacket packet = new DatagramPacket(buffer,0,buffer.length);
        //接收数据报
        socket.receive(packet);
        //输出数据
        System.out.println(new String(packet.getData(),0,packet.getLength()));
        //关闭资源
        socket.close();
    }
}
