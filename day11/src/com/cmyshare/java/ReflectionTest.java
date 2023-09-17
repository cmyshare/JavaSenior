package com.cmyshare.java;

import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Java使用反射前后对比练习
 *
 * 反射重点：获取Class的实例的方式（前三种方式需要掌握）
 *
 * Class实例可以是哪些结构的说明：
 */
public class ReflectionTest {

    /**
     * 反射之前，对于Person的操作
     */
    @Test
    public void test1() {
        //1.创建Person类的对象
        Person p1 = new Person("Tom", 12);
        //2.通过对象，调用其内部的属性、方法
        p1.age = 10;
        System.out.println(p1.toString());
        p1.show();
        //在Person类外部，不可以通过Person类的对象调用其内部私有结构。
        //比如：name、showNation()以及私有的构造器
    }

    /**
     * 反射之后，对于Person的操作
     */
    @Test
    public void test2() throws Exception{
        //通过类的class属性，获取此类的Class类对象
        Class clazz = Person.class;

        //1.通过反射，通过构造器创建Person类的对象
        Constructor cons = clazz.getConstructor(String.class,int.class);
        //通过构造器的newInstance方法，创建对象
        Object obj = cons.newInstance("Tom", 12);
        Person p = (Person) obj;
        System.out.println(p.toString());

        System.out.println("*******************************");

        //2.通过反射，调用对象指定的属性、方法
        //调用属性
        Field age = clazz.getDeclaredField("age");
        //修改属性值
        age.set(p,10);
        System.out.println(p.toString());
        //调用方法
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(p);

        System.out.println("*******************************");

        //3.通过反射，可调用Person类的私有结构的。比如：私有的构造器、方法、属性
        //调用私有的构造器getDeclaredConstructor，参数为String类型
        Constructor cons1 = clazz.getDeclaredConstructor(String.class);
        //在类外修改类的private成员方法、属性
        cons1.setAccessible(true);
        Person p1 = (Person) cons1.newInstance("Jerry");
        System.out.println(p1);

        //调用私有的属性getDeclaredField，属性名name
        Field name = clazz.getDeclaredField("name");
        //在类外修改类的private成员方法、属性
        name.setAccessible(true);
        name.set(p1,"HanMeimei");
        System.out.println(p1);

        //调用私有的方法getDeclaredMethod，方法名showNation，参数类型String
        Method showNation = clazz.getDeclaredMethod("showNation", String.class);
        //在类外修改类的private成员方法、属性
        showNation.setAccessible(true);
        //调用showNation方法，相当于String nation = p1.showNation("中国")
        String nation = (String) showNation.invoke(p1,"中国");
        System.out.println(nation);
    }

    /**
     * 反射疑问：如何看待反射和封装性两个技术
     * 疑问1：通过直接new的方式或反射的方式都可以调用公共的结构，开发中到底用那个？
     * 建议：直接new的方式。反射什么时候会使用：编译时不确定方式，反射的特征-动态性。
     *
     * 疑问2：反射机制与面向对象中的封装性是不是矛盾的？如何看待两个技术？
     * 不矛盾。封装解决的是建议怎样调用方法(直接调用非私有方法)，反射解决的是能不能调用方法(可以调用私有方法)
     */

    /**
     * 关于java.lang.Class类的理解
     * 1.类的加载过程：
     * 编译：程序经过javac.exe命令以后，会生成一个或多个字节码文件(.class结尾)。
     * 加载：接着使用java.exe命令对某个字节码文件进行解释运行。相当于将某个字节码文件加载到内存中。
     *      此过程就称为类的加载。加载到内存中的类，称为运行时类，此运行时类，就作为Class的一个实例。
     *
     * 2.换句话说，Class的实例就对应着一个运行时类。
     *
     * 3.加载到内存中的运行时类，会缓存一定的时间。在此时间之内，可以通过不同的方式来获取此运行时类。
     */

    /**
     * 反射重点：获取Class的实例的方式（前三种方式需要掌握）
     */
    @Test
    public void test3() throws ClassNotFoundException {
        //方式一：调用运行时类的属性：.class
        Class clazz1 = Person.class;
        System.out.println(clazz1);
        //方式二：通过运行时类的对象,调用getClass()
        Person p1 = new Person();
        Class clazz2 = p1.getClass();
        System.out.println(clazz2);

        //方式三(热门动态)：调用Class的静态方法：forName(String classPath)
        Class clazz3 = Class.forName("com.cmyshare.java.Person");
        //clazz3 = Class.forName("java.lang.String");
        System.out.println(clazz3);

        //判断是否为同一个类
        System.out.println(clazz1 == clazz2);
        System.out.println(clazz1 == clazz3);

        //方式四：使用类的加载器：ClassLoader
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        //AppClassLoader的父类加载器是ExtensionClassLoader JDK1.9之前
        ClassLoader classLoader2 = classLoader.getParent();
        //ExtClassLoader父类加载器(最高一层)Bootstrap C++实现 返回null
        ClassLoader classLoader3 = classLoader2.getParent();
        //输出三层类加载器，向上询问是否加载它，逐层向下尝试是否可加载
        System.out.println(classLoader);
        System.out.println(classLoader2);
        System.out.println(classLoader3);
        //显示地获取加载类Class
        Class clazz4 = classLoader.loadClass("com.cmyshare.java.Person");
        System.out.println(clazz4);
        System.out.println(clazz1 == clazz4);

    }

    /**
     * Class实例是哪些结构的说明：万事万物皆对象？对象.xxx,File,URL,反射,前端、数据库操作
     */
    @Test
    public void test4(){
        //对象
        Class c1 = Object.class;
        //接口
        Class c2 = Comparable.class;
        //数组
        Class c3 = String[].class;
        //多维数组
        Class c4 = int[][].class;
        //枚举类
        Class c5 = ElementType.class;
        //注解
        Class c6 = Override.class;
        //基本数据类型
        Class c7 = int.class;
        //void关键字
        Class c8 = void.class;
        //Class本身
        Class c9 = Class.class;

        int[] a = new int[10];
        int[] b = new int[100];
        Class c10 = a.getClass();
        Class c11 = b.getClass();
        // 只要数组的元素类型与维度一样，就是同一个Class
        System.out.println(c10 == c11);
    }
}
