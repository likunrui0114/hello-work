package xyz.etesh.masterslave.dynamicdatasource;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/21 9:14
 * @desc TODO
 */
public class DataSourceContextHolder {
    private static final ThreadLocal<String> DYNAMIC_DATASOURCE_CONTEXT = new ThreadLocal<>();

    public static void set(String datasourceType) {
        DYNAMIC_DATASOURCE_CONTEXT.set(datasourceType);
    }

    public static String get() {
        return DYNAMIC_DATASOURCE_CONTEXT.get();
    }

    public static void clear() {
        DYNAMIC_DATASOURCE_CONTEXT.remove();
    }

}
