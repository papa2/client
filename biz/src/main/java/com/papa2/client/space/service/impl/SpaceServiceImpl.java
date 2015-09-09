package com.papa2.client.space.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.papa2.client.api.cache.IMemcachedCacheService;
import com.papa2.client.api.park.IParkService;
import com.papa2.client.api.park.bo.Park;
import com.papa2.client.api.space.ISpaceService;
import com.papa2.client.api.space.bo.Space;
import com.papa2.client.framework.bo.BooleanResult;
import com.papa2.client.framework.exception.ServiceException;
import com.papa2.client.framework.log.Logger4jCollection;
import com.papa2.client.framework.log.Logger4jExtend;
import com.papa2.client.framework.util.LogUtil;
import com.papa2.client.space.dao.ISpaceDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class SpaceServiceImpl implements ISpaceService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(SpaceServiceImpl.class);

	private IMemcachedCacheService memcachedCacheService;

	private IParkService parkService;

	private ISpaceDao spaceDao;

	private BooleanResult validate(Space space) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (space == null) {
			result.setCode("车位信息不能为空。");
			return result;
		}

		if (StringUtils.isBlank(space.getSpaceCode())) {
			result.setCode("车位编号信息不能为空。");
			return result;
		}

		if (space.getParkId() == null) {
			result.setCode("停车场信息不能为空。");
			return result;
		}

		if (StringUtils.isBlank(space.getMon())) {
			space.setMon("N");
		}

		if (StringUtils.isBlank(space.getTue())) {
			space.setTue("N");
		}

		if (StringUtils.isBlank(space.getWed())) {
			space.setWed("N");
		}

		if (StringUtils.isBlank(space.getThu())) {
			space.setThu("N");
		}

		if (StringUtils.isBlank(space.getFri())) {
			space.setFri("N");
		}

		if (StringUtils.isBlank(space.getSat())) {
			space.setSat("N");
		}

		if (StringUtils.isBlank(space.getSun())) {
			space.setSun("N");
		}

		if (StringUtils.isBlank(space.getCostType())) {
			result.setCode("出租方式不能为空。");
			return result;
		}

		if ("H".equals(space.getCostType())) {
			Park park = parkService.getPark(space.getParkId());
			if (park == null) {
				result.setCode("停车场信息不能为空。");
				return result;
			}

			space.setCost(park.getCostHour());
		} else {
			if (space.getCost() == null) {
				result.setCode("出租费用信息不能为空。");
				return result;
			}
		}

		result.setResult(true);
		return result;
	}

	@Override
	public BooleanResult createSpace(Long userId, Space space, String modifyUser) {
		BooleanResult result = validate(space);
		if (!result.getResult()) {
			return result;
		}

		result.setResult(false);

		if (userId == null) {
			result.setCode("用户信息不能为空。");
			return result;
		}
		space.setUserId(userId);

		space.setType("P");

		if (StringUtils.isEmpty(modifyUser)) {
			result.setCode("操作人信息不能为空。");
			return result;
		}
		space.setModifyUser(modifyUser);

		try {
			spaceDao.createSpace(space);
			result.setResult(true);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(space), e);

			result.setCode("写入车位信息表失败！");
		}

		return result;
	}

	@Override
	public List<Space> getSpaceList(Long userId) {
		if (userId == null) {
			return null;
		}

		Space space = new Space();
		space.setUserId(userId);

		try {
			return spaceDao.getSpaceList(space);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(space), e);
		}

		return null;
	}

	@Override
	public Space getSpace(Long userId, String spaceId) {
		Space space = new Space();

		if (userId == null) {
			return null;
		}
		space.setUserId(userId);

		if (StringUtils.isBlank(spaceId)) {
			return null;
		}

		try {
			space.setSpaceId(Long.valueOf(spaceId));
		} catch (NumberFormatException e) {
			logger.error(e);
			return null;
		}

		try {
			return spaceDao.getSpace(space);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(space), e);
		}

		return null;
	}

	@Override
	public BooleanResult updateSpace(Long userId, Space space, String modifyUser) {
		BooleanResult result = validate(space);
		if (!result.getResult()) {
			return result;
		}

		result.setResult(false);

		if (space.getSpaceId() == null) {
			result.setCode("出租车位信息不能为空。");
			return result;
		}

		if (userId == null) {
			result.setCode("用户信息不能为空。");
			return result;
		}
		space.setUserId(userId);

		if (StringUtils.isEmpty(modifyUser)) {
			result.setCode("操作人信息不能为空。");
			return result;
		}
		space.setModifyUser(modifyUser);

		try {
			int c = spaceDao.updateSpace(space);
			if (c == 1) {
				result.setResult(true);
				// remove cache
				remove(space.getSpaceId());
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(space), e);

			result.setCode("更新车位信息表失败！");
		}

		return result;
	}

	@Override
	public BooleanResult cancelSpace(Long userId, Space space, String modifyUser) {
		return updateSpace(userId, space, "D", modifyUser);
	}

	@Override
	public BooleanResult enableSpace(Long userId, Space space, String modifyUser) {
		return updateSpace(userId, space, "U", modifyUser);
	}

	/**
	 * 
	 * @param userId
	 * @param space
	 * @param state
	 * @param modifyUser
	 * @return
	 */
	private BooleanResult updateSpace(Long userId, Space space, String state, String modifyUser) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (space == null || space.getSpaceId() == null) {
			result.setCode("出租车位信息不能为空。");
			return result;
		}

		if (userId == null) {
			result.setCode("用户信息不能为空。");
			return result;
		}

		if (StringUtils.isEmpty(modifyUser)) {
			result.setCode("操作人信息不能为空。");
			return result;
		}

		try {
			int c = spaceDao.updateSpace(userId, space.getSpaceId(), state, modifyUser);
			if (c == 1) {
				result.setResult(true);
				// remove cache
				remove(space.getSpaceId());
			} else {
				result.setCode("更新车位信息失败！");
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(space), e);

			result.setCode("更新车位信息表失败！");
		}

		return result;
	}

	@Override
	public List<Space> getSpaceList(String parkId) {
		if (StringUtils.isBlank(parkId)) {
			return null;
		}

		Space space = new Space();

		try {
			space.setParkId(Long.valueOf(parkId));
		} catch (NumberFormatException e) {
			logger.error(e);
			return null;
		}

		// 正在出租的车位
		space.setState("U");

		try {
			return spaceDao.getSpaceList(space);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(space), e);
		}

		return null;
	}

	@Override
	public Space getSpace(String spaceId) {
		if (StringUtils.isBlank(spaceId)) {
			return null;
		}

		try {
			return getSpace(Long.valueOf(spaceId));
		} catch (NumberFormatException e) {
			logger.error(e);
		}

		return null;
	}

	@Override
	public Space getSpace(Long spaceId) {
		if (spaceId == null) {
			return null;
		}

		Space space = null;
		String key = spaceId.toString();

		try {
			space = (Space) memcachedCacheService.get(IMemcachedCacheService.CACHE_KEY_SPACE + key);
		} catch (ServiceException e) {
			logger.error(IMemcachedCacheService.CACHE_KEY_SPACE + key, e);
		}

		if (space != null) {
			return space;
		}

		space = new Space();
		space.setSpaceId(spaceId);

		try {
			space = spaceDao.getSpace(space);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(space), e);
			return null;
		}

		if (space != null) {
			try {
				memcachedCacheService.set(IMemcachedCacheService.CACHE_KEY_SPACE + key, space);
			} catch (ServiceException e) {
				logger.error(IMemcachedCacheService.CACHE_KEY_SPACE + key, e);
			}
		}

		return space;
	}

	/**
	 * 清除车位缓存.
	 * 
	 * @param spaceId
	 */
	private void remove(Long spaceId) {
		String key = spaceId.toString();

		try {
			memcachedCacheService.remove(IMemcachedCacheService.CACHE_KEY_SPACE + key);
		} catch (ServiceException e) {
			logger.error(IMemcachedCacheService.CACHE_KEY_SPACE + key, e);
		}
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

	public IParkService getParkService() {
		return parkService;
	}

	public void setParkService(IParkService parkService) {
		this.parkService = parkService;
	}

	public ISpaceDao getSpaceDao() {
		return spaceDao;
	}

	public void setSpaceDao(ISpaceDao spaceDao) {
		this.spaceDao = spaceDao;
	}

}
