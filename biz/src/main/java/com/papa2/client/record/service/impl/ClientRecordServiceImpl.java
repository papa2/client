package com.papa2.client.record.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.papa2.client.api.price.IPriceService;
import com.papa2.client.api.record.IClientRecordService;
import com.papa2.client.api.record.bo.Record;
import com.papa2.client.api.reserve.IReserveService;
import com.papa2.client.api.reserve.bo.Reserve;
import com.papa2.client.framework.bo.BooleanResult;
import com.papa2.client.framework.exception.ServiceException;
import com.papa2.client.framework.log.Logger4jCollection;
import com.papa2.client.framework.log.Logger4jExtend;
import com.papa2.client.framework.util.LogUtil;
import com.papa2.client.record.dao.IClientRecordDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class ClientRecordServiceImpl implements IClientRecordService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(ClientRecordServiceImpl.class);

	private IReserveService reserveService;

	private IPriceService priceService;

	private IClientRecordDao clientRecordDao;

	@Override
	public BooleanResult createRecord(Long userId, Record record, String modifyUser) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (record == null) {
			result.setCode("记录日志信息不能为空。");
			return result;
		}

		if (userId == null) {
			result.setCode("用户信息不能为空。");
			return result;
		}
		record.setUserId(userId);

		if (StringUtils.isEmpty(modifyUser)) {
			result.setCode("操作人信息不能为空。");
			return result;
		}

		record.setModifyUser(modifyUser);

		try {
			clientRecordDao.createRecord(record);
			result.setResult(true);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(record), e);

			result.setCode("写入记录日志表失败！");
		}

		return result;
	}

	@Override
	public BooleanResult updateRecord(Long userId, Long reserveId, String modifyUser) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		Record record = new Record();

		if (userId == null) {
			result.setCode("用户信息不能为空。");
			return result;
		}
		record.setUserId(userId);

		if (reserveId == null) {
			result.setCode("预约信息不能为空。");
			return result;
		}
		record.setReserveId(reserveId);

		if (StringUtils.isEmpty(modifyUser)) {
			result.setCode("操作人信息不能为空。");
			return result;
		}
		record.setModifyUser(modifyUser);

		try {
			int c = clientRecordDao.updateRecord(record);
			if (c == 1) {
				result.setResult(true);
			} else {
				result.setCode("更新失败！");
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(record), e);

			result.setCode("更新记录日志表失败！");
		}

		return result;
	}

	@Override
	public BooleanResult updateRecordState(Long userId, Long tradeId, String state, String modifyUser) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		Record record = new Record();

		if (userId == null) {
			result.setCode("用户信息不能为空。");
			return result;
		}
		record.setUserId(userId);

		if (tradeId == null) {
			result.setCode("交易信息不能为空。");
			return result;
		}
		record.setTradeId(tradeId);

		if (StringUtils.isBlank(state)) {
			result.setCode("停车记录状态信息不能为空。");
			return result;
		}
		record.setState(state.trim());

		if (StringUtils.isEmpty(modifyUser)) {
			result.setCode("操作人信息不能为空。");
			return result;
		}
		record.setModifyUser(modifyUser);

		try {
			clientRecordDao.updateRecordState(record);
			result.setResult(true);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(record), e);

			result.setCode("更新记录日志表失败！");
		}

		return result;
	}

	@Override
	public List<Record> getRecordList(Long userId, String state) {
		return getRecordList(userId, state, null);
	}

	@Override
	public List<Record> getRecordList(Long userId, String state, String carNo) {
		Record record = new Record();

		if (userId == null) {
			return null;
		}

		record.setUserId(userId);

		if (StringUtils.isBlank(state)) {
			return null;
		}

		record.setState(state.trim());

		if (StringUtils.isNotBlank(carNo)) {
			record.setCarNo(carNo.trim());
		}

		List<Record> recordList = null;

		try {
			recordList = clientRecordDao.getRecordList(record);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(record), e);
		}

		if (recordList == null) {
			return null;
		}

		for (Record rec : recordList) {
			try {
				// TODO if cost == 0 then state F -> D
				rec.setCost(priceService.cost(rec.getCostType(), rec.getCost(), rec.getStartTime(), rec.getEndTime()));
			} catch (ServiceException e) {
				return null;
			}
		}

		return recordList;
	}

	@Override
	public Record getRecord(Long userId, String recordId) {
		return getRecord(userId, recordId, null);
	}

	@Override
	public Record getRecord(Long userId, String recordId, String state) {
		Record record = new Record();

		if (userId == null) {
			return null;
		}
		record.setUserId(userId);

		if (StringUtils.isBlank(recordId)) {
			return null;
		}

		try {
			record.setRecordId(Long.valueOf(recordId));
		} catch (NumberFormatException e) {
			logger.error(e);

			return null;
		}

		if (StringUtils.isNotBlank(state)) {
			record.setState(state.trim());
		}

		try {
			record = clientRecordDao.getRecord(record);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(record), e);

			return null;
		}

		if (record == null) {
			return null;
		}

		Reserve reserve = reserveService.getReserve(userId, record.getReserveId());

		if (reserve == null) {
			return null;
		}

		record.setReserve(reserve);

		try {
			record.setCost(priceService.cost(reserve.getSpace().getCostType(), record.getCost(), record.getStartTime(),
				record.getEndTime()));
		} catch (ServiceException e) {
			return null;
		}

		return record;
	}

	public IReserveService getReserveService() {
		return reserveService;
	}

	public void setReserveService(IReserveService reserveService) {
		this.reserveService = reserveService;
	}

	public IPriceService getPriceService() {
		return priceService;
	}

	public void setPriceService(IPriceService priceService) {
		this.priceService = priceService;
	}

	public IClientRecordDao getClientRecordDao() {
		return clientRecordDao;
	}

	public void setClientRecordDao(IClientRecordDao clientRecordDao) {
		this.clientRecordDao = clientRecordDao;
	}

}
