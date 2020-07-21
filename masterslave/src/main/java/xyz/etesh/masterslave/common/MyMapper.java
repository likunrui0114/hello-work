package xyz.etesh.masterslave.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/21 9:04
 * @desc TODO
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
    // TODO
}
