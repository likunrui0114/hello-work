package xyz.etesh.test0731.objectstream;

import java.io.*;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/31 21:28
 * @desc TODO
 */
public class Demo01ObjectOutputStream {
    /*
        对象序列化
     */
    public static void main(String[] args) throws IOException {
        method1();
    }

    private static void method1() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./person.txt"));
        oos.writeObject(new Person("小美女", 18));
        oos.close();
    }

    private static void method2() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./person.txt"));
        Object object = ois.readObject();
        Person person = (Person) object;
    }

}
