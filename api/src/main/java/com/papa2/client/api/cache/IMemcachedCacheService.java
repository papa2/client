package com.papa2.client.api.cache;

import java.net.InetSocketAddress;
import java.util.List;

import com.papa2.client.api.cache.bo.CacheStats;
import com.papa2.client.framework.exception.ServiceException;
import com.papa2.client.framework.exception.SystemException;

/**
 * MemcachedCache.
 * 
 * @author xujiakun
 * 
 */
public interface IMemcachedCacheService extends ICacheService<String, Object> {

	/**
	 * default_exp_time.
	 */
	int DEFAULT_EXP = 24 * 60 * 60;

	/**
	 * session.
	 */
	int CACHE_KEY_SESSION_DEFAULT_EXP = 168 * 60 * 60;

	/**
	 * session.
	 */
	int CACHE_KEY_SESSION_EXP = 3 * 60;

	// >>>>>>>>>>以下是权限相关<<<<<<<<<<

	/**
	 * sso token.
	 */
	int CACHE_KEY_SSO_TOKEN_DEFAULT_EXP = 60;

	/**
	 * check code.
	 */
	String CACHE_KEY_CHECK_CODE = "key_check_code_";

	/**
	 * check code.
	 */
	int CACHE_KEY_CHECK_CODE_DEFAULT_EXP = 10 * 60;

	/**
	 * user id.
	 */
	String CACHE_KEY_USER_ID = "key_user_id_";

	String CACHE_KEY_LOCKED_USER_ID = "key_locked_user_id_";

	int CACHE_KEY_LOCKED_USER_ID_DEFAULT_EXP = 1 * 3;

	/**
	 * passport.
	 */
	String CACHE_KEY_PASSPORT = "key_passport_";

	/**
	 * request.
	 */
	int CACHE_KEY_REQUEST_DEFAULT_EXP = 5 * 60;

	// >>>>>>>>>>以下是系统参数相关<<<<<<<<<<

	/**
	 * dict list.
	 */
	int CACHE_KEY_DICT_DEFAULT_EXP = 1 * 60;

	// >>>>>>>>>>以下是监控相关<<<<<<<<<<

	/**
	 * log monitor.
	 */
	String CACHE_KEY_LOG_MONITOR = "key_log_monitor";

	int CACHE_KEY_LOG_MONITOR_DEFAULT_EXP = 0;

	/**
	 * action log.
	 */
	String CACHE_KEY_ACTION_LOG = "key_action_log";

	int CACHE_KEY_ACTION_LOG_DEFAULT_EXP = 0;

	// >>>>>>>>>>以下是接口相关<<<<<<<<<<

	/**
	 * open api.
	 */
	String CACHE_KEY_OPEN_API = "key_open_api";

	int CACHE_KEY_OPEN_API_DEFAULT_EXP = 0;

	// >>>>>>>>>>以下是文件相关<<<<<<<<<<

	/**
	 * file id.
	 */
	String CACHE_KEY_FILE_ID = "key_file_id_";

	// >>>>>>>>>>以下是菜单相关<<<<<<<<<<

	/**
	 * key_menu_tree_userId.
	 */
	String CACHE_KEY_MENU_TREE = "key_menu_tree_";

	// >>>>>>>>>>以下是组织相关<<<<<<<<<<

	// >>>>>>>>>>以下是编制相关<<<<<<<<<<

	/**
	 * position.
	 */
	int CACHE_KEY_POSITION_DEFAULT_EXP = 1 * 10;

	// >>>>>>>>>>以下是新闻相关<<<<<<<<<<

	/**
	 * key_news.
	 */
	String CACHE_KEY_NEWS = "key_news";

	int CACHE_KEY_NEWS_DEFAULT_EXP = 0;

	// >>>>>>>>>>以下是 bo 相关<<<<<<<<<<

	/**
	 * bo parameter.
	 */
	String CACHE_KEY_BO_PARAMETER = "key_bo_parameter_";

	int CACHE_KEY_BO_PARAMETER_DEFAULT_EXP = 0;

	// >>>>>>>>>>以下是 sap 相关<<<<<<<<<<

	/**
	 * sap account.
	 */
	int CACHE_KEY_SAP_ACCOUNT_DEFAULT_EXP = 60;

	/**
	 * sap org.
	 */
	String CACHE_KEY_SAP_ORG = "key_sap_org_";

	// >>>>>>>>>>以下是 cordys 相关<<<<<<<<<<

	String CACHE_KEY_CORDYS_SYNC = "key_cordys_sync";

	int CACHE_KEY_CORDYS_SYNC_DEFAULT_EXP = 10 * 60;

