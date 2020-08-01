package xyz.etesh.lambdatest;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/8/1 17:27
 * @desc TODO
 */
public class Demo02Comparator {
    public static Comparator<String> getComparator() {
       /* return new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        };*/

        //lambda优化
        return (o1, o2) -> o2.length() - o1.length();

    }


    public static void main(String[] args) {
        String[] array = {"aaa", "bb", "cccc"};
        System.out.println(Arrays.toString(array));
        Arrays.sort(array, getComparator());
    }
}
