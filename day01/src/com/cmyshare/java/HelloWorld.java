package com.cmyshare.java;


import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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

        List<Object> objects = new ArrayList<>();
        System.out.println(objects == null ? "null" : objects.size());
        System.out.println(Objects.isNull(objects));

        System.out.println("******************LocalDate************************");

        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int monthValue = now.getMonthValue();
        System.out.println(year);
        System.out.println(monthValue);


        System.out.println("******************Date************************");
        System.out.println(String.format("%tY", new Date()));
        System.out.println(String.format("%tm", new Date()));

        System.out.println(Integer.valueOf("02"));


        // 创建一个包含中文字符串的列表，根据中文拼音字母排序
        List<String> chineseFieldNames = Arrays.asList("第二周", "第一周", "第三周");

        // 使用Stream API进行排序
        List<String> sortedFieldNames = chineseFieldNames.stream()
                .sorted(Comparator.comparing(s -> s, Comparator.reverseOrder())) // 逆序排序
                .collect(Collectors.toList());

        // 输出排序后的列表
        sortedFieldNames.forEach(System.out::println);

        Integer i=1;
        System.out.println(i==null);

    }
}
