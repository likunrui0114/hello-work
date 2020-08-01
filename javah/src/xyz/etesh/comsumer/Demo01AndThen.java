package xyz.etesh.comsumer;

import java.util.function.Consumer;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/8/1 18:02
 * @desc TODO
 */
public class Demo01AndThen {
    /*
    andThen连接两个consumer接口
     */
    public static void method(String s, Consumer<String> con1, Consumer<String> con2) {
//        con1.accept(s);
//        con2.accept(s);
        con1.andThen(con2).accept(s);
    }


    public static void main(String[] args) {
        method("likunrui", (s) -> {
            System.out.println(s.toUpperCase());
        }, (s) -> {
            System.out.println(s.toLowerCase());
        });
    }
}
