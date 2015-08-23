package com.papa2.client.park.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.papa2.client.api.park.IParkService;
import com.papa2.client.api.park.bo.Park;
import com.papa2.client.framework.log.Logger4jCollection;
import com.papa2.client.framework.log.Logger4jExtend;
import com.papa2.client.framework.util.LogUtil;
import com.papa2.client.park.dao.IParkDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class ParkServiceImpl implements IParkService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(ParkServiceImpl.class);

	private IParkDao parkDao;

	@Override
	public List<Park> getParkList(String backCode) {
		return getParkList(backCode, null);
	}

	@Override
	public List<Park> getParkList(String backCode, String type) {
		Park park = new Park();

		if (StringUtils.isBlank(backCode)) {
			return null;
		}

		park.setBackCode(backCode.trim());

		if (StringUtils.isNotBlank(type)) {
			park.setType(type.trim());
		}

		try {
			return parkDao.getParkList(park);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(park), e);
		}

		return null;
	}

	@Override
	public Park getPark(Long parkId) {
		if (parkId == null) {
			return null;
		}

		Park park = new Park();
		park.setParkId(parkId);

		try {
			return parkDao.getPark(park);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(park), e);
		}

		return null;
	}

	public IParkDao getParkDao() {
		return parkDao;
	}

	public void setParkDao(IParkDao parkDao) {
		this.parkDao = parkDao;
	}

}
