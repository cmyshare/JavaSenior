package com.cmyshare.generic;

import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @Author cmy
 * @Date 2025/10/27 10:49
 * @desc
 * 2.2 什么是上界？—— <? extends T>
 * ✅ 定义
 * <? extends T> 表示上界通配符，类型参数必须是 T 或 T 的子类。
 *
 * ? extends Animal：可以是 Animal、Cat、PersianCat 等。
 * ✅ 特点
 * 适合“读”操作（Producer）
 * 不能写入（除 null 外），因为具体类型未知。
 * 🧪 案例：安全读取，禁止写入
 */
public class DemoExtendsT {
    // ✅ 使用 ? extends Animal，可以接收 Animal 及其所有子类的列表
    public static void feedAnimals(List<? extends Animal> animals) {
        for (Animal animal : animals) {
            animal.eat(); // 安全读取为 Animal
        }
    }

    public static void main(String[] args) {
        List<Cat> cats = Arrays.asList(new Cat(), new PersianCat());
        List<Animal> animals = Arrays.asList(new Animal(), new Cat());

        feedAnimals(cats);     // ✅ 成功！
        feedAnimals(animals);  // ✅ 成功！

        System.out.println("所有动物都喂好了！");
    }
}
