package com.atguigu;

import java.util.HashSet;

/**
 * @author cmy
 * @version 1.0
 * @date 2022/10/27 18:38
 * @description 说说hashCode() 和 equals() 之间的关系
 *
 * equals() 的作用是用来判断两个对象是否相等。
 * hashCode() 的作用是获取哈希码，也称为散列码；它实际上是返回一个int整数。这个哈希码的作用是确定该对象在哈希表中的索引位置。
 *
 * 原则：
 * 1.同一个对象（没有发生过修改）无论何时调用hashCode()得到的返回值必须一样。
 * 2.hashCode()的返回值相等的对象equals不一定相等，通过hashCode()和equals()必须能唯一确定一个对象。
 * 3.一旦重写了equals()函数（重写equals的时候还要注意要满足自反性、对称性、传递性、一致性），就必须重写hashCode()函数。
 *
 * 参考链接：
 * https://blog.csdn.net/javassb/article/details/115756922
 */
public class EqualsHashCode {

    public static void main(String[] args) {
        /**
         * 1、不会创建“类对应的散列表”
         * 不会在HashSet, Hashtable, HashMap等等这些本质是散列表的数据结构中，用到该类hashCode
         * 在这种情况下，该类的“hashCode() 和 equals() ”没有半毛钱关系的！
         * equals()用来比较该类的两个对象是否相等。而hashCode() 则根本没有任何作用。
         *
         * 结果：
         * p1.equals(p2) : true; p1(242481580) p2(1627800613)
         * p1.equals(p3) : true; p1(242481580) p3(242481580)
         * p1.equals(p4) : false; p1(242481580) p4(1282788025)
         *
         * 结论：
         * 不同对象，p1和p2，Equals相等，hashCode()也不一定相等。
         * 相同对象，p1和p3，Equals相等，hashCode()一定相等。
         *                equals不相等，hashCode有可能会相等。
         *                hashCode不相等，equals肯定不相等。
         *                hashCode相等。equals不一定相等。
         *
         */
        //Person p1 = new Person("eee", 100);
        //Person p2 = new Person("eee", 100);
        ////浅拷贝（shallowCopy）只是增加了一个指针指向已存在的内存地址。
        ////深拷贝（deepCopy）是增加了一个指针并且申请了一个新的内存，使这个增加的指针指向这个新的内存
        //Person p3 = p1; //同一对象复制
        //Person p4 = new Person("aaa", 200);
        //System.out.printf("p1.equals(p2) : %s; p1(%d) p2(%d)\n", p1.equals(p2), p1.hashCode(), p2.hashCode());
        //System.out.printf("p1.equals(p3) : %s; p1(%d) p3(%d)\n", p1.equals(p3), p1.hashCode(), p3.hashCode());
        //System.out.printf("p1.equals(p4) : %s; p1(%d) p4(%d)\n", p1.equals(p4), p1.hashCode(), p4.hashCode());

        System.out.println("**************************************************************************");

        /**
         * 2、会创建“类对应的散列表”
         *
         * 类的“hashCode() 和 equals() ”是有关系的：
         * 如果两个对象相等，那么它们的hashCode()值一定相同。这里的相等是指，通过equals()比较两个对象时返回true。
         * 如果两个对象hashCode()相等，它们并不一定相等。因为在散列表中，hashCode()相等，即两个键值对的哈希值相等。
         * 然而哈希值相等，并不一定能得出键值对相等。补充说一句：“两个不同的键值对，哈希值相等”，这就是哈希冲突。
         * 若要判断两个对象是否相等，h除了要覆盖equals()之外，也要覆盖hasCode()函数。否则，equals()无效。
         *
         * 结果：
         * p1.equals(p2) : true; p1(68545) p2(68545)
         * p1.equals(p4) : false; p1(68545) p4(68545)
         * set:[eee - 100, aaa - 200]
         *
         * 结论：
         * 这下，equals()生效了，HashSet中没有重复元素。
         * 比较p1和p2，hashCode()相等，equals()比较也返回true。所以，p1和p2被视为相等。
         * 比较p1和p4，hashCode()相等；equals()比较返回false。所以，p1和p4被视为不相等。
         */
        // 新建Person对象，
        Person1 p1 = new Person1("eee", 100);
        Person1 p2 = new Person1("eee", 100);
        Person1 p3 = new Person1("aaa", 200);
        Person1 p4 = new Person1("EEE", 100);
        // 新建HashSet对象
        HashSet set = new HashSet();
        set.add(p1);
        set.add(p2);
        set.add(p3);
        // 比较p1 和 p2， 并打印它们的hashCode()
        System.out.printf("p1.equals(p2) : %s; p1(%d) p2(%d)\n", p1.equals(p2), p1.hashCode(), p2.hashCode());
        // 比较p1 和 p4， 并打印它们的hashCode()
        System.out.printf("p1.equals(p4) : %s; p1(%d) p4(%d)\n", p1.equals(p4), p1.hashCode(), p4.hashCode());
        // 打印set
        System.out.printf("set:%s\n", set);

    }
}

/**
 * 1、不会创建“类对应的散列表”
 */
class Person {
    int age;
    String name;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return name + " - " +age;
    }

    /**
     * @desc 覆盖equals方法
     */
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }

        //如果是同一个对象返回true，反之返回false
        if(this == obj){
            return true;
        }

        //判断是否类型相同
        if(this.getClass() != obj.getClass()){
            return false;
        }

        Person person = (Person)obj;
        return name.equals(person.name) && age==person.age;
    }
}

/**
 * 2、会创建“类对应的散列表”，重写equals、重写hashCode让equals生效。
 */
class Person1 {
    int age;
    String name;

    public Person1(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return name + " - " +age;
    }

    /**
     * @desc重写hashCode
     */
    @Override
    public int hashCode(){
        int nameHash =  name.toUpperCase().hashCode();
        //异或运算 两个位相同为0，相异为1
        return nameHash ^ age;
    }

    /**
     * @desc 覆盖equals方法
     */
    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }

        //如果是同一个对象返回true，反之返回false
        if(this == obj){
            return true;
        }

        //判断是否类型相同
        if(this.getClass() != obj.getClass()){
            return false;
        }
        Person1 person1 = (Person1)obj;
        return name.equals(person1.name) && age==person1.age;
    }
}