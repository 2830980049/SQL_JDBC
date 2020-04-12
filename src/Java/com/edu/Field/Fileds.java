package com.edu.Field;

import com.edu.Class.test_1;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

public class Fileds {
    @Test
    /** 测试公有属性
     *
     */
    public void demo1() throws Exception {
        Class class_1 = Class.forName("com.edu.Class.test_1");
        // 获得公有属性
        Field field = class_1.getField("sex");
        // 操作属性 p.name = ""
        test_1 test =  (test_1)class_1.newInstance();
        field.set(test,"女");

        Object obj = field.get(test);
        System.out.println(obj);
    }

    @Test
    /** 测试私有属性
     *
     */
    public void demo2() throws Exception {
        Class class_1 = Class.forName("com.edu.Class.test_1");
        // 获得私有属性
        Field field = class_1.getDeclaredField("name");
        // 操作属性 p.name = ""
        test_1 test =  (test_1)class_1.newInstance();
        //私有属性，需要设置一个可访问权限
        field.setAccessible(true);

        field.set(test,"苏苏");
        Object obj = field.get(test);
        System.out.println(obj);
    }
}
