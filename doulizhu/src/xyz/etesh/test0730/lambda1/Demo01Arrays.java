package xyz.etesh.test0730.lambda1;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/30 15:58
 * @desc TODO
 */
public class Demo01Arrays {
    public static void main(String[] args) {
        Person[] arr = {
                new Person("地理热吧", 18),
                new Person("迪丽热巴", 19),
                new Person("鼓励那种", 17)
        };
        Arrays.sort(arr, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        for (Person person : arr) {
            System.out.println(person);
        }
        Arrays.sort(arr, (Person o1, Person o2) -> {
            return o1.getAge() - o2.getAge();
        });
        /*
        Person类型省略，大括号 return省略 一齐
         */
        Arrays.sort(arr, (o1, o2) ->
                o1.getAge() - o2.getAge()
        );
        for (Person person : arr) {
            System.out.println(person);
        }
    }
}
