package xyz.etesh.lambdatest;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/8/1 17:23
 * @desc TODO
 */
public class Demo01Runnable {
    public static void startThread(Runnable run) {
        new Thread(run).start();
    }

    public static void main(String[] args) {
        startThread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "-->" + "线程启动了");
            }
        });

        startThread(() -> System.out.println(Thread.currentThread().getName() + "-->" + "线程启动了"));

    }
}
