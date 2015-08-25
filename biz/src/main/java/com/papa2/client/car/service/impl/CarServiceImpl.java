package com.papa2.client.car.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.papa2.client.api.car.ICarService;
import com.papa2.client.api.car.bo.Car;
import com.papa2.client.car.dao.ICarDao;
import com.papa2.client.framework.bo.BooleanResult;
import com.papa2.client.framework.log.Logger4jCollection;
import com.papa2.client.framework.log.Logger4jExtend;
import com.papa2.client.framework.util.LogUtil;

/**
 * 
 * @author xujiakun
 * 
 */
public class CarServiceImpl implements ICarService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(CarServiceImpl.class);

	private ICarDao carDao;

	@Override
	public List<Car> getCarList(Long userId) {
		if (userId == null) {
			return null;
		}

		Car car = new Car();
		car.setUserId(userId);

		try {
			return carDao.getCarList(car);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(car), e);
		}

		return null;
	}

	private BooleanResult validate(Car car) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (car == null) {
			result.setCode("车辆信息不能为空。");
			return result;
		}

		String carNo = car.getCarNo();

		if (StringUtils.isEmpty(carNo)) {
			result.setCode("车牌信息不能为空。");
			return result;
		}

		boolean res = false;

		try {
			Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");
			Matcher matcher = pattern.matcher(carNo);
			res = matcher.matches();
		} catch (Exception e) {
			logger.error(e);
		}

		if (!res) {
			result.setCode("车牌信息不符合规则。");
			return result;
		}

		car.setCarNo("浙A" + car.getCarNo().toUpperCase());

		result.setResult(true);
		return result;
	}

	@Override
	public BooleanResult createCar(Long userId, Car car, String modifyUser) {
		BooleanResult result = validate(car);
		if (!result.getResult()) {
			return result;
		}

		result.setResult(false);

		if (userId == null) {
			result.setCode("用户信息不能为空。");
			return result;
		}
		car.setUserId(userId);

		if (StringUtils.isEmpty(modifyUser)) {
			result.setCode("操作人信息不能为空。");
			return result;
		}
		car.setModifyUser(modifyUser);

		try {
			carDao.createCar(car);
			result.setResult(true);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(car), e);

			result.setCode("写入车辆信息失败！");
		}

		return result;
	}

	public ICarDao getCarDao() {
		return carDao;
	}

	public void setCarDao(ICarDao carDao) {
		this.carDao = carDao;
	}

}
