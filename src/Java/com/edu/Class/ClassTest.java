package com.edu.Class;

import org.junit.jupiter.api.Test;

public class ClassTest {
    @Test
    /**
     * 获得Class对象
     * 1. 通过类目.class
     * 2. 对象.getClass()
     * 3. Class.forName()
     */
    public void demo1() throws ClassNotFoundException {
        // 通过类目.class
        Class class_1 = test_1.class;

        // 通过对象.getClass()
        test_1 test = new test_1();
        Class class_2 = test.getClass();

        // Class.forName() 常用
        Class.forName("com.edu.Class.test_1");

    }
}
