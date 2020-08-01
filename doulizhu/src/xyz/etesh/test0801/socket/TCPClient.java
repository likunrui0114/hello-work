package xyz.etesh.test0801.socket;

import sun.reflect.generics.scope.Scope;

import java.io.*;
import java.net.Socket;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/8/1 10:42
 * @desc TODO
 */
public class TCPClient {

    /*
    文件上传客户端
     */
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("文件");
        Socket socket = new Socket("127.0.0.1", 8888);
        OutputStream os = socket.getOutputStream();
        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len = fis.read()) != -1) {
            os.write(bytes, 0, len);
        }
        socket.shutdownOutput();
        InputStream is = socket.getInputStream();
        while ((len = is.read()) != -1) {
            System.out.println(new String(bytes, 0, len));
        }
        fis.close();
        socket.close();
    }
}
