package com.cmyshare.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author cmy
 * @Date 2025/10/27 11:01
 * @desc
 * 2.3 什么是下界？—— <? super T>
 * ✅ 定义
 * <? super T> 表示下界通配符，类型参数必须是 T 或 T 的父类。
 *
 * ? super Cat：可以是 Cat、Animal、Object。
 * ✅ 特点
 * 适合“写”操作（Consumer）
 * 读取时只能转为 Object
 * 🧪 案例：安全写入，读取受限
 */
public class DemoSuperT {

    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();

        // 下界通配符
        List<? super Cat> cats = animals; // List<Animal> 是 Cat 的“上界”

        // ✅ 可以写入 Cat 及其子类
        cats.add(new Cat());
        cats.add(new PersianCat());

        // ❌ 不能写入父类
        // cats.add(new Animal()); // ❌ 错误！Animal 不一定是 Cat

        // ⚠️ 读取时，只能保证是 Object
        Object obj = cats.get(0);
        System.out.println("读取到对象: " + obj);

        // 如果需要调用 Animal 方法，必须强转（不安全）
        // Animal a = (Animal) obj; // 需要 instanceof 判断

        System.out.println("下界通配符演示完成。");
    }

}
