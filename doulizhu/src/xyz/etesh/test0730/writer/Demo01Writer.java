package xyz.etesh.test0730.writer;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/31 18:07
 * @desc TODO
 */
public class Demo01Writer {
    /*
    文件中写字符
    FileWriter extend OutputStreamWriter extend Writer
     */
    public static void main(String[] args) throws IOException {

        FileWriter fw = new FileWriter("./b.txt", true);
        fw.write("我爱你");
        fw.flush();
        fw.write(97);
        fw.close();

        /*
        flush:刷新缓冲区，可以继续使用
        close:刷新缓冲区，不能继续使用

         */
        FileWriter fw1 = new FileWriter("./b.txt", true);
        char[] ch = {'a', 'a', 'b', 'a'};
        fw1.write(ch);
        fw1.flush();
        fw1.write(ch, 1, 3);
        fw1.close();

    }

    public static void test() {
        FileWriter fw1 = null;
        try {
            fw1 = new FileWriter("./b.txt", true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw1 != null) {
                try {
                    fw1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void test1() {
        //jdk7新特性
        try (
                FileWriter fw1 = new FileWriter("./b.txt", true);
        ) {
            fw1.write("卧槽");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
