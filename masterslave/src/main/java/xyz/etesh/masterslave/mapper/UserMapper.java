package xyz.etesh.masterslave.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.etesh.masterslave.common.MyMapper;
import xyz.etesh.masterslave.domain.User;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/21 9:19
 * @desc TODO
 */
@Mapper
public interface UserMapper extends MyMapper<User> {
}
