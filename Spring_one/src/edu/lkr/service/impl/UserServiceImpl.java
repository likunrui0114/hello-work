package edu.lkr.service.impl;

import java.util.List;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import edu.lkr.dao.UserRowMapper;
import edu.lkr.pojo.User;
import edu.lkr.service.UserService;

public class UserServiceImpl implements UserService {
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void save(User user) {
		String sql = "insert into user(name,age,sex)values(?,?,?)";
		Object[] result = new Object[] { user.getName(), user.getAge(), user.getSex() };
		int[] row = new int[] { java.sql.Types.VARCHAR, java.sql.Types.INTEGER, java.sql.Types.VARCHAR };
		jdbcTemplate.update(sql, result, row);
	}

	@Override
	public List<User> getUsers() {
		List<User> list = jdbcTemplate.query("select * from user", new UserRowMapper());
		return list;
	}

}
