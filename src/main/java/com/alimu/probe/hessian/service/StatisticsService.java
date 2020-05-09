package com.alimu.probe.hessian.service;

public interface StatisticsService {
	String getDailyPcAnalysis(Long schoolCode);
	String getDailyWebAnalysis(Long schoolCode);
	String getDailySoftAnalysis(Long schoolCode);
	String getDailyPcStateAnalysis(Long schoolCode);
	
	/**
	 * 获取软件名称列表
	 * 备注:参数按需传递,不传查询所有
	 * @param regionCode 	区域编码
	 * @param schoolCode	学校编码
	 * @return
	 * @author ymsn
	 * @date   2019年12月13日
	 */
	String getSoftNameList(Long regionCode,Long schoolCode);
}
