package xyz.etesh.test0730.inputstream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/31 16:51
 * @desc TODO
 */
public class Demo01InputStream {
    /*
    字节输入流的超类
    read();
     */
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("./b.txt");
        int a = 0;
        //每次读取一个字节
        while ((a = fis.read()) != -1) {
            System.out.println((char) a);
        }
        fis.close();
        //读取多个字节
        FileInputStream fis1 = new FileInputStream("./b.txt");
        byte[] bytes = new byte[1024];
        //len 有效字节个数
        int len = fis1.read(bytes);
        System.out.println(new String(bytes));

        fis1.close();

    }
}
