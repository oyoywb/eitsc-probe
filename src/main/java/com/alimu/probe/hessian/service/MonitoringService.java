package com.alimu.probe.hessian.service;


public interface MonitoringService {
	/**
	 * PC监控异常数据
	 * @创建人:leaf
	 * @创建时间:2019年9月29日
	 */
	String getPcErrorMonitoring(Long regionCode,Long schoolCode); 
	/**
	 * PC监控数据
	 * @创建人:leaf
	 * @创建时间:2019年9月29日
	 */
	String getPcMonitoring(Long regionCode,Long schoolCode); 
	/**
	 * 获取当天pc监控数量
	 * @创建人:leaf
	 * @创建时间:2019年12月13日
	 */
	Integer getPcCountByDay(Long regionCode, Long schoolCode, String day); 
	/**
	 * pc当天报警次数
	 * @创建人:leaf
	 * @创建时间:2019年12月13日
	 */
	Integer getPcWarnCountByDay(Long regionCode, Long schoolCode, String day); 
}
