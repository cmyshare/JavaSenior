package com.cmyshare;

import java.io.*;

/**
 * @version 1.0
 * @Author cmy
 * @Date 2024/5/7 19:33
 * @desc
 */
public class readJsonFile {
    public static void main(String[] args) {
        try {
            File file = new File("C:\\Users\\10056\\Desktop\\application.json");
            FileReader fileReader = new FileReader(file);
            Reader reader = new InputStreamReader(new FileInputStream(file), "Utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            String jsonStr = sb.toString();
            System.out.println(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
