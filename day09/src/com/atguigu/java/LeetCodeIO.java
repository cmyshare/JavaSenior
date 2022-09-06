package com.atguigu.java;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author cmy
 * @version 1.0
 * @date 2022/9/6 18:21
 * @description 力扣算法题IO练习
 */
public class LeetCodeIO {

    public static void main(String[] args) {
        //1、BufferedReader_read()在控制台标准输入中读取一个字符
        //BufferedReader_read();

        //2、BufferedReader_readLine()在控制台标准输入中读取一个字符串
        //BufferedReader_readline();

        //3、通过 Scanner类的next()与nextLine()方法获取输入的字符串。
        //Scanner_next();
        //Scanner_nextLine();
        //Scanner_next_nextLine();

        //4、Scanner读取整数、浮点数等基础数据类型
        //Scanner_baseTypes();

        //5、Scanner读取整型\字符串数组
        //Scanner_StringArray();

        //6、Scanner读取二维数组
        //Scanner_TwoArray();

        //7、Scanner换行读取数字、字符串、字符串数组(包含空格)，字符串分割为数组
        Scanner_IntStringArraySpace();
    }

    /**
     * 1、BufferedReader_read()在控制台标准输入中读取一个字符
     *
     * @Test测试启动会无视输入操作
     */
    @Test
    public static void BufferedReader_read() {
        char c;
        //使用System.in创建BufferedReader
        //InputStreamReader：将一个字节的输入流转换为字符的输入流
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("输入字符, 按下 'q' 键退出。");
        // 读取字符
        try {
            do {
                c = (char) br.read();
                System.out.println(c);
            } while (c != 'q');
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 2、BufferedReader_readLine()在控制台标准输入中读取一行字符串
     */
    @Test
    public static void BufferedReader_readline() {
        // 使用 System.in 创建 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        System.out.println("Enter lines of text.");
        System.out.println("Enter 'end' to quit.");

        try {
            do {
                str = br.readLine();
                System.out.println(str);
            } while (!str.equals("end"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 3、通过 Scanner类的next()与nextLine()方法获取输入的字符串。
     * java.util.Scanner是Java5的新特征，获取用户的输入。
     * 读取前使用hasNext与hasNextLine判断是否还有输入的数据。
     * <p>
     * next() 与 nextLine() 区别
     * next():
     * 1、一定要读取到有效字符后才可以结束输入。
     * 2、对输入有效字符之前遇到的空白，next() 方法会自动将其去掉。
     * 3、只有输入有效字符后才将其后面输入的空白作为分隔符或者结束符。
     * 4、next() 不能得到带有空格的字符串。
     * <p>
     * nextLine()：
     * 1、以Enter为结束符,也就是说 nextLine()方法返回的是输入回车之前的所有字符。
     * 2、可以获得空白。
     * <p>
     * 如果要输入 int 或 float 类型的数据，在 Scanner 类中也有支持，
     * 但是在输入之前最好先使用 hasNextXxx() 方法进行验证，再使用 nextXxx() 来读取：
     */
    @Test
    public static void Scanner_next() {
        // 从键盘接收数据
        Scanner scan = new Scanner(System.in);
        // next方式接收字符串
        System.out.println("next方式接收：");
        // 判断是否还有输入
        if (scan.hasNext()) {
            String str1 = scan.next();
            System.out.println("输入的数据为：" + str1);
        }
        scan.close();
    }

    @Test
    public static void Scanner_nextLine() {
        // 从键盘接收数据
        Scanner scan = new Scanner(System.in);
        // nextLine方式接收一行字符串
        System.out.println("nextLine方式接收：");
        // 判断是否还有输入
        if (scan.hasNextLine()) {
            String str2 = scan.nextLine();
            System.out.println("输入的数据为：" + str2);
        }
        scan.close();
    }

    public static void Scanner_next_nextLine() {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入字符串:");

        //next():只读取输入直到空格。空格作为停止符
        String str = sc.next();

        //这里不换行，输入str后，enter直接九结束了，str2为空
        //解决方式：1、将str、str2输入到一行以空格隔开。2、加入sc.nextLine()光标换行操作

        //nextLine():读取输入，包括单词之间的空格和除回车以外的所有符号。回车作为停止符
        String str2 = sc.nextLine();

        System.out.println("str：" + str);
        System.out.println("str2：" + str2);

        //关闭
        sc.close();
    }

    /**
     * 4、Scanner读取整数、浮点数等基础数据类型
     */
    @Test
    public static void Scanner_baseTypes() {
        // 从键盘接收数据
        Scanner scan = new Scanner(System.in);
        // next方式接收整形，以空白为结束符
        System.out.println("nextInt方式接收整型：");
        // 判断是否还有输入
        if (scan.hasNextInt()) {
            int int1 = scan.nextInt();
            System.out.println("输入的数据为：" + int1);
        }
        scan.close();
    }

    /**
     * 5、Scanner读取整型\字符串数组
     */
    @Test
    public static void Scanner_StringArray() {
        //创建对象
        Scanner sc = new Scanner(System.in);
        System.out.println("输入数据:");
        //输入整型数组长度n 字符串数组长度m
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] arr = new int[n];
        String[] str = new String[m];
        //int等基本数据类型的数组,用nextInt()，同行或不同都可以
        for (int i = 0; i < n; i++) {
            if (sc.hasNextInt()) {
                arr[i] = sc.nextInt();
            }
        }
        //String字符串数组, 读取用next()，以空格划分
        for (int i = 0; i < m; i++) {
            if (sc.hasNext()) {
                str[i] = sc.next();
            }
        }

        //调用方法进行操作
        System.out.println("数据n：" + n + ", 数据m：" + m);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(str));
        //关闭
        sc.close();
    }

    /**
     * 6、Scanner读取二维数组
     */
    @Test
    public static void Scanner_TwoArray() {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入数据:");

        //二维数组
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] arr2 = new int[n][m];
        System.out.println("Test02 输入二维数组数据：");

        //可以直接读入
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //读取用nextInt()，以空格划分
                arr2[i][j] = sc.nextInt();
            }
        }

        //输出二维数组
        System.out.println("数据n：" + n + ", 数据m：" + m);
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(arr2[i]));
        }
        System.out.println("数组行数： arr.length= " + arr2.length);
        System.out.println("数组列数： arr[0].length= " + arr2[0].length);
        //关闭
        sc.close();
    }

    /**
     * 7、Scanner换行读取数字、字符串、字符串数组(包含空格)，字符串分割为数组
     * next()读取到空白停止，在读取输入后将光标放在同一行中。
     * nextLine()读取到回车停止 ，在读取输入后将光标放在下一行。
     * <p>
     * next()和nextLine()链接注意：换行sc.nextLine();
     */
    @Test
    public static void Scanner_IntStringArraySpace() {
        Scanner sc = new Scanner(System.in);
        //输入以一个整形
        int n = sc.nextInt();

        //注意！！！光标换到下一行
        sc.nextLine();

        //输入一行空格间隔字符串
        String spaceString = sc.nextLine();
        //字符串分割为数组
        String[] split = spaceString.split(" ");

        //定义字符串数组
        String[] strs = new String[n];
        //将光标移动到下一行
        sc.nextLine();
        //循环输出字符串，每次获取输入回车之前的所有字符，加入字符串数组
        for (int i = 0; i < n; i++) {
            String str = sc.nextLine();
            strs[i] = str;
        }

        System.out.println(n);
        System.out.println(Arrays.toString(split));
        ////输出字符串数组
        //for (int i = 0; i < strs.length; i++) {
        //    String str = strs[i];
        //    System.out.println(str);
        //}
        sc.close();
    }
}

