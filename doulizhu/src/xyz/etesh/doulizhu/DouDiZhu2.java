package xyz.etesh.doulizhu;

import java.util.*;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/29 17:44
 * @desc TODO
 */
public class DouDiZhu2 {
    public static void main(String[] args) {
        Map<Integer, String> puke = new HashMap<>();
        ArrayList<Integer> pokeIndex = new ArrayList<>();


//        List<String> colors = new ArrayList<>();
//        List<String> numbers = new ArrayList<>();
        String[] colors = {"♠", "♥", "♣", "♦"};
        String[] numbers = {"2", "A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3"};
        int index = 0;
        puke.put(index, "大王");
        pokeIndex.add(index);
        index++;
        puke.put(index, "小王");
        index++;
        for (String number : numbers) {
            for (String color : colors) {
                puke.put(index, color + number);
                pokeIndex.add(index);
                index++;
            }
        }
        Collections.shuffle(pokeIndex);
        ArrayList<Integer> poker1 = new ArrayList<>();
        ArrayList<Integer> poker2 = new ArrayList<>();
        ArrayList<Integer> poker3 = new ArrayList<>();
        ArrayList<Integer> dipai = new ArrayList<>();
        for (int i = 0; i < pokeIndex.size(); i++) {
            Integer in = pokeIndex.get(i);
            if (i >= 51) {
                dipai.add(in);
            } else if (i % 3 == 0) {
                poker1.add(in);
            } else if (i % 3 == 1) {
                poker2.add(in);
            } else {
                poker3.add(in);
            }
        }

        Collections.sort(poker1);
        Collections.sort(poker2);
        Collections.sort(poker3);
        Collections.sort(dipai);
        lookPoker("李德华", puke, poker1);
        lookPoker("周润发", puke, poker2);
        lookPoker("周星驰", puke, poker3);
        lookPoker("底牌", puke, dipai);
    }

    public static void lookPoker(String name, Map<Integer, String> puke, ArrayList<Integer> list) {
        System.out.print(name + ":");
        for (Integer key : list) {
            String value = puke.get(key);
            System.out.print(value + " ");
        }
        System.out.println();


    }


}
