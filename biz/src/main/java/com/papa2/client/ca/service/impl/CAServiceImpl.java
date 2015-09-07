package com.papa2.client.ca.service.impl;

import org.apache.commons.lang.StringUtils;

import com.papa2.client.api.ca.ICAService;
import com.papa2.client.api.ca.bo.ValidateResult;
import com.papa2.client.api.user.IBossUserService;
import com.papa2.client.api.user.IClientUserService;
import com.papa2.client.api.user.bo.User;
import com.papa2.client.framework.log.Logger4jCollection;
import com.papa2.client.framework.log.Logger4jExtend;
import com.papa2.client.framework.util.EncryptUtil;

/**
 * 
 * @author xujiakun
 * 
 */
public class CAServiceImpl implements ICAService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(CAServiceImpl.class);

	private IBossUserService bossUserService;

	private IClientUserService clientUserService;

	@Override
	public ValidateResult validateUser(String passport, String password, String type) {
		// 初始化返回值 状态 = 失败
		ValidateResult result = new ValidateResult();
		result.setResultCode(ICAService.RESULT_FAILED);

		// 账号或密码为空
		if (StringUtils.isBlank(passport) || StringUtils.isEmpty(password)) {
			result.setMessage(ICAService.INCORRECT_NULL);
			return result;
		}

		// 根据passport查找用户信息
		User user = null;

		if ("BOSS".equals(type)) {
			user = bossUserService.getUserByPassport(passport);
		} else if ("CLIENT".equals(type)) {
			user = clientUserService.getUserByPassport(passport);
		}

		// 1. 判断登录用户是否在系统中
		if (user == null) {
			result.setMessage(ICAService.INCORRECT_LOGINID);
			return result;
		}

		user.setType(type);

		// 2. 判斷登陸用戶是否已被禁用
		if ("F".equals(user.getState())) {
			result.setMessage(ICAService.INCORRECT_DISABLED);
			return result;
		}

		try {
			if (EncryptUtil.encryptHMAC(password).equals(user.getPassword())) {
				return setSuccessResult(result, user);
			}
		} catch (Exception e) {
			logger.error(e);
		}

		result.setMessage(ICAService.INCORRECT_LOGIN);
		return result;
	}

	@Override
	public boolean validateRequest(Long userId, String url) {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * 
	 * @param result
	 * @param user
	 * @return
	 */
	private ValidateResult setSuccessResult(ValidateResult result, User user) {
		result.setResultCode(ICAService.RESULT_SUCCESS);
		user.setPassword(null);
		result.setUser(user);
		result.setMessage(null);
		return result;
	}

	public IBossUserService getBossUserService() {
		return bossUserService;
	}

	public void setBossUserService(IBossUserService bossUserService) {
		this.bossUserService = bossUserService;
	}

	public IClientUserService getClientUserService() {
		return clientUserService;
	}

	public void setClientUserService(IClientUserService clientUserService) {
		this.clientUserService = clientUserService;
	}

}
