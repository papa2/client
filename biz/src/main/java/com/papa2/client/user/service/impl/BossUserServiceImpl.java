package com.papa2.client.user.service.impl;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;

import com.papa2.client.api.cache.IMemcachedCacheService;
import com.papa2.client.api.user.IBossUserService;
import com.papa2.client.api.user.bo.User;
import com.papa2.client.framework.bo.BooleanResult;
import com.papa2.client.framework.exception.ServiceException;
import com.papa2.client.framework.log.Logger4jCollection;
import com.papa2.client.framework.log.Logger4jExtend;
import com.papa2.client.framework.util.EncryptUtil;
import com.papa2.client.framework.util.LogUtil;
import com.papa2.client.user.dao.IBossUserDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class BossUserServiceImpl implements IBossUserService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(BossUserServiceImpl.class);

	private IMemcachedCacheService memcachedCacheService;

	private IBossUserDao bossUserDao;

	@Override
	public User getUserByPassport(String passport) {
		if (StringUtils.isBlank(passport)) {
			return null;
		}

		String key = passport.trim().toUpperCase();

		User user = null;

		try {
			user = (User) memcachedCacheService.get(IMemcachedCacheService.CACHE_KEY_BOSS_PASSPORT + key);
		} catch (ServiceException e) {
			logger.error(IMemcachedCacheService.CACHE_KEY_BOSS_PASSPORT + key, e);
		}

		if (user != null) {
			return user;
		}

		user = getUser4Validate(passport);

		// not null then set to cache
		if (user != null) {
			try {
				memcachedCacheService.set(IMemcachedCacheService.CACHE_KEY_BOSS_PASSPORT + key, user);
			} catch (ServiceException e) {
				logger.error(IMemcachedCacheService.CACHE_KEY_BOSS_PASSPORT + key, e);
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
			return bossUserDao.getUserByPassport(passport.trim());
		} catch (Exception e) {
			logger.error(passport, e);
		}

		return null;
	}

	@Override
	public BooleanResult setPassword(String passport, String password, String modifyUser) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		User user = new User();

		if (StringUtils.isBlank(passport)) {
			result.setCode("账号不能为空。");
			return result;
		}

		user.setPassport(passport.trim());

		if (StringUtils.isEmpty(password)) {
			result.setCode("密码不能为空。");
			return result;
		}

		try {
			user.setPassword(EncryptUtil.encryptHMAC(password));
		} catch (IOException e) {
			logger.error("password:" + password, e);

			result.setCode("密码加密失败。");
			return result;
		}

		if (StringUtils.isEmpty(modifyUser)) {
			result.setCode("修改人不能为空。");
			return result;
		}

		user.setModifyUser(modifyUser);

		try {
			int c = bossUserDao.setPassword(user);
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
			result.setCode("请输入原密码。");
			return result;
		}

		if (StringUtils.isEmpty(password)) {
			result.setCode("请输入新密码。");
			return result;
		}

		try {
			user.setPassword(EncryptUtil.encryptHMAC(password));
			user.setOldPassword(EncryptUtil.encryptHMAC(oldPassword));
		} catch (IOException e) {
			logger.error("password:" + password + "oldPassword:" + oldPassword, e);

			result.setCode("密码加密失败。");
			return result;
		}

		if (StringUtils.isEmpty(modifyUser)) {
			result.setCode("修改人不能为空。");
			return result;
		}

		user.setModifyUser(modifyUser);

		try {
			int c = bossUserDao.resetPassword(user);
			if (c == 1) {
				result.setResult(true);
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(user), e);
		}

		return result;
	}

	@Override
	public User getUser(Long userId) {
		User user = new User();

		if (userId == null) {
			return null;
		}

		user.setUserId(userId);

		try {
			return bossUserDao.getUser(user);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(user), e);
		}

		return null;
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

	public IBossUserDao getBossUserDao() {
		return bossUserDao;
	}

	public void setBossUserDao(IBossUserDao bossUserDao) {
		this.bossUserDao = bossUserDao;
	}

}
