package com.cmyshare.generic;

/**
 * @version 1.0
 * @Author cmy
 * @Date 2025/10/27 10:15
 * @desc 笼子
 */

public class Cage<T> {
    private T item;
    public Cage(T t) {
        item = t;
    }
    public void set(T t) {
        item = t;
    }
    public T get() {
        return item;
    }
    @Override
    public String toString() {
        return "Cage{" + "item=" + item + '}';
    }
}