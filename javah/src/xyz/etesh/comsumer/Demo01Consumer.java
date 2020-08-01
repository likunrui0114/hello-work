package xyz.etesh.comsumer;

import java.util.function.Consumer;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/8/1 17:55
 * @desc TODO
 */
public class Demo01Consumer {
    public static void method1(String name, Consumer<String> con) {
        con.accept(name);
    }

    public static void main(String[] args) {
        method1("likunrui", (String name) -> {
            System.out.println(name);
            String reName = new StringBuilder(name).reverse().toString();
            System.out.println(reName);
        });
    }
}
