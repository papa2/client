package com.papa2.client.reserve.service.impl;

import org.apache.commons.lang3.StringUtils;

import com.papa2.client.api.reserve.IReserveService;
import com.papa2.client.api.reserve.bo.Reserve;
import com.papa2.client.api.space.ISpaceService;

/**
 * 
 * @author xujiakun
 * 
 */
public class ReserveServiceImpl implements IReserveService {

	private ISpaceService spaceService;

	@Override
	public Reserve getReserve(String spaceId) {
		if (StringUtils.isBlank(spaceId)) {
			return null;
		}

		Reserve reserve = (Reserve) spaceService.getSpace(spaceId);

		return reserve;
	}

	public ISpaceService getSpaceService() {
		return spaceService;
	}

	public void setSpaceService(ISpaceService spaceService) {
		this.spaceService = spaceService;
	}

}
