package xyz.etesh.test0730;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/30 15:25
 * @desc TODO
 */
public class Demo01ThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new RunnableImpl());//pool-1-thread-1
        executorService.submit(new RunnableImpl());//pool-1-thread-1
        executorService.submit(new RunnableImpl());//pool-1-thread-2

    }
}
