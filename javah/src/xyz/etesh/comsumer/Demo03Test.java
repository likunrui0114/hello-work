package xyz.etesh.comsumer;

import java.util.function.Consumer;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/8/1 18:10
 * @desc TODO
 */
public class Demo03Test {
    public static void printInfo(String[] array, Consumer<String> con1, Consumer<String> con2) {
        for (String message : array) {
            con1.andThen(con2).accept(message);
        }
    }

    public static void main(String[] args) {
        String[] array = {"李坤睿,男", "xxx,女"};
        printInfo(array, (message) -> {
            String name = message.split(",")[0];
            System.out.print("姓名" + name);
        }, (message) -> {
            String age = message.split(",")[1];
            System.out.print("。年龄" + age + "。\n");
        });
    }
}
