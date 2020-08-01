package xyz.etesh.test0730.file;

import java.io.File;
import java.io.FileFilter;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/31 15:29
 * @desc 过滤器实现类
 */
public class FileFilterImpl implements FileFilter {
    @Override
    public boolean accept(File pathname) {
        if (pathname.isDirectory()) {
            return true;
        }
        return pathname.getName().toLowerCase().endsWith(".java");
    }
}
