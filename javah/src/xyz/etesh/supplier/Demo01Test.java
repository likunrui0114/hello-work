package xyz.etesh.supplier;

import java.util.function.Supplier;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/8/1 17:42
 * @desc TODO
 */
public class Demo01Test {
    public static int getMax(Supplier<Integer> sup) {
        return sup.get();
    }


    public static void main(String[] args) {
        int[] array = {100, 0, -50, 88, 99, 33};
        int maxValue = getMax(() -> {
            int max = array[0];
            for (int i : array) {
                if (i > max) {
                    max = i;
                }
            }
            return max;
        });
        System.out.println("最大的元素" + maxValue);
    }
}
