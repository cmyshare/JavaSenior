package com.atguigu.java;

import java.util.ArrayList;
import java.util.List;

/**
 * SubOrder继承Order<Integer>泛型类
 *
 * @author cmy
 * @create 2019 上午 11:15
 */
public class SubOrder extends Order<Integer> {
    //SubOrder:不是泛型类

    //泛型方法
    public static <E> List<E> copyFromArrayToList(E[] arr){

        ArrayList<E> list = new ArrayList<>();

        for(E e : arr){
            list.add(e);
        }
        return list;
    }
}
