package xyz.etesh.test0731.buffer;

import java.io.*;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/31 20:41
 * @desc TODO
 */
public class Demo01BufferedWriter {
    /*
    写入一个行分隔符
     */
    public static void main(String[] args) throws IOException {
        method1();
    }

    private static void method1() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("string", true));
        bw.write("测试");
        bw.newLine();
        bw.flush();
        bw.close();
        System.out.println();


        BufferedReader br = new BufferedReader(new FileReader("String"));
        String s = null;
        while ((s = br.readLine()) != null) {
            System.out.println(s);
        }
    }


}
