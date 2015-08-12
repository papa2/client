package com.papa2.client.user.dao.impl;

import com.papa2.client.api.user.bo.User;
import com.papa2.client.framework.dao.impl.BaseDaoImpl;
import com.papa2.client.user.dao.IClientUserDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class ClientUserDaoImpl extends BaseDaoImpl implements IClientUserDao {

	@Override
	public User getUserByPassport(String passport) {
		return (User) getSqlMapClientTemplate().queryForObject("user.client.getUserByPassport", passport);
	}

	@Override
	public Long createUser(User user) {
		return (Long) getSqlMapClientTemplate().insert("user.client.createUser", user);
	}

	@Override
	public int setPassword(User user) {
		return getSqlMapClientTemplate().update("user.client.setPassword", user);
	}

	@Override
	public int resetPassword(User user) {
		return getSqlMapClientTemplate().update("user.client.resetPassword", user);
	}

}
