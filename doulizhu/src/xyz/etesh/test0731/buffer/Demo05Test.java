package xyz.etesh.test0731.buffer;

import java.io.*;
import java.util.HashMap;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/31 20:50
 * @desc TODO
 */
public class Demo05Test {

    public static void main(String[] args) throws IOException {
        HashMap<String, String> map = new HashMap<>();

        BufferedReader br = new BufferedReader(new FileReader("string"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("string"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] array = line.split("\\.");
            map.put(array[0], array[1]);
        }
        for (String key : map.keySet()) {
            String value = map.get(key);
            line = key + "." + value;
            bw.write(line);
            bw.newLine();
        }
        bw.close();
        br.close();
    }


}
