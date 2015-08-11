package com.papa2.client.register.service.impl;

import java.io.IOException;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

import com.papa2.client.api.cache.IMemcachedCacheService;
import com.papa2.client.api.register.IRegisterService;
import com.papa2.client.api.register.bo.RegisterResult;
import com.papa2.client.api.sms.ISMSService;
import com.papa2.client.api.user.IClientUserService;
import com.papa2.client.api.user.bo.User;
import com.papa2.client.framework.bo.BooleanResult;
import com.papa2.client.framework.log.Logger4jCollection;
import com.papa2.client.framework.log.Logger4jExtend;
import com.papa2.client.framework.util.EncryptUtil;
import com.papa2.client.framework.util.LogUtil;

/**
 * 
 * @author xujiakun
 * 
 */
public class RegisterServiceImpl implements IRegisterService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(RegisterServiceImpl.class);

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

		User user = new User();
		user.setMobile(mobile.trim());

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
		return smsService.send("注册用户", mobile, token, "sys");
	}

	private RegisterResult validate(String mobile, String password) {
		// 初始化返回值 状态 = 失败
		RegisterResult result = new RegisterResult();
		result.setResultCode(IRegisterService.RESULT_FAILED);

		if (StringUtils.isBlank(mobile)) {
			result.setMessage("手机号码不能为空！");
			return result;
		}

		if (StringUtils.isBlank(password)) {
			result.setMessage("密码不能为空！");
			return result;
		}

		result.setResultCode(IRegisterService.RESULT_SUCCESS);
		return result;
	}

	@Override
	public RegisterResult registerUser(String mobile, String password, String userName, String checkCode) {
		return registerUser(mobile, password, userName, checkCode, null);
	}

	@Override
	public RegisterResult registerUser(String mobile, String password, String userName, String checkCode,
		String recommend) {
		// 验证
		RegisterResult result = validate(mobile, password);

		if (IRegisterService.RESULT_FAILED.equals(result.getResultCode())) {
			return result;
		}

		result.setResultCode(IRegisterService.RESULT_FAILED);

		if (StringUtils.isBlank(checkCode)) {
			result.setMessage("验证码不能为空！");
			return result;
		}

		User user = validateCheckCode(checkCode);
		if (user == null || !user.getMobile().equals(mobile.trim())) {
			result.setMessage("验证码错误或已失效，请重新点击获取验证码！");
			return result;
		}

		// 验证码失效
		invalidCheckCode(checkCode);

		// 构造对象
		user.setPassport(mobile.trim());

		// encrypt
		try {
			user.setPassword(EncryptUtil.encryptHMAC(password));
		} catch (IOException e) {
			logger.error("password:" + password, e);

			result.setMessage("系统异常，请稍后再试！");
			return result;
		}

		user.setUserName(StringUtils.isEmpty(userName) ? mobile : userName);
		user.setMobile(mobile.trim());

		BooleanResult res = clientUserService.createUser(user);

		// 创建失败
		if (!res.getResult()) {
			result.setMessage(res.getCode());
			return result;
		}

		user.setPassword(null);
		user.setUserId(Long.valueOf(res.getCode()));

		result.setUser(user);
		result.setMessage("用户注册成功！");

		// if recommend is not null then
		if (StringUtils.isNotBlank(recommend)) {
			// TODO
		}

		result.setResultCode(IRegisterService.RESULT_SUCCESS);
		return result;
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
