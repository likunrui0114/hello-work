package xyz.etesh.test0731.transfer;

import java.io.*;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/31 21:13
 * @desc TODO
 */
public class Demo01OutputStreamWriter {
    /*
    字符变为字节  编码
    OutputStreamWriter(OutputStream os);
    OutputStreamWriter(OutputStream os,String charsetName);
     */
    public static void main(String[] args) throws IOException {
        writeUTF8();
    }

    private static void writeUTF8() throws IOException {
        //默认utf-8格式编码
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("string"), "utf-8");
        osw.write("你好");
        osw.flush();
        osw.close();
    }

    private static void read() throws IOException {
        /*
        InputStreamReader(InputStream is);
        InputStreamReader(InputStream is,String charsetName);
         */
        InputStreamReader isr = new InputStreamReader(new FileInputStream("string"), "utf-8");
        int len = 0;
        while ((len = isr.read()) != -1) {
            System.out.println((char) len);
        }
        isr.close();
    }
}