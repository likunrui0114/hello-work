package xyz.etesh.supplier;

import java.util.function.Supplier;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/8/1 17:38
 * @desc TODO
 */
public class Demo01Supplier {
    /*
    Supplier生产型接口
     */
    public static String getString(Supplier<String> sup) {
        return sup.get();
    }

    public static void main(String[] args) {
        String str = getString(() -> "abc");
        System.out.println(str);
    }
}
