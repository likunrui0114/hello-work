package xyz.etesh.masterslave.enu;

import lombok.Getter;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/21 8:37
 * @desc TODO
 */
@Getter
public enum DynamicDataSourceEnum {
    MASTER("master"),
    SLAVE("slave");
    private String dataSourceName;

    DynamicDataSourceEnum(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }
}
