package com.papa2.client.reserve.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.papa2.client.api.reserve.IReserveService;
import com.papa2.client.api.reserve.bo.Reserve;
import com.papa2.client.api.space.ISpaceService;
import com.papa2.client.api.space.bo.Space;
import com.papa2.client.framework.bo.BooleanResult;
import com.papa2.client.framework.log.Logger4jCollection;
import com.papa2.client.framework.log.Logger4jExtend;
import com.papa2.client.framework.util.LogUtil;
import com.papa2.client.reserve.dao.IReserveDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class ReserveServiceImpl implements IReserveService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(ReserveServiceImpl.class);

	private ISpaceService spaceService;

	private IReserveDao reserveDao;

	@Override
	public int getReserveCount(Long userId) {
		if (userId == null) {
			return 0;
		}

		Reserve reserve = new Reserve();
		reserve.setUserId(userId);

		try {
			return reserveDao.getReserveCount(reserve);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(reserve), e);
		}

		return 0;
	}

	@Override
	public List<Reserve> getReserveList(Long userId) {
		if (userId == null) {
			return null;
		}

		Reserve reserve = new Reserve();
		reserve.setUserId(userId);

		try {
			return reserveDao.getReserveList(reserve);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(reserve), e);
		}

		return null;
	}

	@Override
	public Reserve getSpace(String spaceId) {
		if (StringUtils.isBlank(spaceId)) {
			return null;
		}

		Reserve reserve = new Reserve();

		Space space = spaceService.getSpace(spaceId);
		if (space != null) {
			reserve.setSpace(space);
		}

		return reserve;
	}

	/**
	 * 
	 * @param reserve
	 * @return
	 */
	private BooleanResult validate(Reserve reserve) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (reserve == null) {
			result.setCode("预定信息不能为空。");
			return result;
		}

		result.setResult(true);
		return result;
	}

	@Override
	public BooleanResult createReserve(Long userId, Reserve reserve, String modifyUser) {
		BooleanResult result = validate(reserve);
		if (!result.getResult()) {
			return result;
		}

		result.setResult(false);

		if (userId == null) {
			result.setCode("用户信息不能为空。");
			return result;
		}
		reserve.setUserId(userId);

		if (StringUtils.isEmpty(modifyUser)) {
			result.setCode("操作人信息不能为空。");
			return result;
		}
		reserve.setModifyUser(modifyUser);

		try {
			reserveDao.createReserve(reserve);
			result.setResult(true);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(reserve), e);

			result.setCode("写入预定信息失败！");
		}

		return result;
	}

	@Override
	public Reserve getReserve(Long userId, String reserveId) {
		Reserve reserve = new Reserve();

		if (userId == null) {
			return null;
		}
		reserve.setUserId(userId);

		if (StringUtils.isBlank(reserveId)) {
			return null;
		}

		try {
			reserve.setReserveId(Long.valueOf(reserveId));
		} catch (NumberFormatException e) {
			logger.error(LogUtil.parserBean(reserve), e);
		}

		try {
			reserve = reserveDao.getReserve(reserve);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(reserve), e);
			return null;
		}

		if (reserve == null) {
			return null;
		}

		reserve.setSpace(spaceService.getSpace(userId, String.valueOf(reserve.getSpaceId())));

		return reserve;
	}

	public ISpaceService getSpaceService() {
		return spaceService;
	}

	public void setSpaceService(ISpaceService spaceService) {
		this.spaceService = spaceService;
	}

	public IReserveDao getReserveDao() {
		return reserveDao;
	}

	public void setReserveDao(IReserveDao reserveDao) {
		this.reserveDao = reserveDao;
	}

}
