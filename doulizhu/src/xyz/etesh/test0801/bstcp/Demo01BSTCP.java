package xyz.etesh.test0801.bstcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/8/1 11:14
 * @desc TODO
 */
public class Demo01BSTCP {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        Socket accept = serverSocket.accept();
        InputStream is = accept.getInputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = is.read(bytes)) != -1) {
            System.out.println(new String(bytes, 0, len));

        }
    }
}
