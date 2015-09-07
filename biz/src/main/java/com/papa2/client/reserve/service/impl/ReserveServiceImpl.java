package com.papa2.client.reserve.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.papa2.client.api.ca.bo.ValidateResult;
import com.papa2.client.api.cache.IMemcachedCacheService;
import com.papa2.client.api.record.IBossRecordService;
import com.papa2.client.api.record.IClientRecordService;
import com.papa2.client.api.record.bo.Record;
import com.papa2.client.api.reserve.IReserveService;
import com.papa2.client.api.reserve.bo.Reserve;
import com.papa2.client.api.space.ISpaceService;
import com.papa2.client.api.space.bo.Space;
import com.papa2.client.api.user.IBossUserService;
import com.papa2.client.api.user.bo.User;
import com.papa2.client.framework.bo.BooleanResult;
import com.papa2.client.framework.exception.ServiceException;
import com.papa2.client.framework.log.Logger4jCollection;
import com.papa2.client.framework.log.Logger4jExtend;
import com.papa2.client.framework.util.LogUtil;
import com.papa2.client.framework.util.UUIDUtil;
import com.papa2.client.reserve.dao.IReserveDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class ReserveServiceImpl implements IReserveService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(ReserveServiceImpl.class);

	private TransactionTemplate transactionTemplate;

	private IMemcachedCacheService memcachedCacheService;

	private ISpaceService spaceService;

	private IBossUserService bossUserService;

	private IBossRecordService bossRecordService;

	private IClientRecordService clientRecordService;

	private IReserveDao reserveDao;

	@Override
	public int getReserveCount(Long userId) {
		if (userId == null) {
			return 0;
		}

		Reserve reserve = new Reserve();
		reserve.setUserId(userId);

		try {
			return reserveDao.getReserveCount(reserve);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(reserve), e);
		}

		return 0;
	}

	@Override
	public List<Reserve> getReserveList(Long userId) {
		if (userId == null) {
			return null;
		}

		Reserve reserve = new Reserve();
		reserve.setUserId(userId);

		try {
			return reserveDao.getReserveList(reserve);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(reserve), e);
		}

		return null;
	}

	@Override
	public Reserve getSpace(String spaceId) {
		if (StringUtils.isBlank(spaceId)) {
			return null;
		}

		Reserve reserve = new Reserve();

		Space space = spaceService.getSpace(spaceId);
		if (space != null) {
			reserve.setSpace(space);
		}

		return reserve;
	}

	/**
	 * 
	 * @param reserve
	 * @return
	 */
	private BooleanResult validate(Reserve reserve) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (reserve == null) {
			result.setCode("预定信息不能为空。");
			return result;
		}

		result.setResult(true);
		return result;
	}

	@Override
	public BooleanResult createReserve(Long userId, Reserve reserve, String modifyUser) {
		BooleanResult result = validate(reserve);
		if (!result.getResult()) {
			return result;
		}

		result.setResult(false);

		if (userId == null) {
			result.setCode("用户信息不能为空。");
			return result;
		}
		reserve.setUserId(userId);

		if (StringUtils.isEmpty(modifyUser)) {
			result.setCode("操作人信息不能为空。");
			return result;
		}
		reserve.setModifyUser(modifyUser);

		try {
			reserveDao.createReserve(reserve);
			result.setResult(true);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(reserve), e);

			result.setCode("写入预定信息失败！");
		}

		return result;
	}

	private Reserve getReserve(Reserve reserve) {
		try {
			return reserveDao.getReserve(reserve);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(reserve), e);
		}

		return null;
	}

	@Override
	public Reserve getReserve(Long userId, String reserveId) {
		Reserve reserve = new Reserve();

		if (userId == null) {
			return null;
		}
		reserve.setUserId(userId);

		if (StringUtils.isBlank(reserveId)) {
			return null;
		}

		try {
			reserve.setReserveId(Long.valueOf(reserveId));
		} catch (NumberFormatException e) {
			logger.error(LogUtil.parserBean(reserve), e);
		}

		reserve = getReserve(reserve);

		if (reserve == null) {
			return null;
		}

		reserve.setSpace(spaceService.getSpace(userId, String.valueOf(reserve.getSpaceId())));

		return reserve;
	}

	@Override
	public String generateToken(Long userId, String reserveId) {
		if (userId == null || StringUtils.isBlank(reserveId)) {
			return null;
		}

		Reserve reserve = getReserve(userId, reserveId);

		if (reserve == null) {
			return null;
		}

		String key = UUIDUtil.generate();

		try {
			memcachedCacheService.set(IMemcachedCacheService.CACHE_KEY_QR_CODE + key, reserve,
				IMemcachedCacheService.CACHE_KEY_QR_CODE_DEFAULT_EXP);
		} catch (ServiceException e) {
			logger.error(IMemcachedCacheService.CACHE_KEY_QR_CODE + key, e);
			return null;
		}

		return key;
	}

	@Override
	public ValidateResult validateToken(String token) {

		return null;
	}

	@Override
	public Reserve getReserve(Long parkId, Long userId, String token) {
		if (parkId == null || userId == null || StringUtils.isBlank(token)) {
			return null;
		}

		User user = bossUserService.getUser(userId);

		if (user == null) {
			return null;
		}

		if (user.getParkId().compareTo(parkId) != 0) {
			return null;
		}

		Reserve reserve = null;
		String key = token.trim();

		try {
			reserve = (Reserve) memcachedCacheService.get(IMemcachedCacheService.CACHE_KEY_QR_CODE + key);
		} catch (ServiceException e) {
			logger.error(IMemcachedCacheService.CACHE_KEY_QR_CODE + key, e);
			return null;
		}

		if (reserve == null) {
			return null;
		}

		Space space = spaceService.getSpace(reserve.getSpaceId());

		if (space == null) {
			return null;
		}

		if (space.getParkId().compareTo(parkId) != 0) {
			return null;
		}

		return reserve;
	}

	/**
	 * 只用于获取预约基本信息（验证）.
	 * 
	 * @param reserveId
	 * @return
	 */
	private Reserve getReserve(String reserveId) {
		Reserve reserve = new Reserve();

		if (StringUtils.isBlank(reserveId)) {
			return null;
		}

		try {
			reserve.setReserveId(Long.valueOf(reserveId));
		} catch (NumberFormatException e) {
			logger.error(LogUtil.parserBean(reserve), e);
		}

		return getReserve(reserve);
	}

	/**
	 * 验证当前操作人是否有权限操作.
	 * 
	 * @param parkId
	 * @param userId
	 * @param reserveId
	 */
	private BooleanResult validate(Long parkId, Long userId, String reserveId) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		if (parkId == null) {
			result.setCode("停车场信息不能为空。");
			return result;
		}

		if (userId == null) {
			result.setCode("操作人信息不能为空。");
			return result;
		}

		User user = bossUserService.getUser(userId);

		if (user == null) {
			result.setCode("操作人信息不存在。");
			return result;
		}

		if (user.getParkId().compareTo(parkId) != 0) {
			result.setCode("操作人无权操作。");
			return result;
		}

		if (StringUtils.isBlank(reserveId)) {
			result.setCode("车位预约信息不能为空。");
			return result;
		}

		Reserve reserve = getReserve(reserveId);

		if (reserve == null) {
			result.setCode("预约信息不存在。");
			return result;
		}

		Space space = spaceService.getSpace(reserve.getSpaceId());

		if (space == null) {
			result.setCode("出租车位信息不存在。");
			return result;
		}

		if (space.getParkId().compareTo(parkId) != 0) {
			result.setCode("操作人无权操作。");
			return result;
		}

		// 租车位人信息
		result.setCode(reserve.getUserId().toString());
		result.setResult(true);
		return result;
	}

	@Override
	public BooleanResult enter(Long parkId, final Long userId, final String reserveId) {
		BooleanResult result = validate(parkId, userId, reserveId);

		if (!result.getResult()) {
			return result;
		}

		final Long clientUserId = Long.valueOf(result.getCode());

		return transactionTemplate.execute(new TransactionCallback<BooleanResult>() {
			public BooleanResult doInTransaction(TransactionStatus ts) {
				// 1. 更新 client_tb_park_reserve STATE U -> I
				BooleanResult result = updateReserve(reserveId, "I", userId.toString());
				if (!result.getResult()) {
					ts.setRollbackOnly();
					return result;
				}

				// 2. 更新 boss_tb_park_record
				Record bossRecord = new Record();
				bossRecord.setUserId(userId);
				bossRecord.setReserveId(Long.valueOf(reserveId));

				result = bossRecordService.createRecord(bossRecord, userId.toString());
				if (!result.getResult()) {
					ts.setRollbackOnly();
					return result;
				}

				// 3. 更新 client_tb_park_record
				Record clientRecord = new Record();
				clientRecord.setUserId(clientUserId);
				clientRecord.setReserveId(Long.valueOf(reserveId));

				result = clientRecordService.createRecord(clientRecord, userId.toString());
				if (!result.getResult()) {
					ts.setRollbackOnly();
					return result;
				}

				return result;
			}
		});
	}

	@Override
	public BooleanResult leave(Long parkId, final Long userId, final String reserveId) {
		BooleanResult result = validate(parkId, userId, reserveId);

		if (!result.getResult()) {
			return result;
		}

		final Long clientUserId = Long.valueOf(result.getCode());

		return transactionTemplate.execute(new TransactionCallback<BooleanResult>() {
			public BooleanResult doInTransaction(TransactionStatus ts) {
				// 1. 更新 client_tb_park_reserve STATE I -> O
				BooleanResult result = updateReserve(reserveId, "O", userId.toString());
				if (!result.getResult()) {
					ts.setRollbackOnly();
					return result;
				}

				// 2. 更新 boss_tb_park_record
				Record bossRecord = new Record();
				bossRecord.setUserId(userId);
				bossRecord.setReserveId(Long.valueOf(reserveId));

				result = bossRecordService.createRecord(bossRecord, userId.toString());
				if (!result.getResult()) {
					ts.setRollbackOnly();
					return result;
				}

				// 3. 更新 client_tb_park_record
				Record clientRecord = new Record();
				clientRecord.setUserId(clientUserId);
				clientRecord.setReserveId(Long.valueOf(reserveId));

				result = clientRecordService.updateRecord(clientRecord, userId.toString());
				if (!result.getResult()) {
					ts.setRollbackOnly();
					return result;
				}

				return result;
			}
		});
	}

	private BooleanResult updateReserve(String reserveId, String state, String modifyUser) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		Reserve reserve = new Reserve();
		reserve.setReserveId(Long.valueOf(reserveId));
		reserve.setState(state);
		reserve.setModifyUser(modifyUser);

		try {
			int c = reserveDao.updateReserve(reserve);
			if (c == 1) {
				result.setResult(true);
			} else {
				result.setCode("更新失败！");
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(reserve), e);

			result.setCode("更新预约表失败！");
		}

		return result;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

	public ISpaceService getSpaceService() {
		return spaceService;
	}

	public void setSpaceService(ISpaceService spaceService) {
		this.spaceService = spaceService;
	}

	public IBossUserService getBossUserService() {
		return bossUserService;
	}

	public void setBossUserService(IBossUserService bossUserService) {
		this.bossUserService = bossUserService;
	}

	public IBossRecordService getBossRecordService() {
		return bossRecordService;
	}

	public void setBossRecordService(IBossRecordService bossRecordService) {
		this.bossRecordService = bossRecordService;
	}

	public IClientRecordService getClientRecordService() {
		return clientRecordService;
	}

	public void setClientRecordService(IClientRecordService clientRecordService) {
		this.clientRecordService = clientRecordService;
	}

	public IReserveDao getReserveDao() {
		return reserveDao;
	}

	public void setReserveDao(IReserveDao reserveDao) {
		this.reserveDao = reserveDao;
	}

}
