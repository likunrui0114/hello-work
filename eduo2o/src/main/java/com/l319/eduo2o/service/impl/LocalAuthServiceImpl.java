package com.l319.eduo2o.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.l319.eduo2o.dto.LocalAuthExecution;
import com.l319.eduo2o.enums.LocalAuthStateEnum;
import com.l319.eduo2o.exception.LocalAuthOperationException;
import com.l319.eduo2o.mapper.LocalAuthMapper;
import com.l319.eduo2o.pojo.LocalAuth;
import com.l319.eduo2o.service.LocalAuthService;
import com.l319.eduo2o.util.MD5;

/**
 * 
 *
 * @author likunrui
 * @version 1.0
 */
@Service
public class LocalAuthServiceImpl implements LocalAuthService {
	@Autowired
	private LocalAuthMapper localAuthMapper;

	@Override
	public LocalAuth getLocalByUserNameAndPwd(String username, String password) {
		return localAuthMapper.queryLocalByUserNameAndPwd(username, MD5.getMd5(password));
	}

	@Override
	public LocalAuth getLocalByUserId(Long userId) {
		return localAuthMapper.queryLocalByUserId(userId);
	}

	@Override
	@Transactional
	public LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException {
		if (null == localAuth || null == localAuth.getPassword() || null == localAuth.getUsername()
				|| null == localAuth.getPersonInfo() || null == localAuth.getPersonInfo().getUserId()) {
			return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
		}
		LocalAuth tempAuth = localAuthMapper.queryLocalByUserId(localAuth.getPersonInfo().getUserId());
		if (null != tempAuth) {
			return new LocalAuthExecution(LocalAuthStateEnum.ONLY_ONE_ACCOUNT);
		}
		try {
			localAuth.setCreateTime(new Date());
			localAuth.setLastEditTime(new Date());
			localAuth.setPassword(MD5.getMd5(localAuth.getPassword()));
			int effectedNum = localAuthMapper.insertLocalAuth(localAuth);
			if (effectedNum <= 0) {
				throw new LocalAuthOperationException("账号绑定失败");
			} else {
				return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS, localAuth);
			}
		} catch (Exception e) {
			throw new LocalAuthOperationException("insertLoalAuth error:" + e.getMessage());
		}
	}

	@Override
	@Transactional
	public LocalAuthExecution modifyLocalAuth(Long userId, String userName, String password, String newPassword)
			throws LocalAuthOperationException {
		if (null != userId && null != userName && null != password && null != newPassword
				&& !password.equals(newPassword)) {
			try {
				int effectedNum = localAuthMapper.updateLocalAuth(userId, userName, MD5.getMd5(password),
						MD5.getMd5(newPassword), new Date());
				if (effectedNum <= 0) {
					throw new LocalAuthOperationException("更新密码失败");
				}
				return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS);
			} catch (Exception e) {
				throw new LocalAuthOperationException("更新密码失败：" + e.getMessage());
			}
		} else {
			return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
		}
	}

}
