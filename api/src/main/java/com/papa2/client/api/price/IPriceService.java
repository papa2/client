package com.papa2.client.api.price;

import java.math.BigDecimal;

import com.papa2.client.framework.exception.ServiceException;

/**
 * 
 * @author JiakunXu
 * 
 */
public interface IPriceService {

	/**
	 * 
	 * @param costType
	 * @param cost
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ServiceException
	 */
	BigDecimal cost(String costType, BigDecimal cost, String startTime, String endTime) throws ServiceException;

}
