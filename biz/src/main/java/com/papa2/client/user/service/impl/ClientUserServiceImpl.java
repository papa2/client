package com.papa2.client.user.service.impl;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;

import com.papa2.client.api.cache.IMemcachedCacheService;
import com.papa2.client.api.user.IClientUserService;
import com.papa2.client.api.user.bo.User;
import com.papa2.client.framework.bo.BooleanResult;
import com.papa2.client.framework.exception.ServiceException;
import com.papa2.client.framework.log.Logger4jCollection;
import com.papa2.client.framework.log.Logger4jExtend;
import com.papa2.client.framework.util.EncryptUtil;
import com.papa2.client.framework.util.LogUtil;
import com.papa2.client.user.dao.IClientUserDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class ClientUserServiceImpl implements IClientUserService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(ClientUserServiceImpl.class);

	private IMemcachedCacheService memcachedCacheService;

	private IClientUserDao clientUserDao;

	@Override
	public User getUserByPassport(String passport) {
		if (StringUtils.isBlank(passport)) {
			return null;
		}

		String key = passport.trim().toUpperCase();

		User user = null;

		try {
			user = (User) memcachedCacheService.get(IMemcachedCacheService.CACHE_KEY_CLIENT_PASSPORT + key);
		} catch (ServiceException e) {
			logger.error(IMemcachedCacheService.CACHE_KEY_CLIENT_PASSPORT + key, e);
		}

		if (user != null) {
			return user;
		}

		user = getUser4Validate(passport);

		// not null then set to cache
		if (user != null) {
			try {
				memcachedCacheService.set(IMemcachedCacheService.CACHE_KEY_CLIENT_PASSPORT + key, user);
			} catch (ServiceException e) {
				logger.error(IMemcachedCacheService.CACHE_KEY_CLIENT_PASSPORT + key, e);
			}
		}

		return user;
	}

	@Override
	public User getUser4Validate(String passport) {
		if (StringUtils.isBlank(passport)) {
			return null;
		}

		try {
			return clientUserDao.getUserByPassport(passport.trim());
		} catch (Exception e) {
			logger.error(passport, e);
		}

		return null;
	}

	@Override
	public BooleanResult createUser(User user) {
		BooleanResult result = new BooleanResult();
		result.setCode(IClientUserService.ERROR_MESSAGE);
		result.setResult(false);

		// 创建用户
		Long userId = null;
		try {
			userId = clientUserDao.createUser(user);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(user), e);

			if (e.getMessage().indexOf("ORA-00001") != -1 || e.getMessage().indexOf("CLIENT_TB_USER_INDEX1") != -1) {
				result.setCode("手机号码已注册！");
			}

			return result;
		}

		result.setCode(userId.toString());
		result.setResult(true);
		return result;
	}

	@Override
	public User getUser(String passport) {
		User user = getUserByPassport(passport);

		if (user == null) {
			return null;
		}

		user.setPassword(null);

		return user;
	}

	@Override
	public BooleanResult updateUser(String passport, User user, String modifyUser) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (user == null) {
			result.setCode("用户信息不能为空。");
			return result;
		}

		if (StringUtils.isBlank(passport)) {
			result.setCode("用户账号信息不能为空。");
			return result;
		}
		user.setPassport(passport.trim());

		if (StringUtils.isEmpty(modifyUser)) {
			result.setCode("操作人信息不能为空。");
			return result;
		}
		user.setModifyUser(modifyUser);

		try {
			int c = clientUserDao.updateUser(user);

			if (c == 1) {
				result.setCode("修改信息成功！");
				result.setResult(true);

				// remove cache
				remove(passport);
			} else {
				result.setCode("修改信息失败，请稍后再试！");
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(user), e);

			result.setCode("修改信息失败！");
		}

		return result;
	}

	@Override
	public BooleanResult setPassword(String passport, String password, String modifyUser) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		User user = new User();

		if (StringUtils.isBlank(passport)) {
			result.setCode("账号不能为空！");
			return result;
		}

		user.setPassport(passport.trim());

		if (StringUtils.isEmpty(password)) {
			result.setCode("密码不能为空！");
			return result;
		}

		try {
			user.setPassword(EncryptUtil.encryptHMAC(password));
		} catch (IOException e) {
			logger.error("password:" + password, e);

			result.setCode("密码加密失败！");
			return result;
		}

		if (StringUtils.isEmpty(modifyUser)) {
			result.setCode("修改人不能为空。");
			return result;
		}

		user.setModifyUser(modifyUser);

		try {
			int c = clientUserDao.setPassword(user);
			if (c == 1) {
				result.setResult(true);
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(user), e);
		}

		return result;
	}

	@Override
	public BooleanResult resetPassword(String passport, String password, String oldPassword, String modifyUser) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		User user = new User();

		if (StringUtils.isBlank(passport)) {
			result.setCode("用户信息不能为空");
			return result;
		}

		user.setPassport(passport.trim());

		if (StringUtils.isEmpty(oldPassword)) {
			result.setCode("请输入原密码！");
			return result;
		}

		if (StringUtils.isEmpty(password)) {
			result.setCode("请输入新密码！");
			return result;
		}

		try {
			user.setPassword(EncryptUtil.encryptHMAC(password));
			user.setOldPassword(EncryptUtil.encryptHMAC(oldPassword));
		} catch (IOException e) {
			logger.error("password:" + password + "oldPassword:" + oldPassword, e);

			result.setCode("密码加密失败！");
			return result;
		}

		if (StringUtils.isEmpty(modifyUser)) {
			result.setCode("修改人不能为空。");
			return result;
		}

		user.setModifyUser(modifyUser);

		try {
			int c = clientUserDao.resetPassword(user);
			if (c == 1) {
				result.setResult(true);
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(user), e);
		}

		return result;
	}

	/**
	 * remove cache.
	 * 
	 * @param userId
	 */
	private void remove(String passport) {
		String key = passport.trim().toUpperCase();

		try {
			memcachedCacheService.remove(IMemcachedCacheService.CACHE_KEY_CLIENT_PASSPORT + key);
		} catch (ServiceException e) {
			logger.error(e);
		}
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

	public IClientUserDao getClientUserDao() {
		return clientUserDao;
	}

	public void setClientUserDao(IClientUserDao clientUserDao) {
		this.clientUserDao = clientUserDao;
	}

}
