package com.cmyshare.java1;

/**
 * 定义子类
 * 继承父类<泛型>，实现比较器Comparable，实现自定义接口
 */

@MyAnnotation(value="hi") //使用注解修饰类
public class Person extends Creature<String> implements Comparable<String>,MyInterface{

    private String name;
    int age;
    public int id;

    public Person(){}

    @MyAnnotation(value="abc") //使用注解修饰构造器
    private Person(String name){
        this.name = name;
    }

     Person(String name,int age){
        this.name = name;
        this.age = age;
    }

    @MyAnnotation //使用注解修饰方法
    private String show(String nation){
        System.out.println("我的国籍是：" + nation);
        return nation;
    }

    public String display(String interests,int age) throws NullPointerException,ClassCastException{
        return interests + age;
    }


    @Override
    public void info() {
        System.out.println("我是一个人");
    }

    @Override
    public int compareTo(String o) {
        //按照person对象的name排序，默认小于0升序，大于0降序
        return this.name.compareTo(o);
    }

    //静态方法
    private static void showDesc(){
        System.out.println("我是一个可爱的人");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
