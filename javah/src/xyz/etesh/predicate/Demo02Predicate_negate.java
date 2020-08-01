package xyz.etesh.predicate;


import java.util.function.Predicate;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/8/1 18:24
 * @desc TODO
 */
public class Demo02Predicate_negate {
    public static boolean checkString(String s, Predicate<String> pre1) {
//        return pre1.test(s) && pre2.test(s);
        return pre1.negate().test(s);
    }

    public static void main(String[] args) {
        String s = "abcde";
        boolean flag = checkString(s, (str) -> {
            return str.length() > 5;
        });
        System.out.println(flag);
    }
}
