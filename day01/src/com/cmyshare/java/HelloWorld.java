package com.cmyshare.java;


import java.time.LocalDate;
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

        LocalDate start = LocalDate.now();
        int quarter = (start.getMonthValue()-1)/3 + 1;

        start = start.withMonth(quarter*3 - 2*quarter);
        System.out.println(9);
        System.out.println(start);
    }
}
