package com.alimu.probe.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alimu.probe.entity.CpuState;
import com.alimu.probe.entity.PcState;
import com.alimu.probe.entity.SoftwareState;
import com.alimu.probe.entity.WebState;

public interface PcMonitoringDao {

	int saveWebState(WebState webState) ;
	List<WebState> getWebState(WebState webState);	
	int delWebState(Long id);
	WebState getWebStateById(Long id);
	List<WebState> getWebStateByFactors(@Param("schoolCode")Long schoolCode,@Param("buildCode")Long buildCode,@Param("startDate")Date startDate,@Param("endDate")Date endDate);
	
	
	
	int saveSoftwareState(SoftwareState softwareState);
	List<SoftwareState> getSoftwareState(SoftwareState softwareState);
	int delSoftwareState(Long id);
	SoftwareState getSoftwareStateById(Long id);
	List<SoftwareState> getSoftwareStateByFactors(@Param("schoolCode")Long schoolCode,@Param("buildCode")Long buildCode,@Param("startDate")Date startDate,@Param("endDate")Date endDate);
	
	
	int savePcState(PcState pcState);
	int saveCpuState(CpuState cpuState);
	
	List<Map<String, Object>> getPcState(@Param("id")Long id,@Param("schoolCode")Long schoolCode,@Param("buildCode")Long buildCode,@Param("classCode")Long classCode,@Param("date")Date date,@Param("pcCode")String pcCode);
	String getErrorInfo(PcState pcState);
	Map<String, Object> getCpuState(PcState pcState);
	
	
	List<Map<String, Object>> getErrorLoadingWarning(@Param("regionCode")Long regionCode,@Param("schoolCode")Long schoolCode);
	Map<String, Object> getSoftWarning(@Param("regionCode")Long regionCode,@Param("schoolCode")Long schoolCode);
	Map<String, Object> getWebWarning(@Param("regionCode")Long regionCode,@Param("schoolCode")Long schoolCode);
	Map<String, Object> getSoftFileName(@Param("regionCode")Long regionCode,@Param("schoolCode")Long schoolCode);
	List<Map<String, Object>> getWebFileName(@Param("regionCode")Long regionCode,@Param("schoolCode")Long schoolCode);
	List<PcState> getPcStatePage(PcState pcState);
	//获取当前学校最后一条监控数据
	WebState getLastWebState(@Param("schoolCode")Long schoolCode, @Param("webName")String webName,@Param("pcCode")String pcCode);
	
	
	//做数据接口
	List<PcState> getPcStateList(Long schoolCode);
	List<CpuState> getCpuStateList(Long schoolCode);
	List<WebState> getWebStateList(Long schoolCode);
	List<SoftwareState> getSoftwareStateList(Long schoolCode);
	int updateWebState(WebState webState);
	int updateSoftwareState(SoftwareState softwareState);
	PcState getPcStateById(Long id);
	List<SoftwareState> getSoftwareStatePage(@Param("schoolCode")Long schoolCode, @Param("pcCode")String pcCode,@Param("openDate")Date openDate, @Param("closeDate")Date closeDate);
	List<WebState> getWebStatePage(@Param("schoolCode")Long schoolCode, @Param("pcCode")String pcCode,@Param("openDate")Date openDate, @Param("closeDate")Date closeDate);
	List<Map<String, Object>> getPcUsetimeDay(@Param("schoolCode")Long schoolCode, @Param("pcCode")String pcCode,@Param("date")Date date);
}
