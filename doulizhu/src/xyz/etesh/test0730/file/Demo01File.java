package xyz.etesh.test0730.file;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/30 16:20
 * @desc 文件/文件夹操作
 */
public class Demo01File {
    public static void main(String[] args) {
        /*
        file
        directory
        path
         */
        //路径分隔符
        String pathSeparator = File.pathSeparator;
        System.out.println(pathSeparator);
        //文件分隔符
        String separator = File.separator;
        System.out.println(separator); // window: \  linux: /


        /*
        父子路径
        public File(String parent, String child)

        public File(File parent, String child)
         */

    }

    public static void method1() {
        File file = new File("D:\\java大厂\\java一\\01.JavaSE Java语言基础+进阶\\03.Java语言高级\\06-File类与IO流\\01 File类");
        file.getAbsoluteFile();//绝对路径
        file.getPath();//相对路径
        file.toString();//return getPath();
        file.getName();//构造方法传递的文件或目录的名字，结尾
        file.length();//文件的大小,Byte单位,文件夹不能计算。


        file.isDirectory();//是目录？
        file.exists();//是否存在
        file.isFile();//是否为文件

        try {
            //创建文件的目录和名称在构造方法中给出，返回值为boolean，只能创建文件不能创建文件夹，创建的文件路径必须存在
            file.createNewFile();//创建文件
        } catch (IOException e) {
            e.printStackTrace();
        }
        file.mkdir();//创建文件夹
        file.mkdirs();//创建文件夹（单级多级 ？ 111\\222\\333）

        file.delete();//删除文件或文件夹，成功true  文件夹中有内容返回false，路径不存在返回false，直接在硬盘删除不走回收站，谨慎

    }
}
