package xyz.etesh.test0730.lambda;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/30 15:38
 * @desc TODO
 */
public class Demo01Runnable {
    public static void main(String[] args) {
        RunnableImpl runnable = new RunnableImpl();
        Thread thread = new Thread(runnable);
        thread.start();

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        new Thread(r).start();
        
    }
}
