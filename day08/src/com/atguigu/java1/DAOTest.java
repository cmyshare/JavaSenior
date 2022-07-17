package com.atguigu.java1;

import org.junit.Test;

import java.util.List;

/**
 * @author cmy
 * @create 2019 上午 11:57
 */
public class DAOTest {

    @Test
    public void test1(){
        //实例化dao接口类
        CustomerDAO dao1 = new CustomerDAO();

        dao1.add(new Customer());
        List<Customer> list = dao1.getForList(10);

        StudentDAO dao2 = new StudentDAO();
        Student student = dao2.getIndex(1);
    }
}
