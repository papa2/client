package com.papa2.client.user.dao.impl;

import com.papa2.client.api.user.bo.User;
import com.papa2.client.framework.dao.impl.BaseDaoImpl;
import com.papa2.client.user.dao.IBossUserDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class BossUserDaoImpl extends BaseDaoImpl implements IBossUserDao {

	@Override
	public User getUserByPassport(String passport) {
		return (User) getSqlMapClientTemplate().queryForObject("user.boss.getUserByPassport", passport);
	}

	@Override
	public int setPassword(User user) {
		return getSqlMapClientTemplate().update("user.boss.setPassword", user);
	}

	@Override
	public int resetPassword(User user) {
		return getSqlMapClientTemplate().update("user.boss.resetPassword", user);
	}

	@Override
	public User getUser(User user) {
		return (User) getSqlMapClientTemplate().queryForObject("user.boss.getUser", user);
	}

}
