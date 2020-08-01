package xyz.etesh.doulizhu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/28 20:51
 * @desc TODO
 */
public class DouDiZhu {
    public static void main(String[] args) {
        ArrayList<String> poker = new ArrayList<>();
        String[] colors = {"♠", "♥", "♣", "♦"};
        String[] numbers = {"2", "A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3"};
        poker.add("大王");
        poker.add("小王");
        for (String number : numbers) {
            for (String color : colors) {
                poker.add(color + number);
            }
        }
        Collections.shuffle(poker);

        ArrayList<String> poker1 = new ArrayList<>();
        ArrayList<String> poker2 = new ArrayList<>();
        ArrayList<String> poker3 = new ArrayList<>();
        ArrayList<String> dipai = new ArrayList<>();
        for (int i = 0; i < poker.size(); i++) {
            String s = poker.get(i);
            if (i >= 51) {
                dipai.add(s);
            } else if (i % 3 == 0) {
                poker1.add(s);
            } else if (i % 3 == 1) {
                poker2.add(s);
            } else if (i % 3 == 2) {
                poker3.add(s);
            }
        }
        System.out.println(poker1);
        System.out.println(poker2);
        System.out.println(poker3);
        System.out.println(dipai);
    }
}
