package xyz.etesh.test0731.properties;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/31 18:26
 * @desc TODO
 */
public class Demo01Properties {
    /*
    map hashtable 子类
    和io相结合的集合

    Properties store 吧集合中的零时数据，持久化到硬盘
    Properties load 吧硬盘中的数据读取到集合中使用
    键和值都是string
     */
    public static void main(String[] args) throws IOException {
        method();
    }

    public static void method() throws IOException {
        Properties p = new Properties();
        /*
        setProperties put方法 string
        getProperties get方法 获取
        StringPropertiesNames(); 相当于keySet
         */
        p.setProperty("赵丽颖", "168");
        p.setProperty("迪丽热巴", "165");
        p.setProperty("古力娜扎", "178");

        Set<String> keys = p.stringPropertyNames();
        for (String key : keys) {
            System.out.println(p.getProperty(key));
        }
        /*
        String comments不能为中文
        store(OutPutStream out,String comments);OutPutStream字节输出流不能中文
        store(Writer writer,String comments);Writer可以中文
        */
        FileWriter fw = new FileWriter("./b.txt", true);
        p.store(fw, "save data");
        fw.close();
    }

    public static void method1() throws IOException {

        Properties p = new Properties();
        /*
        load(InputStream is);字节
        load(Reader reader);字符
        读取保存键值对的文件，默认链接符号为等号或空格，井号代表注释，不会被读取
         */
    }
}
