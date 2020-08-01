package xyz.etesh.predicate;


import java.util.function.Predicate;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/8/1 18:24
 * @desc TODO
 */
public class Demo02Predicate_and {
    public static boolean checkString(String s, Predicate<String> pre1, Predicate<String> pre2) {
//        return pre1.test(s) && pre2.test(s);
        return pre1.and(pre2).test(s);
    }

    public static void main(String[] args) {
        String s = "abcdef";
        boolean flag = checkString(s, (str) -> {
            return str.length() > 5;
        }, (str) -> {
            return str.contains("a");
        });
        System.out.println(flag);
    }
}
