package com.cmyshare.java2;

import org.junit.Test;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 1. 泛型在继承方面的体现
 * 2. 通配符的使用
 *
 * @author cmy
 * @create 2019 下午 2:13
 */
public class GenericTest {

    /**
     * 1. 泛型在继承方面的体现
     * 虽然类A是类B的父类，但是G<A> 和G<B>二者不具备子父类关系，二者是并列关系。
     * 补充：类A是类B的父类，A<G> 是 B<G> 的父类
     */
    @Test
    public void test1(){
        //子类向上转型成父类，多态的体现
        Object obj = null;
        String str = null;
        obj = str;
        //子类向上转型成父类，多态的体现
        Object[] arr1 = null;
        String[] arr2 = null;
        arr1 = arr2;

        //编译不通过
        //Date date = new Date();
        //str = date;

        List<Object> list1 = null;
        List<String> list2 = new ArrayList<String>();
        //此时的list1和list2的类型不具有子父类关系，类型不一致编译不通过
        //list1 = list2;

        //反证法：
        //假设list1 = list2;
        //list1.add(123);导致混入非String的数据。出错。
        show(list1);
        show1(list2);

    }

    public void show1(List<String> list){
    }

    public void show(List<Object> list){
    }

    @Test
    public void test2(){
        //AbstractList实现List接口
        AbstractList<String> list1 = null;
        List<String> list2 = null;
        //ArrayList继承AbstractList实现List接口
        ArrayList<String> list3 = null;
        //可以转换，多态的体现
        list1 = list3;
        list2 = list3;

        List<String> list4 = new ArrayList<>();

    }

    /**
     * 2. 通配符的使用
     * 通配符：? 都能配对
     * 类A是类B的父类，G<A>和G<B>是没有关系的，二者共同的父类是：G<?>
     */
    @Test
    public void test3(){
        List<Object> list1 = null;
        List<String> list2 = null;
        List<?> list = null;

        //通配转换，多态体现
        list = list1;
        list = list2;

        List<String> list3 = new ArrayList<>();
        list3.add("AA");
        list3.add("BB");
        list3.add("CC");
        list = list3;

        //添加(写入)：对于List<?>就不能向其内部添加数据，除了添加null之外。
        //list.add("DD");
        //list.add('?');
        list.add(null);

        //获取(读取)：允许读取数据，读取的数据类型为Object。
        Object o = list.get(0);
        System.out.println(o);

        //编译通过 读取List<?>
        print(list);
    }

    //读取List<?>
    public void print(List<?> list){
        Iterator<?> iterator = list.iterator();
        while(iterator.hasNext()){
            Object obj = iterator.next();
            System.out.println(obj);
        }
    }

    /**
     * 3.有限制条件的通配符的使用。
     * ? extends A:
     * G<? extends A> 可以作为G<A>和G<B>的父类，其中B是A的子类，只允许泛型为A及A子类的引用调用
     * ? super A:
     * G<? super A> 可以作为G<A>和G<B>的父类，其中B是A的父类，只允许泛型为A及A父类的引用调用
     * ? extends Compareable
     * G<? extends Compareable> 只允许泛型为实现Comparable接口的实现类的引用调用
     */

    @Test
    public void test4(){
        //只允许泛型为Person及Person子类的引用调用
        List<? extends Person> list1 = null;
        //只允许泛型为Person及Person父类的引用调用
        List<? super Person> list2 = null;

        List<Student> list3 = new ArrayList<Student>();
        List<Person> list4 = new ArrayList<Person>();
        List<Object> list5 = new ArrayList<Object>();

        //list3、list4小于等于list1_向上转换子到父_父类list1引用子类list3、list4
        list1 = list3;
        list1 = list4;
        //list1 = list5;

        //list4、list5大于等于list1_向下转换父到子_子类list1引用父类list4、list5
        //list2 = list3;
        list2 = list4;
        list2 = list5;

        //? extends Person读取数据：
        list1 = list3;
        //只能用小于等于Person
        Person p = list1.get(0);
        //编译不通过
        //Student s = list1.get(0);

        //? super Person读取数据：
        list2 = list4;
        //只能用大于等于Person
        Object obj = list2.get(0);
        //编译不通过
        //Person obj = list2.get(0);

        //? extends Person写入数据：
        //编译不通过，list1添加的？范围是(-∞,person],如果student比-∞还小，就出错。子类可以赋给父类、父类不可赋给子类
        //list1.add(new Student());

        //? super Person写入数据：
        //编译通过 list2添加的？范围是[person,+∞)
        list2.add(new Person());
        list2.add(new Student());
    }

}
