package com.cmyshare.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author cmy
 * @Date 2025/10/27 11:10
 * @desc
 * 2.5 PECS 原则（Producer-Extends, Consumer-Super）
 * 这是 Joshua Bloch 在《Effective Java》中提出的黄金法则。
 *
 * ✅ 原则
 * 如果一个集合主要用于“生产”数据（读取）→ 使用 ? extends T
 * 如果一个集合主要用于“消费”数据（写入）→ 使用 ? super T
 * 🧪 案例：实现一个通用的“转移”方法
 *
 * ✅ 疑问：PECS 生产者用 extends，消费者用 super。生产者用 extends不应该是写入吗？消费者用 super不应该是读取吗？
 * PECS = Producer → ? extends T (读) ， Consumer → ? super T (写)
 * 角色	操作	通配符	原因
 * Producer（生产者）	你从它读数据 → 它“生产”数据给你	? extends T	能安全地读出 T 类型的对象
 * Consumer（消费者）	你往它写数据 → 它“消费”你给的数据	? super T	能安全地接收 T 类型的对象
 *
 * ✅ PECS 总结：
 * 场景	通配符	示例
 * 只读（Producer）	? extends T	List<? extends Number>
 * 只写（Consumer）	? super T	List<? super Integer>
 * 既读又写	不使用通配符	List<T> *
 * ✅ 总结
 * 概念	说明	适用场景
 * ? extends T	上界，T 或其子类	读取，如遍历、获取元素
 * ? super T	下界，T 或其父类	写入，如添加元素
 * PECS	生产者用 extends，消费者用 super	设计泛型方法时的指导原则
 * 副作用	无法同时读写，类型信息丢失	需权衡灵活性与安全性
 * 通过合理使用通配符和 PECS 原则，您可以写出类型安全、灵活高效的泛型代码，充分发挥 Java 泛型的强大威力。
 */
public class DemoPecs {
    /**
     * 将 src 中的元素转移到 dest
     * src 是“生产者” → ? extends T 读取 src 中的元素
     * dest 是“消费者” → ? super T 写入 dest 中
     */
    public static <T> void transfer(
            List<? extends T> src,
            List<? super T> dest) {
        for (T item : src) {
            dest.add(item); // ✅ 安全：dest 能接受 T 类型
        }
    }

    public static void main(String[] args) {
        List<Cat> cats = new ArrayList<>();
        cats.add(new Cat());
        cats.add(new PersianCat());

        List<Animal> animals = new ArrayList<>();

        // ✅ 完美工作！
        transfer(cats, animals);

        // 验证转移结果
        for (Animal animal : animals) {
            animal.eat();
        }
    }
}
