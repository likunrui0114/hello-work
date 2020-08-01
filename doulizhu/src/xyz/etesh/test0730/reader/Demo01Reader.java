package xyz.etesh.test0730.reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/31 17:15
 * @desc TODO
 */
public class Demo01Reader {
    /*
    字符流 Reader超类

     */
    public static void main(String[] args) throws IOException {

        Reader fileReader = new FileReader("./b.txt");
        int a = 0;
        while ((a = fileReader.read()) != -1) {
            System.out.println((char) a);
        }
        fileReader.close();


        char[] ch = new char[1024];
        int len = fileReader.read(ch);
    }

}
