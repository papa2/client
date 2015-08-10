package com.papa2.client.api.account;

import com.papa2.client.framework.bo.BooleanResult;

/**
 * 账号管理.
 * 
 * @author xujiakun
 * 
 */
public interface IAccountService {

	/**
	 * 邮件找回密码.
	 * 
	 * @param passport
	 * @return
	 */
	BooleanResult generateCheckCode4Mail(String passport);

	/**
	 * 忘记密码.
	 * 
	 * @param checkCode
	 * @param password
	 * @return
	 */
	BooleanResult setPassword(String checkCode, String password);

	/**
	 * 修改过期密码.
	 * 
	 * @param passport
	 * @param password
	 * @return
	 */
	BooleanResult setExpirePassword(String passport, String password);

	/**
	 * 修改密码.
	 * 
	 * @param passport
	 * @param password
	 * @param oldPassword
	 * @return
	 */
	BooleanResult resetPassword(String passport, String password, String oldPassword);

}
