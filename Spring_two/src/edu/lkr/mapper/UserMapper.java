package edu.lkr.mapper;

import edu.lkr.pojo.User;

public interface UserMapper {
	void insertUser(User user);

	User getUser(Integer id);
}
