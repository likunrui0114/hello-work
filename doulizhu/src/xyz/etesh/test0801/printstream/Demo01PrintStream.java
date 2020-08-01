package xyz.etesh.test0801.printstream;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/8/1 10:01
 * @desc TODO
 */
public class Demo01PrintStream {
    /*
        printStream
            只负责数据的输出，不负责数据的读取
            不会抛出IOException
            有特殊的方法
    printStream(File file);
    printStream(OutputStream os);
    printStream(string fileName);
    printStream继承了OutputStream
     */
    public static void main(String[] args) throws FileNotFoundException {
        PrintStream ps = new PrintStream("stringName");
        ps.write(97);
        ps.print(97);
        ps.print(true);
        ps.close();
        method01();
    }

    private static void method01() throws FileNotFoundException {
        System.out.println("控制台输出");
        PrintStream ps = new PrintStream("stringName");
        System.setOut(ps);
        System.out.println("在目标地址输出");
    }
}
