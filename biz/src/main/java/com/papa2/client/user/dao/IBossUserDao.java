package com.papa2.client.user.dao;

import com.papa2.client.api.user.bo.User;

/**
 * 
 * @author xujiakun
 * 
 */
public interface IBossUserDao {

	/**
	 * 
	 * @param passport
	 * @return
	 */
	User getUserByPassport(String passport);

}
