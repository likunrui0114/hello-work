package xyz.etesh.test0730.file;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/31 15:25
 * @desc 过滤器
 */
public class Demo01Filter {
    public static void main(String[] args) {
        File file = new File("D:\\java大厂\\java一");
        getAllFile(file);

    }

    public static void getAllFile(File file) {
        File[] files = file.listFiles(new FileFilterImpl());
        /*
            构造方法遍历文件/文件夹封装成为file对象
            调用accept
         */
        for (File f : files) {
            if (f.isDirectory()) {
                getAllFile(f);
            } else {
                System.out.println(f);
            }
        }
    }

    public static void getAllFile1(File file) {
        //1
        File[] files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory() || pathname.getName().toLowerCase().endsWith(".java");
            }
        });
        //2
        File[] f1 = file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {

                return new File(dir, name).isDirectory() || name.toLowerCase().endsWith(".java");
            }
        });
        //3
        File[] f2 = file.listFiles((dir, name) ->
                new File(dir, name).isDirectory() || name.toLowerCase().endsWith(".java")
        );
        //4
        File[] f4 = file.listFiles((pathname) ->
                pathname.isDirectory() || pathname.getName().toLowerCase().endsWith(".java")
        );
        /*
            构造方法遍历文件/文件夹封装成为file对象
            调用accept
         */
        for (File f : files) {
            if (f.isDirectory()) {
                getAllFile(f);
            } else {
                System.out.println(f);
            }
        }
    }
}
