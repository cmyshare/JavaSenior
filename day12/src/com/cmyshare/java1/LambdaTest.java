package com.cmyshare.java1;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Lambda表达式的使用举例
 */
public class LambdaTest {

    @Test
    public void test1() {
        //匿名内部类，定义线程
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("我爱北京天安门");
            }
        };
        r1.run();

        System.out.println("***********************");

        //Lambda表达式，定义线程，(参数列表)->lambda体
        Runnable r2 = () -> System.out.println("我爱北京故宫");
        r2.run();
    }


    @Test
    public void test2() {
        //1 匿名内部类写法
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        //小于0升序，大于0降序
        int compare1 = com1.compare(12, 21);
        System.out.println(compare1);

        System.out.println("***********************");

        //2 Lambda表达式写法
        Comparator<Integer> com2 = (o1, o2) -> Integer.compare(o1, o2);
        int compare2 = com2.compare(32, 21);
        System.out.println(compare2);

        System.out.println("***********************");

        //3 方法引用，lambda体使用实现方法Integer.compare(o1,o2)
        Comparator<Integer> com3 = Integer::compare;
        int compare3 = com3.compare(32, 21);
        System.out.println(compare3);
    }
}
