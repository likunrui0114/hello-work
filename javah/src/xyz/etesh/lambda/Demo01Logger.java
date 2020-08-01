package xyz.etesh.lambda;

import java.lang.reflect.Member;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/8/1 17:10
 * @desc TODO
 */
public class Demo01Logger {
    /*
    根据日志的级别显示日志信息
     */
    public static void showLog(int level, String message) {
        if (level == 1) {
            System.out.println(message);
        }
    }

    public static void main(String[] args) {
        String msg1 = "hello";
        String msg2 = "word";
        String msg3 = "java";

        showLog(1, msg1 + msg2 + msg3);
    }
}
