package xyz.etesh.baozi;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/30 15:12
 * @desc TODO
 */
public class Main {

    public static void main(String[] args) {
        Baozi baozi = new Baozi();
        new Baozipu(baozi).start();
        new Chihuo(baozi).start();
    }
}
