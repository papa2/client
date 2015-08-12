package com.papa2.client.api.register;

import com.papa2.client.api.register.bo.RegisterResult;
import com.papa2.client.framework.bo.BooleanResult;

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
	 * 生成 6 位数字.
	 * 
	 * @param mobile
	 * @return
	 */
	BooleanResult generateCheckCode(String mobile);

	/**
	 * 用户注册.
	 * 
	 * @param checkCode
	 *            验证码.
	 * @param mobile
	 *            手机号码.
	 * @param password
	 *            密码.
	 * @param userName
	 *            昵称.
	 * @return
	 */
	RegisterResult registerUser(String checkCode, String mobile, String password, String userName);

	/**
	 * 用户注册.
	 * 
	 * @param checkCode
	 *            验证码.
	 * @param mobile
	 *            手机号码.
	 * @param password
	 *            密码.
	 * @param userName
	 *            昵称.
	 * @param recommend
	 *            邀请.
	 * @return
	 */
	RegisterResult registerUser(String checkCode, String mobile, String password, String userName, String recommend);

}