	/**
	 * cordys saml.
	 */
	String CACHE_KEY_CORDYS_PASSPORT = "key_cordys_passport_";

	// >>>>>>>>>>以下是流程相关<<<<<<<<<<

	/**
	 * workflow token.
	 */
	String CACHE_KEY_WORKFLOW_TOKEN_ID = "key_workflow_token_id_";

	int CACHE_KEY_WORKFLOW_TOKEN_ID_DEFAULT_EXP = 1 * 60 * 60;

	/**
	 * workflow file id.
	 */
	String CACHE_KEY_WORKFLOW_FILE_ID = "key_workflow_file_id_";

	/**
	 * workflow inst id.
	 */
	String CACHE_KEY_WORKFLOW_INST_ID = "key_workflow_inst_id_";

	int CACHE_KEY_WORKFLOW_INST_ID_DEFAULT_EXP = 1 * 5;

	/**
	 * workflow inst user id.
	 */
	String CACHE_KEY_WORKFLOW_INST_USER_ID = "key_workflow_inst_user_id_";

	int CACHE_KEY_WORKFLOW_INST_USER_ID_DEFAULT_EXP = 30 * 60;

	/**
	 * workflow inst act id.
	 */
	String CACHE_KEY_WORKFLOW_INST_ACT_ID = "key_workflow_inst_act_id_";

	int CACHE_KEY_WORKFLOW_INST_ACT_ID_DEFAULT_EXP = 5 * 60;

	/**
	 * workflow mod ver id.
	 */
	String CACHE_KEY_WORKFLOW_MOD_VER_ID = "key_workflow_mod_ver_id_";

	/**
	 * workflow mod att id.
	 */
	String CACHE_KEY_WORKFLOW_MOD_ATT_ID = "key_workflow_mod_att_id_";

	/**
	 * workflow process id.
	 */
	String CACHE_KEY_WORKFLOW_PROCESS_ID = "key_workflow_process_id_";

	/**
	 * workflow process alias.
	 */
	String CACHE_KEY_WORKFLOW_PROCESS_ALIAS = "key_workflow_process_alias_";

	/**
	 * workflow process folder.
	 */
	String CACHE_KEY_WORKFLOW_PROCESS_FOLDER = "key_workflow_process_folder_";

	/**
	 * workflow cc user id.
	 */
	String CACHE_KEY_WORKFLOW_CC_USER_ID = "key_workflow_cc_user_id_";

	/**
	 * workflow cond inst id.
	 */
	String CACHE_KEY_WORKFLOW_COND_INST_ID = "key_workflow_cond_inst_id_";

	/**
	 * workflow task id.
	 */
	String CACHE_KEY_WORKFLOW_TASK_ID = "key_workflow_task_id_";

	/**
	 * workflow file preview.
	 */
	String CACHE_KEY_WORKFLOW_FILE_PREVIEW = "key_workflow_file_preview";

	int CACHE_KEY_WORKFLOW_FILE_PREVIEW_DEFAULT_EXP = 1 * 60;

	// >>>>>>>>>>以下是其他相关<<<<<<<<<<

	/**
	 * favorite list.
	 */
	String CACHE_KEY_FAVORITE = "favorite.getFavoriteList";

	/**
	 * notify user type id.
	 */
	String CACHE_KEY_NOTIFY_USER_TYPE_ID = "key_notify_user_type_id_";

	/**
	 * notify file id.
	 */
	String CACHE_KEY_NOTIFY_FILE_ID = "key_notify_file_id_";

	// >>>>>>>>>>end<<<<<<<<<<

	/**
	 * incr.
	 * 
	 * @param key
	 * @param inc
	 * @return
	 * @throws ServiceException
	 */
	long incr(String key, int inc) throws ServiceException;

	/**
	 * incr.
	 * 
	 * @param key
	 * @param inc
	 * @return
	 * @throws ServiceException
	 */
	long incr(String key, long inc) throws ServiceException;

	/**
	 * decr.
	 * 
	 * @param key
	 * @param decr
	 * @return
	 * @throws ServiceException
	 */
	long decr(String key, int decr) throws ServiceException;

	/**
	 * decr.
	 * 
	 * @param key
	 * @param decr
	 * @return
	 * @throws ServiceException
	 */
	long decr(String key, long decr) throws ServiceException;

	/**
	 * flushAll.
	 * 
	 * @param address
	 * @throws SystemException
	 */
	void flushAll(InetSocketAddress address) throws SystemException;

	/**
	 * cache stats.
	 * 
	 * @return
	 * @throws ServiceException
	 */
	List<CacheStats> getStats() throws ServiceException;

}
