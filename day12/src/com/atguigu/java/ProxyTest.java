package com.atguigu.java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 反射应用：动态代理的举例
 * 特点：根据被代理类，Proxy动态地创建代理类。代理类调用接口方法，调用的是被代理类的方法。
 *
 * 动态代理重点：要想实现动态代理，需要解决的问题？
 * 问题一：如何根据加载到内存中的被代理类，动态的创建一个代理类及其对象。
 * 问题二：当通过代理类的对象调用方法a时，如何动态的去调用被代理类中的同名方法a。
 */

/**
 * 1、接口方法
 */
interface Human{

    String getBelief();

    void eat(String food);

}

/**
 * 1、被代理类
 */
class SuperMan implements Human{

    @Override
    public String getBelief() {
        return "I believe I can fly!";
    }

    @Override
    public void eat(String food) {
        System.out.println("我喜欢吃" + food);
    }
}


/**
 * AOP面向切面方法类(可舍去)
 */
class HumanUtil{
    public void method1(){
        System.out.println("====================通用方法一====================");

    }
    public void method2(){
        System.out.println("====================通用方法二====================");
    }
}

/**
 * 2、InvocationHandler调用处理程序
 * 解决问题二，当通过代理类的对象调用方法a时，如何动态的去调用被代理类中的同名方法a。
 */
class MyInvocationHandler implements InvocationHandler{
    //使用被代理类的对象进行赋值
    private Object obj;

    //绑定被代理对象方法
    public void bind(Object obj){
        this.obj = obj;
    }

    /**
     * 当通过代理类的对象，调用方法a时，就会自动的调用如下的方法：invoke()
     * 将被代理类要执行的方法a的功能就声明在invoke()中
     * @param proxy 代理类对象
     * @param method 方法
     * @param args 方法参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //实例化AOP面向切面方法类
        HumanUtil util = new HumanUtil();
        //切面方法1
        util.method1();
        //method:即为代理类对象调用的方法，此方法也就作为了被代理类对象要调用的方法，obj:被代理类的对象
        Object returnValue = method.invoke(obj,args);
        //切面方法2
        util.method2();

        //上述方法的返回值就作为当前类中的invoke()的返回值。
        return returnValue;
    }
}

/**
 * 3、代理工厂：创建代理对象
 */
class ProxyFactory{
    //解决问题一，调用此方法，返回一个代理类的对象。obj:被代理类的对象。
    public static Object getProxyInstance(Object obj){
        MyInvocationHandler handler = new MyInvocationHandler();
        //InvocationHandler绑定被代理对象
        handler.bind(obj);
        //反射下Proxy类，创建一个代理类对象。参数1类加载器、参数2实现的接口，参数3调用处理程序
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),handler);
    }
}

/**
 * 4、动态代理测试
 */
public class ProxyTest {

    public static void main(String[] args) {
        //被代理对象
        SuperMan superMan = new SuperMan();
        //调用代理工厂中getProxyInstance方法，创建代理类对象。
        Human proxyInstance = (Human) ProxyFactory.getProxyInstance(superMan);
        //代理类对象调用方法，会自动的调用被代理类中同名的方法
        String belief = proxyInstance.getBelief();
        System.out.println(belief);
        proxyInstance.eat("四川麻辣烫");

        System.out.println("*****************************");

        /**
         *  改造静态代理，加入动态代理，根据接口、被代理类对象动态创建一个代理类，
         */
        //定义被代理类对象
        NikeClothFactory nikeClothFactory = new NikeClothFactory();
        //动态创建一个代理类对象
        ClothFactory proxyClothFactory = (ClothFactory) ProxyFactory.getProxyInstance(nikeClothFactory);
        //通过代理类对象调用接口，从而调用被代理对象的方法
        proxyClothFactory.produceCloth();
    }
}
