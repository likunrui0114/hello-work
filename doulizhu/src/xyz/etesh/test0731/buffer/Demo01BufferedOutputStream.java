package xyz.etesh.test0731.buffer;

import java.io.*;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/31 18:51
 * @desc TODO
 */
public class Demo01BufferedOutputStream {

    /*
    BufferedOutputStream 字节缓存输出流
    BufferedOutputStream（OutputStream os,int size） 缓存区大小
     */
    public static void main(String[] args) throws IOException {
        method1();
    }

    private static void method1() throws IOException {
        //写
        BufferedOutputStream write = new BufferedOutputStream(new FileOutputStream("./b.txt", true));
        write.write("继续".getBytes());
        write.flush();
        write.close();

        //读
        BufferedInputStream reader = new BufferedInputStream(new FileInputStream("./b.txt"));
        int len = 0;
//        while ((len = reader.read()) != -1) {
//            System.out.println(len);
//        }
        byte[] bytes = new byte[1024];
        while ((len = reader.read(bytes)) != -1) {
            System.out.println(new String(bytes, 0, len));
        }
        reader.close();
    }

    /**
     * 复制文件
     *
     * @throws IOException
     */
    private static void method2() throws IOException {
        BufferedOutputStream write = new BufferedOutputStream(new FileOutputStream("string"));
        BufferedInputStream reader = new BufferedInputStream(new FileInputStream("string"));

        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len = reader.read(bytes)) != -1) {
            write.write(bytes, 0, len);
        }
        write.close();
        reader.close();
    }

}
