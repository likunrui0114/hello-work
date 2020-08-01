package xyz.etesh.functioninterface;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/8/1 11:53
 * @desc TODO
 */
@FunctionalInterface
public interface FunctionInterface {
    /*
    函数式接口，只能有有个抽象方法接口。
    当然也可以包含其他的方法（默认 静态 私有）
     */
    public abstract void method1();

    
}
