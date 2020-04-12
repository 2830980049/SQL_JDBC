package com.edu.Methods;

import com.edu.Class.test_1;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

public class Methods {
    @Test
    /**
     * 测试公有方法
     */
    public void demo1()throws Exception{
        Class class_1 = Class.forName("com.edu.Class.test_1");
        //实例化
        test_1 test = (test_1)class_1.newInstance();
        //获得公有方法
        Method method = class_1.getMethod("st");
        //执行方法
        method.invoke(test); //test.eat()
    }

    @Test
    /**
     * 测试私有方法
     */
    public void demo2()throws Exception{
        Class class_1 = Class.forName("com.edu.Class.test_1");
        //实例化
        test_1 test = (test_1)class_1.newInstance();
        //获得私有方法
        Method method = class_1.getDeclaredMethod("no");
        //设置私有属性访问权限
        method.setAccessible(true);
        //执行方法
        method.invoke(test); //test.eat()
    }

    @Test
    /**
     * 测试带参私有方法
     */
    public void demo3()throws Exception{
        Class class_1 = Class.forName("com.edu.Class.test_1");
        //实例化
        test_1 test = (test_1)class_1.newInstance();
        //获得私有方法
        Method method = class_1.getDeclaredMethod("names",String.class);
        //设置私有属性访问权限
        method.setAccessible(true);
        //执行方法
        Object obj = method.invoke(test,"苏酒儿"); //test.eat()
        System.out.println(obj);
    }
}


