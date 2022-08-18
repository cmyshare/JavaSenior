package com.atguigu.java;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.Random;

/**
 * 通过反射创建对应的运行时类的对象
 */
public class NewInstanceTest {

    /**
     * 反射重点：通过newInstance创建运行时类对象
     */
    @Test
    public void test1() throws IllegalAccessException, InstantiationException {
        //通过类的class属性，获取此类的Class类对象
        Class<Person> clazz = Person.class;
        /*
        newInstance()：调用此方法，创建对应的运行时类的对象。内部调用了运行时类的空参的构造器。

        要想此方法正常的创建运行时类的对象。要求：
        1.运行时类必须提供空参的构造器
        2.空参的构造器的访问权限得够。通常设置为public。

        在javabean中要求提供一个public的空参构造器。原因：
        1.便于通过反射，创建运行时类的对象
        2.便于子类继承此运行时类时，默认调用super()时，保证父类有此构造器
         */
        Person obj = clazz.newInstance();
        System.out.println(obj);

    }

    /**
     * 通过getConstructor构造器的newInstance创建运行时类对象
     */
    @Test
    public void test2() throws Exception {
        //通过类的class属性，获取此类的Class类对象
        Class<Person> clazz = Person.class;
        //1.通过反射，通过构造器创建Person类的对象，有参构造
        Constructor cons = clazz.getConstructor(String.class,int.class);
        //通过构造器的newInstance方法，创建对象
        Object obj = cons.newInstance("Tom", 12);
        System.out.println(obj);
    }

    /**
     * 反射难点：体会反射的动态性
     */
    @Test
    public void test3(){
        for(int i = 0;i < 100;i++){
            //随机0,1,2
            int num = new Random().nextInt(3);
            String classPath = "";
            switch(num){
                //classPath下路径的类必须有空参构造器
                case 0:
                    classPath = "java.util.Date";
                    break;
                case 1:
                    classPath = "java.lang.Object";
                    break;
                case 2:
                    classPath = "com.atguigu.java.Person";
                    break;
            }
            try {
                //动态地传入classPath，确定创建哪个运行时类对象。
                Object obj = getInstance(classPath);
                System.out.println(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建一个指定类的对象。classPath:指定类的全类名路径
     */
    public Object getInstance(String classPath) throws Exception {
        //通过调用Class的静态方法，获取此类的Class类对象
       Class clazz =  Class.forName(classPath);
       //返回创建运行时类对象，内部调用空参构造器
       return clazz.newInstance();
    }
}
