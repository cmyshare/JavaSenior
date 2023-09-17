package com.cmyshare.java1;

import java.util.List;

/**
 * @author cmy
 * @create 2019 上午 11:50
 *
 * DAO:data(base) access object
 * 泛型类、泛型接口、泛型方法的使用
 */
public class DAO<T> {//表的共性操作的DAO

    //添加一条记录
    public void add(T t){

    }

    //删除一条记录
    public boolean remove(int index){

        return false;
    }

    //修改一条记录
    public void update(int index,T t){

    }

    //查询一条记录
    public T getIndex(int index){

        return null;
    }

    //查询多条记录
    public List<T> getForList(int index){

        return null;
    }

    //泛型方法
    //举例：获取表中一共有多少条记录Long？获取最大的员工入职时间Date？
    public <E> E getValue(){
        return null;
    }

}
