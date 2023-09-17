package com.cmyshare.java;


import java.util.*;

public class HelloWorld {
    static int num = 10;
    public static final int NUMBER = 1;

    public static void main(String[] args) {
        System.out.println(123);
        System.out.println(123);

        System.out.println("HelloWorld.main");
        System.out.println("args = [" + args + "]");

        System.out.println("num = " + num);

        String a = "abcdefgaba";
        StringBuilder stringBuilder = new StringBuilder(a);
        System.out.println(stringBuilder);
    }
}
