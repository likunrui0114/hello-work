package xyz.etesh.masterslave.dynamicdatasource;

import xyz.etesh.masterslave.enu.DynamicDataSourceEnum;

import java.lang.annotation.*;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/21 9:13
 * @desc TODO
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DataSourceSelector {

    DynamicDataSourceEnum value() default DynamicDataSourceEnum.MASTER;

    boolean clear() default true;
}
