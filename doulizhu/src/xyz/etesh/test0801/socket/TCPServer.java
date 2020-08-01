package xyz.etesh.test0801.socket;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/8/1 10:48
 * @desc TODO
 */
public class TCPServer {
    /*
    文件上传服务端
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);


        Socket client = serverSocket.accept();
        InputStream is = client.getInputStream();
        File file = new File("path");
        if (!file.exists()) {
            file.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file + "\\1.jpg");
        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len = is.read(bytes)) != -1) {
            fileOutputStream.write(bytes, 0, len);
        }
        client.getOutputStream().write("上传成功".getBytes());
        fileOutputStream.close();
        client.close();
        serverSocket.close();

    }


}
