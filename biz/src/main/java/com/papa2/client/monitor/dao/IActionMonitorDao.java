package com.papa2.client.monitor.dao;

import java.util.List;

import com.papa2.client.api.monitor.bo.ActionMonitor;

/**
 * 
 * @author xujiakun
 * 
 */
public interface IActionMonitorDao {

	/**
	 * 
	 * @param logs
	 * @return
	 */
	int createActionMonitor(List<ActionMonitor> logs);

	/**
	 * 
	 * @param log
	 * @return
	 */
	int getActionMonitorCount(ActionMonitor log);

	/**
	 * 
	 * @param log
	 * @return
	 */
	List<ActionMonitor> getActionMonitorList(ActionMonitor log);

}
