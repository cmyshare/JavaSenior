package com.cmyshare.java1;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * 自定义注解
 */

//修饰范围：类、双属性、方法、范围、构造器、局部变量
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
//生命周期
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    //定义属性
    String value() default "hello";
}
