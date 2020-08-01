package xyz.etesh.test0730.lambda;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/30 15:37
 * @desc TODO
 */
public class RunnableImpl implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
