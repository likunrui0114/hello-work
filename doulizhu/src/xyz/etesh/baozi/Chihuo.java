package xyz.etesh.baozi;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/30 15:09
 * @desc TODO
 */
public class Chihuo extends Thread {
    private Baozi baozi;


    public Chihuo(Baozi baozi) {
        this.baozi = baozi;
    }


    @Override
    public void run() {
        while (true) {
            synchronized (baozi) {
                if (baozi.flag == false) {
                    try {
                        baozi.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("吃货正在吃" + baozi.pi + baozi.xian + "包子");
                baozi.flag = false;

                baozi.notify();
                System.out.println("吃货已经把" + baozi.pi + baozi.xian + "包子吃完了");
                System.out.println("---------------------");
            }
        }
    }
}
