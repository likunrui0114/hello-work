package xyz.etesh.test0731.objectstream;

import java.io.Serializable;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/31 21:30
 * @desc TODO
 */
public class Person implements Serializable {
    /*
    被static修饰的成员变量不能被序列化，序列化的都是对象。
    被transient修饰的成员变量不能被序列化，但不是static类型的
     */
    private static final long serialVersionUID = -3995898575788151711L;

    private String name;
    private int age;

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
