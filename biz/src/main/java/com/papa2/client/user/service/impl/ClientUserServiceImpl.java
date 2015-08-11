package com.papa2.client.user.service.impl;

import org.apache.commons.lang.StringUtils;

import com.papa2.client.api.cache.IMemcachedCacheService;
import com.papa2.client.api.user.IClientUserService;
import com.papa2.client.api.user.bo.User;
import com.papa2.client.framework.bo.BooleanResult;
import com.papa2.client.framework.exception.ServiceException;
import com.papa2.client.framework.log.Logger4jCollection;
import com.papa2.client.framework.log.Logger4jExtend;
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
	public User getUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BooleanResult updateUser(String userId, User user, String modifyUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BooleanResult resetPassword(String passport, String password, String oldPassword, String modifyUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BooleanResult setPassword(String passport, String password, String modifyUser) {
		// TODO Auto-generated method stub
		return null;
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
