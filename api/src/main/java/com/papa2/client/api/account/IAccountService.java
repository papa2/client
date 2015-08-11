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
	 * 生成 6 位数字.
	 * 
	 * @param mobile
	 * @param type
	 *            是否需要验证mobile是否存在.
	 * @return
	 */
	BooleanResult generateCheckCode(String mobile, boolean type);

	/**
	 * 忘记密码.
	 * 
	 * @param password
	 * @param checkCode
	 * @return
	 */
	BooleanResult setPassword(String password, String checkCode);

	/**
	 * 修改密码.
	 * 
	 * @param userId
	 * @param password
	 * @param oldPassword
	 * @return
	 */
	BooleanResult resetPassword(Long userId, String password, String oldPassword);

}
