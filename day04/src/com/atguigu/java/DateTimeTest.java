package com.atguigu.java;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * JDK8之前的日期时间的API
 * 1. System类中currentTimeMillis(); 从Unix元年1970-当前时刻的毫秒值
 * 2. java.util.Date和子类java.sql.Date
 * 3. SimpleDateFormat格式化和解析
 * 4. Calendar日志
 */

public class DateTimeTest {
    /**
     * SimpleDateFormat的使用：SimpleDateFormat对日期Date类的格式化和解析
     * 1.两个操作：
     * 1.1 格式化：日期 --->字符串
     * 1.2 解析：格式化的逆过程，字符串 ---> 日期
     * 2.SimpleDateFormat的实例化
     */
    @Test
    public void testSimpleDateFormat() throws ParseException {
        //实例化SimpleDateFormat:使用默认的构造器
        SimpleDateFormat sdf = new SimpleDateFormat();

        //格式化：format日期 --->字符串
        Date date = new Date();
        System.out.println(date);
        String format = sdf.format(date);
        System.out.println(format);

        //解析：格式化的逆过程，parse字符串 ---> 日期
        String str = "19-12-18 上午11:43";
        Date date1 = sdf.parse(str);
        System.out.println(date1);

        System.out.println("*************按照指定的方式格式化和解析：调用带参的构造器*****************");

        //SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyy.MMMMM.dd GGG hh:mm aaa");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
        //格式化
        String format1 = sdf1.format(date);
        System.out.println(format1);
        //解析:要求字符串必须是符合SimpleDateFormat识别的格式(通过构造器参数体现),
        //否则，抛异常
        Date date2 = sdf1.parse("2020-02-18 11:48:27");
        System.out.println(date2);
    }

    /**
     * 练习一：字符串"2020-09-08"转换为java.sql.Date
     *
     * 练习二："三天打渔两天晒网"   1990-01-01  xxxx-xx-xx 打渔？晒网？
     * 举例：2020-09-08 ？ 总天数
     * 总天数 % 5 == 1,2,3 : 打渔
     * 总天数 % 5 == 4,0 : 晒网
     * 总天数的计算？
     * 方式一：( date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24) + 1
     * 方式二：1990-01-01  --> 2019-12-31  +  2020-01-01 -->2020-09-08
     */
    @Test
    public void testExer() throws ParseException {
        String birth = "2020-09-08";

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf1.parse(birth);
        System.out.println(date);

        java.sql.Date birthDate = new java.sql.Date(date.getTime());
        System.out.println(birthDate);
    }


    /**
     * Calendar日历类(抽象类）的使用
     * Calendar 类是一个抽象类，它提供了在特定时刻和一组日历字段（例如 YEAR、MONTH、DAY_OF_MONTH、HOUR 等）之间进行转换的方法，
     * 以及用于操作日历字段（例如获取日期）的方法下周的。
     */
    @Test
    public void testCalendar(){
        //1.实例化
        //方式一：创建其子类（GregorianCalendar）的对象
        //方式二：调用其静态方法getInstance()
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getClass());

        System.out.println("*****get()指示月份中的哪一天*****");

        //2.常用方法
        //get()指示月份中的哪一天等等时间
        int days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));

        System.out.println("*****set()指定月份中的哪一天*****");

        //calendar可变性:set()指定月份中的哪一天等等时间
        calendar.set(Calendar.DAY_OF_MONTH,22);
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);

        System.out.println("*****add()在给定的日历字段中添加或减去指定的时间量*****");

        //add()在给定的日历字段中添加或减去指定的时间量
        calendar.add(Calendar.DAY_OF_MONTH,-3);
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);

        System.out.println("*****getTime():日历类---> Date*****");

        //getTime():日历类---> Date
        Date date = calendar.getTime();
        System.out.println(date.getTime());

        System.out.println("*****setTime():Date ---> 日历类*****");

        //setTime():Date ---> 日历类
        Date date1 = new Date();
        calendar.setTime(date1);
        //获取月份中的哪一天
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);
    }
}
