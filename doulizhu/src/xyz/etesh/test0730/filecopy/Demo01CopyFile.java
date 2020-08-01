package xyz.etesh.test0730.filecopy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/31 17:02
 * @desc TODO
 */
public class Demo01CopyFile {
    public static void main(String[] args) throws IOException {
        //输出流
        FileOutputStream fos = new FileOutputStream("string");
        //输入流
        FileInputStream fis = new FileInputStream("string");
        int len = 0;
        while ((len = fis.read()) != -1) {
            fos.write(len);
        }

//        byte[] bytes = new byte[1024];
//        while ((len = fis.read(bytes)) != -1) {
//            fos.write(bytes, 0, len);
//        }
        fos.close();
        fis.close();
    }

}
