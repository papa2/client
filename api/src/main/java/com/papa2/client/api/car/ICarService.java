package com.papa2.client.api.car;

import java.util.List;

import com.papa2.client.api.car.bo.Car;
import com.papa2.client.framework.bo.BooleanResult;

/**
 * 我的车辆-正在停车记录.
 * 
 * @author xujiakun
 * 
 */
public interface ICarService {

	/**
	 * 获取我的车辆信息.
	 * 
	 * @param userId
	 * @return
	 */
	List<Car> getCarList(Long userId);

	/**
	 * 添加车辆.
	 * 
	 * @param userId
	 * @param car
	 * @param modifyUser
	 * @return
	 */
	BooleanResult createCar(Long userId, Car car, String modifyUser);

}
