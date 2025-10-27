package com.cmyshare.generic;

import java.util.List;

/**
 * @version 1.0
 * @Author cmy
 * @Date 2025/10/27 11:02
 * @desc
 * 2.4 上下界通配符的副作用
 * ⚠️ 副作用1：无法同时读写
 * ✅ 原因
 * 因为上界通配符 <? extends T> 只能用于读取 T 类型的对象，不能用于写入。
 * 而下界通配符 <? super T> 只能用于写入 T 类型的对象，不能用于读取。
 *
 * ⚠️ 副作用2：类型信息丢失
 * ? extends T：只知道是 T 的子类，但不知道具体是哪个。
 * ? super T：只知道是 T 的父类，但不知道具体是哪个。
 */
public class DemoLimitation {

    /**
     * 2.4.1 无法同时读写的副作用
     * @param animals
     */
    public static void badMethod(List<? extends Animal> animals) {
        Animal a = animals.get(0); // ✅ 读
        // animals.add(a); // ❌ 不能写
    }

    /**
     * 2.4.2 类型信息丢失的副作用
     * @param cats
     */
    public static void badMethod2(List<? super Cat> cats) {
        // Cat c = cats.get(0); // ❌ 不能直接读为 Cat
        Object o = cats.get(0); // 只能读为 Object
        cats.add(new PersianCat()); // ✅ 写
    }

}
