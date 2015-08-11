package com.papa2.client.api.register;

import com.papa2.client.api.register.bo.RegisterResult;

/**
 * 
 * @author xujiakun
 * 
 */
public interface IRegisterService {

	String RESULT_SUCCESS = "0";

	String RESULT_FAILED = "1";

	String RESULT_ERROR = "2";

	/**
	 * 用户注册.
	 * 
	 * @param mobile
	 *            手机号码.
	 * @param password
	 *            密码.
	 * @param userName
	 *            昵称.
	 * @param checkCode
	 *            验证码.
	 * @return
	 */
	RegisterResult registerUser(String mobile, String password, String userName, String checkCode);

	/**
	 * 用户注册.
	 * 
	 * @param mobile
	 *            手机号码.
	 * @param password
	 *            密码.
	 * @param userName
	 *            昵称.
	 * @param checkCode
	 *            验证码.
	 * @param recommend
	 *            邀请.
	 * @return
	 */
	RegisterResult registerUser(String mobile, String password, String userName, String checkCode, String recommend);

}
