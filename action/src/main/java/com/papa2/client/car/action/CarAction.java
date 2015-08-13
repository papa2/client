package com.papa2.client.car.action;

import java.util.List;

import com.papa2.client.api.car.ICarService;
import com.papa2.client.api.car.bo.Car;
import com.papa2.client.framework.action.BaseAction;
import com.papa2.client.framework.bo.BooleanResult;

/**
 * 我的车辆.
 * 
 * @author xujiakun
 * 
 */
public class CarAction extends BaseAction {

	private static final long serialVersionUID = 2962533123805048247L;

	private ICarService carService;

	private List<Car> carList;

	private Car car;

	/**
	 * 首页.
	 * 
	 * @return
	 */
	public String index() {
		carList = carService.getCarList(this.getUser().getUserId());

		return SUCCESS;
	}

	public String create() {
		Long userId = this.getUser().getUserId();

		BooleanResult result = carService.createCar(userId, car, userId.toString());

		if (result.getResult()) {
			this.setSuccessMessage("车辆信息添加成功！");
		} else {
			this.setFailMessage(result.getCode());
		}

		return RESULT_MESSAGE;
	}

	public ICarService getCarService() {
		return carService;
	}

	public void setCarService(ICarService carService) {
		this.carService = carService;
	}

	public List<Car> getCarList() {
		return carList;
	}

	public void setCarList(List<Car> carList) {
		this.carList = carList;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

}
