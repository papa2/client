package com.papa2.client.api.user;

import com.papa2.client.api.user.bo.User;
import com.papa2.client.framework.bo.BooleanResult;

/**
 * 用户.
 * 
 * @author xujiakun
 * 
 */
public interface IClientUserService {

	String SUCCESS = "success";

	String ERROR = "error";

	String ERROR_MESSAGE = "操作失败！";

	String ERROR_INPUT_MESSAGE = "操作失败，输入有误！";

	String ERROR_NULL_MESSAGE = "操作失败，不存在！";

	String ERROR_EXIST_MESSAGE = "操作失败，已存在！";

	/**
	 * 根据登陆帐号获取用户信息(存在缓存).
	 * 
	 * @param passport
	 * @return
	 */
	User getUserByPassport(String passport);

	/**
	 * 根据登陆帐号获取用户信息(不存在缓存).
	 * 
	 * @param passport
	 * @return
	 */
	User getUser4Validate(String passport);

	/**
	 * 创建用户.
	 * 
	 * @param user
	 * @return
	 */
	BooleanResult createUser(User user);

	/**
	 * 修改用户信息.
	 * 
	 * @param userId
	 * @param user
	 * @param modifyUser
	 * @return
	 */
	BooleanResult updateUser(String userId, User user, String modifyUser);

	/**
	 * 用户重置密码.
	 * 
	 * @param passport
	 * @param password
	 * @param modifyUser
	 * @return
	 */
	BooleanResult setPassword(String passport, String password, String modifyUser);

	/**
	 * 用户修改密码.
	 * 
	 * @param passport
	 * @param password
	 * @param oldPassword
	 * @param modifyUser
	 * @return
	 */
	BooleanResult resetPassword(String passport, String password, String oldPassword, String modifyUser);

}
