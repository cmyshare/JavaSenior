package com.cmyshare.java;

/**
 * 静态代理举例
 * 特点：代理类和被代理类在编译期间，就确定下来了。
 */

/**
 * 接口方法
 */
interface ClothFactory{
    void produceCloth();
}

/**
 * 代理类
 */
class ProxyClothFactory implements ClothFactory{
    //用被代理类对象进行实例化
    private ClothFactory factory;
    //有参构造
    public ProxyClothFactory(ClothFactory factory){
        this.factory = factory;
    }
    //实现接口
    @Override
    public void produceCloth() {
        System.out.println("代理工厂做一些准备工作");
        //调用被代理类的接口方法
        factory.produceCloth();
        System.out.println("代理工厂做一些后续的收尾工作");
    }
}

/**
 * 被代理类
 */
class NikeClothFactory implements ClothFactory{
    @Override
    public void produceCloth() {
        System.out.println("Nike工厂生产一批运动服");
    }
}

/**
 * 静态代理测试
 */
public class StaticProxyTest {
    public static void main(String[] args) {
        //创建被代理类的对象
        ClothFactory nike = new NikeClothFactory();
        //创建代理类的对象，使用有参构造
        ClothFactory proxyClothFactory = new ProxyClothFactory(nike);
        //通过代理类调用接口方法
        proxyClothFactory.produceCloth();
    }
}
