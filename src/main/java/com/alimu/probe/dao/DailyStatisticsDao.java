package com.alimu.probe.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DailyStatisticsDao {
	int dailyPcAnalysis();//根据一天的数据统计成PC统计日表
	int dailyWebAnalysis();//根据一天的数据统计成网站统计日表
	int dailySoftAnalysis();//根据一天的数据统计成软件统计日表
	int dailyPcStateAnalysis(@Param(value="totalCount")int totalCount);//根据一天的数据统计成PC状态统计日表
	Map<String,Object> getDailyPcAnalysis(@Param(value="school_code")Long schoolCode);//根据学校编号获取一条PC统计日表
	Map<String,Object> getDailyWebAnalysis(@Param(value="school_code")Long schoolCode);//根据学校编号获取一条网站统计日表
	List<Map<String,Object>> getDailySoftAnalysis(@Param(value="school_code")Long schoolCode);//根据学校编号获取一条软件统计日表
	Map<String,Object> getDailyPcStateAnalysis(@Param(value="school_code")Long schoolCode);//根据学校编号获取一条PC性能统计日表
	
	// 获取软件名称列表
	List<Map<String,Object>> getSoftNameList(@Param(value="regionCode")Long regionCode,@Param(value="schoolCode")Long schoolCode);
}
