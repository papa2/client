package com.papa2.client.space.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.papa2.client.api.space.ISpaceService;
import com.papa2.client.api.space.bo.Space;
import com.papa2.client.framework.bo.BooleanResult;
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
			result.setCode("车位出租人信息不能为空。");
			return result;
		}

		space.setUserId(userId);
		space.setType("P");
		space.setModifyUser(modifyUser);

		try {
			spaceDao.createSpace(space);
			result.setResult(true);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(space), e);

			result.setCode("写入车位信息失败！");
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

	public ISpaceDao getSpaceDao() {
		return spaceDao;
	}

	public void setSpaceDao(ISpaceDao spaceDao) {
		this.spaceDao = spaceDao;
	}

}
