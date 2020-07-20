package edu.lkr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.lkr.pojo.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet arg0, int arg1) throws SQLException {
		User user = new User();
		user.setId(arg0.getInt("id"));
		user.setAge(arg0.getInt("age"));
		user.setName(arg0.getString("name"));
		user.setSex(arg0.getString("sex"));
		return user;
	}

}
