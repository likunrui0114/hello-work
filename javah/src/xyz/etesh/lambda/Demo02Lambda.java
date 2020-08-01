package xyz.etesh.lambda;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/8/1 17:14
 * @desc TODO
 */
public class Demo02Lambda {

    public static void showLog(int level, MessageBuilder mb) {
        if (level == 1) {
            System.out.println(mb.builderMessage());
        }
    }

    public static void main(String[] args) {
        String msg1 = "hello";
        String msg2 = "word";
        String msg3 = "java";

        showLog(1, () -> msg1 + msg2 + msg3);

    }
}
