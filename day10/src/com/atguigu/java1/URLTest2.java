package com.atguigu.java1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author cmy
 * @version 1.0
 * @date 2022/7/15 0015 15:00
 * @description 可以直接从 URL 中读取字节流数据。
 * 网络爬虫基础URL
 */
public class URLTest2 {
    public static void main(String[] args) throws IOException {

        URL url = new URL("http://www.baidu.com");

        /* 读取URL中的字节流 */
        InputStream is = url.openStream();

        /* 将输入流放入字符输入流 */
        InputStreamReader isr = new InputStreamReader(is, "utf-8");

        /* 提供缓存功能 */
        BufferedReader br = new BufferedReader(isr);

        String line;
        //读取一行文本。一行被认为是由换行符 ('\n')、回车符 ('\r') 或紧跟换行符的回车符中的任何一个终止的。
        while ((line = br.readLine()) != null) {
            System.out.println(line+"\n");
        }

        br.close();
    }
}
