package com.papa2.client.account.service.impl;

import java.util.Random;

import org.apache.commons.lang.StringUtils;

import com.papa2.client.api.account.IAccountService;
import com.papa2.client.api.cache.IMemcachedCacheService;
import com.papa2.client.api.sms.ISMSService;
import com.papa2.client.api.user.IClientUserService;
import com.papa2.client.api.user.bo.User;
import com.papa2.client.framework.bo.BooleanResult;
import com.papa2.client.framework.log.Logger4jCollection;
import com.papa2.client.framework.log.Logger4jExtend;
import com.papa2.client.framework.util.LogUtil;

/**
 * 
 * @author xujiakun
 * 
 */
public class AccountServiceImpl implements IAccountService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(AccountServiceImpl.class);

	private IMemcachedCacheService memcachedCacheService;

	private IClientUserService clientUserService;

	private ISMSService smsService;

	@Override
	public BooleanResult generateCheckCode(String mobile) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (StringUtils.isBlank(mobile)) {
			result.setCode("手机号不能为空！");
			return result;
		}

		User user = clientUserService.getUserByPassport(mobile);

		if (user == null) {
			result.setCode("手机号在系统中不存在！");
			return result;
		}

		Random random = new Random();
		int min = 100000;
		int max = 999999;

		String token = String.valueOf(random.nextInt(max) % (max - min + 1) + min);

		try {
			memcachedCacheService.add(IMemcachedCacheService.CACHE_KEY_CHECK_CODE + token, user,
				IMemcachedCacheService.CACHE_KEY_CHECK_CODE_DEFAULT_EXP);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(user), e);

			result.setCode("验证码生成失败！");
			return result;
		}

		// 发送短信
		return smsService.send("找回登录密码", mobile, token, "sys");
	}

	@Override
	public BooleanResult setPassword(String checkCode, String password) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (StringUtils.isBlank(checkCode)) {
			result.setCode("验证码不能为空！");
			return result;
		}

		User user = validateCheckCode(checkCode);
		if (user == null) {
			result.setCode("验证码错误或已失效，请重新点击获取验证码！");
			return result;
		}

		if (StringUtils.isBlank(password)) {
			result.setCode("密码不能为空！");
			return result;
		}

		// 验证码失效
		invalidCheckCode(checkCode);

		return clientUserService.setPassword(user.getPassport(), password, user.getPassport());
	}

	@Override
	public BooleanResult resetPassword(String passport, String password, String oldPassword) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (StringUtils.isBlank(passport)) {
			result.setCode("用户信息不能为空。");
			return result;
		}

		if (StringUtils.isEmpty(oldPassword)) {
			result.setCode("请输入原密码！");
			return result;
		}

		if (StringUtils.isEmpty(password)) {
			result.setCode("请输入新密码！");
			return result;
		}

		return clientUserService.resetPassword(passport, password, oldPassword, passport);
	}

	private User validateCheckCode(String checkCode) {
		try {
			return (User) memcachedCacheService.get(IMemcachedCacheService.CACHE_KEY_CHECK_CODE + checkCode);
		} catch (Exception e) {
			logger.error("checkCode:" + checkCode, e);
		}

		return null;
	}

	private void invalidCheckCode(String checkCode) {
		try {
			memcachedCacheService.remove(IMemcachedCacheService.CACHE_KEY_CHECK_CODE + checkCode);
		} catch (Exception e) {
			logger.error("checkCode:" + checkCode, e);
		}
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

	public IClientUserService getClientUserService() {
		return clientUserService;
	}

	public void setClientUserService(IClientUserService clientUserService) {
		this.clientUserService = clientUserService;
	}

	public ISMSService getSmsService() {
		return smsService;
	}

	public void setSmsService(ISMSService smsService) {
		this.smsService = smsService;
	}

}
