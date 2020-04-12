package com.edu.Constructors;

import com.edu.Class.test_1;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Constructors {
    @Test
    /**
     * 获得无参数构造方法
     */
    public void demo1() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // 获得类的字节码文件对应
        Class class_1 = Class.forName("com.edu.Class.test_1");
        Constructor c = class_1.getConstructor();
        // 相当于 test_1 test = new test_1()
        test_1 test = (test_1) c.newInstance();
        test.st();
    }

    @Test
    /**
     * 获得有参数构造方法
     */
    public void demo2() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // 获得类的字节码文件对应
        Class class_1 = Class.forName("com.edu.Class.test_1");
        Constructor c = class_1.getConstructor(String.class,Integer.class);
        // 相当于 test_1 test = new test_1(name,age)
        test_1 test = (test_1) c.newInstance("苏苏",20);
        System.out.println(test);
    }


}
