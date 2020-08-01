package xyz.etesh.test0730.lambda2;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/30 16:04
 * @desc TODO
 */
public class Demo01Calculator {
    public static void main(String[] args) {
        invokeCalc(10, 20, new Calculator() {
            @Override
            public int code(int a, int b) {
                return a + b;
            }
        });

        invokeCalc(110, 220, (int a, int b) -> {
            return a + b;
        });

        invokeCalc(110, 220, (a, b) ->
                a + b
        );
    }

    /*
    使用lambda必须有接口，且接口中有一个抽象方法。
    上下文推断，方法的类型必须是lambda对应的接口类型，
     */
    public static void invokeCalc(int a, int b, Calculator c) {
        int sum = c.code(a, b);
        System.out.println(sum);
    }
}
