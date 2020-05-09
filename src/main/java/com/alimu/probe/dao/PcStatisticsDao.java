package com.alimu.probe.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;







import org.apache.ibatis.annotations.Param;

import com.alimu.probe.resultEntity.AbnormalInfo;
import com.alimu.probe.resultEntity.PcUseStatisticsDetail;

public interface PcStatisticsDao {
	//软件统计
	List<Map<String,Object>> getWhiteSoftStatistics(@Param(value = "schoolCode")Long schoolCode, @Param (value="date")Date date);
	List<Map<String,Object>> getSoftAbnormalCount(@Param(value = "schoolCode")Long schoolCode, @Param (value="date")Date date);
	List<AbnormalInfo> getSoftAbnormalInfo(@Param(value = "schoolCode")Long schoolCode, @Param (value="date")Date date,@Param(value = "buildCode") Long buildCode);
	
	//网站统计
	List<Map<String,Object>> getWhiteWebStatistics(@Param(value = "schoolCode")Long schoolCode, @Param (value="date")Date date);
	List<Map<String,Object>> getWebAbnormalCount(@Param(value = "schoolCode")Long schoolCode, @Param (value="date")Date date);
	List<AbnormalInfo> getWebAbnormalInfo(@Param(value = "schoolCode")Long schoolCode, @Param (value="date")String date,@Param(value = "buildCode") Long buildCode);
	
	//PC设备使用统计
	List<PcUseStatisticsDetail> getPcUseStatisticsDetail(@Param(value = "schoolCode")Long schoolCode, @Param (value="date")Date date,@Param(value = "buildCode") Long buildCode);
	Map<String,Object> getPcUseStatistics(@Param(value = "schoolCode")Long schoolCode, @Param (value="date")Date date,@Param(value="totalCount") int totalCount);
	
	
	Map<String,Object> pcPerfStatistics(@Param(value = "schoolCode")Long schoolCode, @Param (value="date")Date date);
	int pcAbnormalStatistics(@Param(value="type") Integer type,@Param(value = "schoolCode")Long schoolCode, @Param (value="date")Date date);
	List<Map<String, Object>> getPcPerfStatisticsDetail(@Param(value = "schoolCode")Long schoolCode, @Param (value="date")Date date,@Param(value = "buildCode")Long buildCode);
	Map<String, Object> getPcMonitoring(@Param(value = "regionCode")Long regionCode, @Param(value = "schoolCode")Long schoolCode, @Param(value="totalCount")Integer totalCount);
	List<Map<String, Object>> getPCSchoolList(@Param(value = "regionCode")Long regionCode);
	Integer getPcCount(@Param(value = "regionCode")Long regionCode, @Param(value = "schoolCode")Long schoolCode);
	Integer getPcCountByDay(@Param(value = "regionCode")Long regionCode, @Param(value = "schoolCode")Long schoolCode, @Param(value = "day")String day);
	Integer getPcWarnCountByDay(@Param(value = "regionCode")Long regionCode, @Param(value = "schoolCode")Long schoolCode, @Param(value = "day")String day);
}
