package xyz.etesh.test0730.lambda;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/30 15:54
 * @desc TODO
 */
public class Demo01Cook {
    public static void main(String[] args) {
        invokeCook(new Cook() {
            @Override
            public void makeFood() {
                System.out.println("做好吃的！");
            }
        });
        //使用lambda表达式
        invokeCook(() -> {
            System.out.println("做好吃的！");
        });
        invokeCook(() ->
                System.out.println("做好吃的！")
        );
    }

    public static void invokeCook(Cook cook) {
        cook.makeFood();
    }
}
