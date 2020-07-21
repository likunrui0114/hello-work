package xyz.etesh.masterslave.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.etesh.masterslave.domain.User;
import xyz.etesh.masterslave.dynamicdatasource.DataSourceSelector;
import xyz.etesh.masterslave.enu.DynamicDataSourceEnum;
import xyz.etesh.masterslave.mapper.UserMapper;

import java.util.List;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/21 9:20
 * @desc TODO
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @DataSourceSelector(value = DynamicDataSourceEnum.SLAVE)
    public List<User> listUser() {
        List<User> users = userMapper.selectAll();
        return users;
    }

    @DataSourceSelector(value = DynamicDataSourceEnum.MASTER)
    public int update() {
        User user = new User();
        user.setUserId(Long.parseLong("1196978513958141952"));
        user.setUserName("修改后的名字2");
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @DataSourceSelector(value = DynamicDataSourceEnum.SLAVE)
    public User find() {
        User user = new User();
        user.setUserId(Long.parseLong("1196978513958141952"));
        return userMapper.selectByPrimaryKey(user);
    }
}
