package com.alimu.probe.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alimu.probe.dao.PcStatisticsDao;
import com.alimu.probe.resultEntity.AbnormalInfo;
import com.alimu.probe.resultEntity.PcUseStatisticsDetail;
import com.alimu.probe.service.PcStatisticsService;
@Service
public class PcStatisticsServiceImpl implements PcStatisticsService{
	
	@Autowired
	private PcStatisticsDao pcStatisticsDao;
	
	/**
	 * 软件使用统计
	 * 
	 * @author OuYang
	 * @date 2019-09-18
	 * @version 1.0
	 **/
	@Override
	public List<Map<String,Object>>  getWhiteSoftStatistics(Long schoolCode, Date date){		
		return pcStatisticsDao.getWhiteSoftStatistics(schoolCode,date);
	}

	@Override
	public List<Map<String, Object>> getSoftAbnormalCount(Long schoolCode, Date date) {
		return pcStatisticsDao.getSoftAbnormalCount(schoolCode,date);
	}

	@Override
	public List<AbnormalInfo> getSoftAbnormalInfo(Long schoolCode, Date date, Long buildCode) {
		return pcStatisticsDao.getSoftAbnormalInfo(schoolCode,date,buildCode);
	}

	/**
	 * PC设备使用统计
	 * 
	 * @author OuYang
	 * @date 2019-09-18
	 * @version 1.0
	 **/
	
	@Override
	public List<PcUseStatisticsDetail> getPcUseStatisticsDetail(Long schoolCode, Date date, Long buildCode) {
		return pcStatisticsDao.getPcUseStatisticsDetail(schoolCode,date,buildCode);
	}

	@Override
	public Map<String, Object> getPcUseStatistics(Long schoolCode, Date date, int totalCount) {
		return pcStatisticsDao.getPcUseStatistics(schoolCode, date, totalCount);
	}
	/**
	 * 网站使用统计
	 * 
	 * @author OuYang
	 * @date 2019-09-18
	 * @version 1.0
	 **/
	@Override
	public List<Map<String, Object>> getWhiteWebStatistics(Long schoolCode, Date date) {
		return pcStatisticsDao.getWhiteWebStatistics(schoolCode,date);
	}

	@Override
	public List<Map<String, Object>> getWebAbnormalCount(Long schoolCode, Date date) {
		return pcStatisticsDao.getWebAbnormalCount(schoolCode,date);
	}

	@Override
	public List<AbnormalInfo> getWebAbnormalInfo(Long schoolCode, String date, Long buildCode) {
		return pcStatisticsDao.getWebAbnormalInfo(schoolCode,date,buildCode);
	}

	
	/**
	 * pc性能统计
	 * 
	 * @author OuYang
	 * @date 2019-09-18
	 * @version 1.0
	 **/
	@Override
	public Map<String, Object> pcPerfStatistics(Long schoolCode, Date date) {
		return pcStatisticsDao.pcPerfStatistics(schoolCode,date);
	}

	@Override
	public Map<String, Object> pcAbnormalStatistics(Long schoolCode, Date date) {
		Map<String, Object> abnormalInfo=new HashMap<String, Object>();
		abnormalInfo.put("cpu_count", pcStatisticsDao.pcAbnormalStatistics(1,schoolCode, date));
		abnormalInfo.put("ram_count", pcStatisticsDao.pcAbnormalStatistics(2,schoolCode, date));
		abnormalInfo.put("space_count", pcStatisticsDao.pcAbnormalStatistics(3,schoolCode, date));
		return abnormalInfo;
	}
	
	@Override
	public List<Map<String, Object>> getPcPerfStatisticsDetail(Date date, Long schoolCode,Long buildCode) {
		return pcStatisticsDao.getPcPerfStatisticsDetail(schoolCode,date,buildCode);
	}

	

}
