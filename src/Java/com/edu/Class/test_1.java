package com.edu.Class;

public class test_1 {
    private String name;
    private Integer age;
    public String sex;


    public test_1(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
    public test_1() {
    }

    public void st(){
        System.out.println("s");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private void no(){
        System.out.println("no");
    }

    private String names(String name){
        return name;
    }

    @Override
    public String toString() {
        return "test_1{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
