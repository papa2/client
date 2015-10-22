package com.papa2.client.price.service.impl;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.papa2.client.api.price.IPriceService;
import com.papa2.client.framework.exception.ServiceException;
import com.papa2.client.framework.util.ArithUtil;
import com.papa2.client.framework.util.DateUtil;

/**
 * 
 * @author JiakunXu
 * 
 */
public class PriceServiceImpl implements IPriceService {

	@Override
	public BigDecimal cost(String costType, BigDecimal cost, String startTime, String endTime) {
		if (StringUtils.isBlank(costType)) {
			throw new ServiceException("costType is blank.");
		}

		if (cost == null) {
			throw new ServiceException("cost is null.");
		}

		if (StringUtils.isBlank(startTime)) {
			throw new ServiceException("startTime is blank.");
		}

		if (StringUtils.isBlank(endTime)) {
			throw new ServiceException("endTime is blank.");
		}

		// 按时
		if ("H".equals(costType)) {
			// 秒
			int s = DateUtil.getQuotSeconds(DateUtil.datetime(startTime), DateUtil.datetime(endTime));
			// 分钟
			double m = ArithUtil.div(Double.parseDouble(String.valueOf(s)), 60d, 0);
			// 15分钟内免费
			if (m < 15) {
				return BigDecimal.ONE;
			}
			// 1小时内收费
			if (m < 60) {
				return cost;
			}
			// 小时
			double h = ArithUtil.div(Double.parseDouble(String.valueOf(s)), 3600d, 0);

			return BigDecimal.valueOf(ArithUtil.mul(cost.doubleValue(), h));
		}

		// 按月
		if ("M".equals(costType)) {
			return cost;
		}

		throw new ServiceException("costType is error.");
	}

}
