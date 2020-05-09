package com.alimu.probe.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alimu.probe.entity.PcState;
import com.alimu.probe.entity.SoftwareState;
import com.alimu.probe.entity.WebState;



public interface PcMonitoringService {

	List<WebState> getWebState(WebState webState);
	int delWebState(Long id);
	WebState getWebStateById(Long id);
	List<WebState> getWebStateByFactors(Long schoolCode, Long buildCode, Date startDate, Date endDate);
	
	List<SoftwareState> getSoftwareState(SoftwareState softwareState);
	int delSoftwareState(Long id);
	SoftwareState getSoftwareStateById(Long id);
	List<SoftwareState> getSoftwareStateByFactors(Long schoolCode, Long buildCode, Date startDate, Date endDate);
	
	List<Map<String,Object>> getPcState(Long id,Long schoolCode,Long buildCode,Long classCode,Date date,String pcCode);
	List<PcState> getPcStatePage(PcState pcState);
	int updateWebState(WebState webState);
	int updateSoftwareState(SoftwareState softwareState);
	PcState getPcStateById(Long id);
	List<SoftwareState> getSoftwareStatePage(Long schoolCode, String pcCode,Date openDate, Date closeDate);
	List<WebState> getWebStatePage(Long schoolCode, String pcCode,Date openDate, Date closeDate);
}
