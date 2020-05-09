package com.alimu.probe.hessian.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.alimu.probe.dao.DailyStatisticsDao;
import com.alimu.probe.hessian.service.StatisticsService;

@Service
public class StatisticsServiceImpl implements StatisticsService {
	@Autowired
	DailyStatisticsDao dailystatisticsDao;

	@Override
	public String getDailyPcAnalysis(Long schoolCode) {
		Map<String, Object> _res = new HashMap<String, Object>();
		Map<String, Object> _map = dailystatisticsDao.getDailyPcAnalysis(schoolCode);
		return setRes(_map,_res);
	}

	@Override
	public String getDailyWebAnalysis(Long schoolCode) {
		Map<String, Object> _res = new HashMap<String, Object>();
		Map<String, Object> _map = dailystatisticsDao.getDailyWebAnalysis(schoolCode);
		return setRes(_map,_res);
	}

	@Override
	public String getDailySoftAnalysis(Long schoolCode) {
		Map<String, Object> _res = new HashMap<String, Object>();
		List<Map<String, Object>> _map = dailystatisticsDao.getDailySoftAnalysis(schoolCode);
		return setRes(_map,_res);
	}

	@Override
	public String getDailyPcStateAnalysis(Long schoolCode) {
		Map<String, Object> _res = new HashMap<String, Object>();
		Map<String, Object> _map = dailystatisticsDao.getDailyPcStateAnalysis(schoolCode);
		return setRes(_map,_res);
	}

	public String setRes(Object o, Map<String,Object> _res) {
		if (o != null) {
			_res.put("code", 1);
			_res.put("info", "success");
			_res.put("list", o);
		} else {
			_res.put("code", 1);
			_res.put("info", "fail");
		}
		return JSONObject.toJSONString(_res);
	}

	
	@Override
	public String getSoftNameList(Long regionCode,Long schoolCode) {
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> list = dailystatisticsDao.getSoftNameList(regionCode,schoolCode);
			result.put("code", "1");
			result.put("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", "-1");
		}
		return JSONObject.toJSONString(result);
	}
}
