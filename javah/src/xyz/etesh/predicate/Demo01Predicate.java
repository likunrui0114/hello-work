package xyz.etesh.predicate;

import java.util.function.Predicate;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/8/1 18:19
 * @desc TODO
 */
public class Demo01Predicate {
    public static boolean checkString(String str, Predicate<String> pre) {
        return pre.test(str);
    }

    public static void main(String[] args) {
        String s = "abcde";

        boolean flag = checkString(s, (str) -> {
            return str.length() > 5;
        });
        System.out.println(flag);
    }
}
