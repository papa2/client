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
	 * 生成 6 位数字.
	 * 
	 * @param mobile
	 * @return
	 */
	BooleanResult generateCheckCode(String mobile);

	/**
	 * 忘记密码.
	 * 
	 * @param type
	 *            client or boss.
	 * @param checkCode
	 *            验证码.
	 * @param password
	 * @return
	 */
	BooleanResult setPassword(String type, String checkCode, String password);

	/**
	 * 修改密码.
	 * 
	 * @param type
	 *            client or boss.
	 * @param passport
	 * @param password
	 * @param oldPassword
	 * @return
	 */
	BooleanResult resetPassword(String type, String passport, String password, String oldPassword);

}
