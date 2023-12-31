package com.cmyshare.java4;

import org.junit.Test;

import java.util.Objects;
import java.util.Optional;

/**
 * Optional类：为了在程序中避免出现空指针异常而创建的。
 * 常用的方法：ofNullable(T t)、orElse(T t)
 */
public class OptionalTest {

    /**
     * Optional.of(T t) : 创建一个 Optional 实例，t必须非空；
     * Optional.empty() : 创建一个空的 Optional实例
     * Optional.ofNullable(T t)：t可以为null
     */
    @Test
    public void test1() {
        Girl girl = new Girl();
        //girl = null;
        //of(T t):保证t是非空的
        Optional<Girl> optionalGirl = Optional.of(girl);
    }
    @Test
    public void test2() {
        Girl girl = new Girl();
        girl = null;
        //ofNullable(T t)：t可以为null
        Optional<Girl> optionalGirl = Optional.ofNullable(girl);
        System.out.println(optionalGirl);

        //orElse(T t1):如果单前的Optional内部封装的t是非空的，则返回内部的t
        //如果内部的t是空的，则返回orElse()方法中的参数t1.
        Girl girl1 = optionalGirl.orElse(new Girl("赵丽颖"));
        System.out.println(girl1);
    }


    /**
     * Optional使用前
     */
    //优化前getGirlName
    public String getGirlName(Boy boy) {
        return boy.getGirl().getName();
    }
    @Test
    public void test3() {
        Boy boy = new Boy();
        boy = null;
        String girlName = getGirlName1(boy);
        System.out.println(girlName);

    }
    //优化以后的getGirlName():
    public String getGirlName1(Boy boy) {
        if (boy != null) {
            Girl girl = boy.getGirl();
            if (girl != null) {
                return girl.getName();
            }
        }
        return null;
    }

    @Test
    public void test4() {
        Boy boy = new Boy();
        boy = null;
        String girlName = getGirlName1(boy);
        System.out.println(girlName);

    }

    /**
     * 使用Optional类的getGirlName()
     */
    public String getGirlName2(Boy boy) {
        //Boy包装到Optional
        Optional<Boy> boyOptional = Optional.ofNullable(boy);
        //此时的boy1一定非空，如果为空加入备胎
        Boy boy1 = boyOptional.orElse(new Boy(new Girl("迪丽热巴")));
        Girl girl = boy1.getGirl();

        //Girl包装到Optional，t可以为null
        Optional<Girl> girlOptional = Optional.ofNullable(girl);
        //girl1一定非空，如果为空加入备胎
        Girl girl1 = girlOptional.orElse(new Girl("古力娜扎"));
        return "boy:"+Objects.nonNull(boy) + "\t girl:"+girl1.getName();
    }
    @Test
    public void test5() {
        //Boy为空
        Boy boy = null;
        //Girl为空
        //boy = new Boy();
        //Girl不为空
        //boy = new Boy(new Girl("苍老师"));
        String girlName = getGirlName2(boy);
        System.out.println(girlName);
    }
}
