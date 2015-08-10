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

}
