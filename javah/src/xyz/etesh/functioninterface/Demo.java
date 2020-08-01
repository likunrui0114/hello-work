package xyz.etesh.functioninterface;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/8/1 17:00
 * @desc TODO
 */
public class Demo {
    public static void show(FunctionInterface myInter) {
        myInter.method1();
    }

    public static void main(String[] args) {
        //实现类
        show(new FunctionInterfaceImpl());
        //接口
        show(new FunctionInterface() {
            @Override
            public void method1() {
                System.out.println("我是函数式接口编程2");
            }
        });
        /*
        lambda
        类似匿名内部类，但不会产生字节码文件，不会生内部类，效率高
         */
        show(() -> {
            System.out.println("我是函数式接口编程3");
        });
    }
}
