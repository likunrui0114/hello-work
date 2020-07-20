package edu.lkr.service;

import java.util.List;

import edu.lkr.pojo.User;

public interface UserService {
	void save(User user);

	List<User> getUsers();
}
