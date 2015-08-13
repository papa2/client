package com.papa2.client.car.dao;

import java.util.List;

import com.papa2.client.api.car.bo.Car;

/**
 * 
 * @author xujiakun
 * 
 */
public interface ICarDao {

	/**
	 * 
	 * @param car
	 * @return
	 */
	List<Car> getCarList(Car car);

	/**
	 * 
	 * @param car
	 * @return
	 */
	Long createCar(Car car);

}
