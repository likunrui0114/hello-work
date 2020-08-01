package xyz.etesh.test0730.outputstream;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/31 16:10
 * @desc TODO
 */
public class Demo01OutputStream {
    /*
    OutputStream 为输出字节流的超类

    FileOutputStream;内存中的数据写入文件中
    FileOutputStream(File file);构造方法 文件
    FileOutputStream(String name);构造方法 文件路径

    创建一个FileOutputStream对象，创建一个空文件，指向空文件。

     */

    public static void main(String[] args) throws IOException {
        //写入一个字符
        FileOutputStream fos = new FileOutputStream("./a.txt");
        fos.write(97);
        fos.close();

        //写入多个字符
        FileOutputStream fos1 = new FileOutputStream("./b.txt");
        byte[] bytes = new byte[]{65, 66, 67, 68, 69};
        fos1.write(bytes);
        fos1.close();
        /*
        若写入的第一个字符为负数，那第一个字节和第二个字节组合成为一个中显示。
         */
        /*
        追加
        FileOutputStream(File file,boolean oppend);
        FileOutputStream(String file,boolean oppend);
         */

    }
}
