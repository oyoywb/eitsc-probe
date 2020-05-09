package com.alimu.probe.service;


import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alimu.probe.resultEntity.AbnormalInfo;
import com.alimu.probe.resultEntity.PcUseStatisticsDetail;

public interface PcStatisticsService {
	// 软件统计
	List<Map<String,Object>> getWhiteSoftStatistics(Long schoolCode, Date date);

	List<Map<String, Object>> getSoftAbnormalCount(Long schoolCode, Date date);

	List<AbnormalInfo> getSoftAbnormalInfo(Long schoolCode, Date date, Long buildCode);

	// 网站统计
	List<Map<String, Object>> getWhiteWebStatistics(Long schoolCode, Date date);

	List<Map<String, Object>> getWebAbnormalCount(Long schoolCode, Date date);

	List<AbnormalInfo> getWebAbnormalInfo(Long schoolCode, String date, Long buildCode);

	// PC设备使用统计

	List<PcUseStatisticsDetail> getPcUseStatisticsDetail(Long schoolCode, Date date, Long buildCode);
	
	Map<String,Object> getPcUseStatistics(Long schoolCode, Date date, int totalCount);
	
	//pc性能统计
	Map<String,Object> pcPerfStatistics(Long schoolCode, Date date);
	
	Map<String,Object> pcAbnormalStatistics(Long schoolCode, Date date);
	
	List<Map<String, Object>> getPcPerfStatisticsDetail(Date date, Long schoolCode,Long buildCode);
}
