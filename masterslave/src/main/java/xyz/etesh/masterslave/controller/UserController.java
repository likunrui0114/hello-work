package xyz.etesh.masterslave.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.etesh.masterslave.domain.User;
import xyz.etesh.masterslave.service.UserService;

import java.util.List;

/**
 * @author likunrui
 * @version 1.0
 * @date 2020/7/21 9:06
 * @desc TODO
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public List<User> userList() {
        return userService.listUser();
    }

    @RequestMapping("/update")
    public int update() {
        return userService.update();
    }
}
