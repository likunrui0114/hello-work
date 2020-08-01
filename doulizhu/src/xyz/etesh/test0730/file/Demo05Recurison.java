package xyz.etesh.test0730.file;

import java.io.File;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/31 15:13
 * @desc 文件搜索
 */
public class Demo05Recurison {
    public static void main(String[] args) {
        File file = new File("D:\\java大厂\\java一");
        getAllFile(file);

    }

    public static void getAllFile(File file) {
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                getAllFile(f);
            } else {
                String s = f.toString();
                s = s.toLowerCase();
                boolean b = s.endsWith(".java");
                if (f.toString().toLowerCase().endsWith(".java")) {
                    System.out.println(f);
                }

            }
        }
    }
}
