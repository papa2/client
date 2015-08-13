package com.papa2.client.car.dao.impl;

import java.util.List;

import com.papa2.client.api.car.bo.Car;
import com.papa2.client.car.dao.ICarDao;
import com.papa2.client.framework.dao.impl.BaseDaoImpl;

/**
 * 
 * @author xujiakun
 * 
 */
public class CarDaoImpl extends BaseDaoImpl implements ICarDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Car> getCarList(Car car) {
		return getSqlMapClientTemplate().queryForList("car.getCarList", car);
	}

	@Override
	public Long createCar(Car car) {
		return (Long) getSqlMapClientTemplate().insert("car.createCar", car);
	}

}
