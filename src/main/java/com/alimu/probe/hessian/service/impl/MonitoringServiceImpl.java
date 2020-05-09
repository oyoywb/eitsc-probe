package com.alimu.probe.hessian.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.alimu.probe.dao.PcMonitoringDao;
import com.alimu.probe.dao.PcStatisticsDao;
import com.alimu.probe.hessian.service.MonitoringService;

@Service
public class MonitoringServiceImpl implements MonitoringService {
	@Autowired
	private PcMonitoringDao pcMonitoringDao;
	@Autowired
	private PcStatisticsDao pcStatisticsDao; 
	
	@Override
	public String getPcErrorMonitoring(Long regionCode,Long schoolCode) {
		Map<String, Object> _res = new HashMap<String, Object>();
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			StringBuffer url = request.getRequestURL();
			url = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/");
			List<Map<String, Object>> textList = pcMonitoringDao.getErrorLoadingWarning(regionCode,schoolCode);
			textList.add(pcMonitoringDao.getSoftWarning(regionCode,schoolCode));
			textList.add(pcMonitoringDao.getWebWarning(regionCode,schoolCode));
			_res.put("textList", textList);
			List<Map<String,Object>> list = pcMonitoringDao.getWebFileName(regionCode,schoolCode);
			list.add(pcMonitoringDao.getSoftFileName(regionCode,schoolCode));
			_res.put("imageList", list);
			_res.put("serverAddress",url +"local/");
			_res.put("code", "1");
		} catch (Exception e) {
			_res.put("code", "0");
			e.printStackTrace();
		}
		return JSONObject.toJSONString(_res);
	}

	@Override
	public String getPcMonitoring(Long regionCode,Long schoolCode) {
		Map<String, Object> _res = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			if (schoolCode != null) {
				Integer totalCount = pcStatisticsDao.getPcCount(regionCode,schoolCode);
				Map<String,Object> result = pcStatisticsDao.getPcMonitoring(regionCode,schoolCode,totalCount);
				list.add(result);
			}else if(regionCode != null){
				List<Map<String,Object>> schoolList = pcStatisticsDao.getPCSchoolList(regionCode);
				if (schoolList != null && !schoolList.isEmpty()) {
					for (int j = 0; j < schoolList.size(); j++) {
						Integer totalCount = pcStatisticsDao.getPcCount(regionCode,Long.valueOf(schoolList.get(j).get("school_code").toString()));
						if (totalCount == 0) {
							continue;
						}
						Map<String,Object> result = pcStatisticsDao.getPcMonitoring(regionCode,Long.valueOf(schoolList.get(j).get("school_code").toString()),totalCount);
						list.add(result);
					}
				}
			}else{
				List<Map<String,Object>> schoolList = pcStatisticsDao.getPCSchoolList(null);
				if (schoolList != null && !schoolList.isEmpty()) {
					for (int j = 0; j < schoolList.size(); j++) {
						Integer totalCount = pcStatisticsDao.getPcCount(regionCode,Long.valueOf(schoolList.get(j).get("school_code").toString()));
						if (totalCount == 0) {
							continue;
						}
						Map<String,Object> result = pcStatisticsDao.getPcMonitoring(regionCode,Long.valueOf(schoolList.get(j).get("school_code").toString()),totalCount);
						list.add(result);
					}
				}
			}
			_res.put("code", "1");
			_res.put("data", list);
		} catch (Exception e) {
			_res.put("code", "-1");
			e.printStackTrace();
		}		
		return JSONObject.toJSONString(_res);
	}

	@Override
	public Integer getPcCountByDay(Long regionCode, Long schoolCode, String day) {
		return pcStatisticsDao.getPcCountByDay(regionCode, schoolCode,day);
	}

	@Override
	public Integer getPcWarnCountByDay(Long regionCode, Long schoolCode,String day) {
		return pcStatisticsDao.getPcWarnCountByDay(regionCode, schoolCode, day);
	}

}
