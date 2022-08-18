package com.atguigu.java;

import org.junit.Test;

import java.io.InputStream;
import java.util.Properties;

/**
 * 了解类的加载器
 */
public class ClassLoaderTest {

    @Test
    public void test1(){
        //对于自定义类，使用系统类加载器进行加载
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);

        //调用系统类加载器的getParent()：获取扩展类加载器
        ClassLoader classLoader1 = classLoader.getParent();
        System.out.println(classLoader1);

        //调用扩展类加载器的getParent()：无法获取引导类加载器。
        //引导类加载器主要负责加载java的核心类库，无法加载自定义类的。
        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println(classLoader2);

        //获取String的引导类加载器
        ClassLoader classLoader3 = String.class.getClassLoader();
        System.out.println(classLoader3);
    }

    /**
     * Properties用来读取配置文件。使用ClassLoader进行配置文件替换
     */
    @Test
    public void test2() throws Exception {
        //实例化Properties对象
        Properties pros =  new Properties();

        //读取配置文件的方式一：
        //目前文件相对路径：在当前的module下
        //FileInputStream fis = new FileInputStream("jdbc.properties");
        //FileInputStream fis = new FileInputStream("src\\jdbc1.properties");
        //加载输入流
        //pros.load(fis);

        //读取配置文件的方式二：使用ClassLoader
        //目前文件相对路径：当前module的src下，在部署时放在这里。
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        //classLoader获取流
        InputStream is = classLoader.getResourceAsStream("jdbc1.properties");
        pros.load(is);

        //获取配置文件参数值
        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        System.out.println("user = " + user + ",password = " + password);

    }
}
