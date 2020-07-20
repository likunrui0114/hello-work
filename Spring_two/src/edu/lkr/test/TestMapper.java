package edu.lkr.test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;

import edu.lkr.mapper.UserMapper;
import edu.lkr.pojo.User;

public class TestMapper {
	static SqlSessionFactory sqlSessionFactory = null;
	static {
		sqlSessionFactory = MybatisUtil.getSqlSessionFactory();
	}

	@Test
	public void testAdd() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			User user = new User("tom", 15);
			userMapper.insertUser(user);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.close();
		}
	}

	@Test
	public void getUser() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			User user = userMapper.getUser(1);
			System.out.println(user);
		} catch (Exception e) {
			sqlSession.close();
		}
	}
}
