package xyz.etesh.test0729;

import com.sun.org.apache.xpath.internal.functions.FuncNamespace;

import java.util.Objects;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/29 20:06
 * @desc Objects.requireNonNull 使用
 */
public class ObjectNull {

    public static void main(String[] args) {
//        method(null);
        int b = 0;
        int a = 0;
        a = method1(b);
        System.out.println(a);
    }

    public static void method(Object o) {
      /*  if (o == null) {
            throw new NullPointerException("传递的对象值为空");
        }*/
//        Object object1 = Objects.requireNonNull(o);
        Object object2 = Objects.requireNonNull(o, "传递的对象为空");

    }

    public static int method1(int o) {
        try {
            o = 9;
            return o;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            o = 8;
            return o;
        }
    }
    /*
    有finally则必返回finally中的语句

    父类抛出异常，子类可以抛出父类异常的子类，或者不抛出异常。
    父类没有抛出异常，子类也不可以抛出异常。

     */
}
