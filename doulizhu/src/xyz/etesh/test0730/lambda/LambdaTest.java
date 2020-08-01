package xyz.etesh.test0730.lambda;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/30 15:34
 * @desc TODO
 */
public class LambdaTest {
    /*
    面向对象的思想：
        做一件事情，找一个能解决这个事情的对象，调用对象的方法，完成事情
    面向函数编程：
        只要获取到结果，谁去做的，怎么做的都不重要，重视的是结果。
     */

    public static void main(String[] args) {
        //内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();
        //lambda
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        }).start();
        /*
        只有一行代码没有返回值可以省略括号
         */
        new Thread(() ->
                System.out.println(Thread.currentThread().getName())
        ).start();
        /*
        优点：省去了定义实现类
        缺点：复杂
         */
        /*
        格式：
        一些参数 ():接口中抽象方法的参数列表，没有参数，空着，有参数就写出来
        一个箭头 ->：传递给方法体
        一段代码 {}：重写接口的方法体
         */

    }


}
