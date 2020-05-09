package com.alimu.probe.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alimu.probe.dao.PcMonitoringDao;
import com.alimu.probe.entity.PcState;
import com.alimu.probe.entity.SoftwareState;
import com.alimu.probe.entity.WebState;
import com.alimu.probe.service.PcMonitoringService;

@Service
public class PcMonitoringServiceImpl implements PcMonitoringService {
	@Autowired
	private PcMonitoringDao pcMonitoringDao;

	@Override
	public List<WebState> getWebState(WebState webState) {
		return pcMonitoringDao.getWebState(webState);
	}

	@Override
	public WebState getWebStateById(Long id) {
		return pcMonitoringDao.getWebStateById(id);
	}

	@Override
	public int delWebState(Long id) {
		return pcMonitoringDao.delWebState(id);
	}

	@Override
	public List<WebState> getWebStateByFactors(Long schoolCode, Long buildCode, Date startDate, Date endDate) {
		return pcMonitoringDao.getWebStateByFactors(schoolCode, buildCode, startDate, endDate);
	}

	@Override
	public List<SoftwareState> getSoftwareState(SoftwareState softwareState) {
		return pcMonitoringDao.getSoftwareState(softwareState);
	}

	@Override
	public int delSoftwareState(Long id) {
		return pcMonitoringDao.delSoftwareState(id);
	}

	@Override
	public SoftwareState getSoftwareStateById(Long id) {
		return pcMonitoringDao.getSoftwareStateById(id);
	}

	@Override
	public List<SoftwareState> getSoftwareStateByFactors(Long schoolCode, Long buildCode, Date startDate,
			Date endDate) {
		return pcMonitoringDao.getSoftwareStateByFactors(schoolCode, buildCode, startDate, endDate);
	}

	@Override
	public List<Map<String, Object>> getPcState(Long id,Long schoolCode,Long buildCode,Long classCode,Date date,String pcCode) {
		List<Map<String, Object>> list = pcMonitoringDao.getPcState(id,schoolCode,buildCode,classCode,date,pcCode);
		try {
			PcState pcState = pcMonitoringDao.getPcStateById(id);
			List<Map<String, Object>> timeList = pcMonitoringDao.getPcUsetimeDay(pcState.getSchoolCode(),pcState.getPcCode(),pcState.getDate());
			if (timeList != null && !timeList.isEmpty()) {
				Long times = 0L;
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				for (int i = 0; i < timeList.size(); i++) {
					times += df.parse(timeList.get(i).get("close_date").toString()).getTime() - df.parse(timeList.get(i).get("open_date").toString()).getTime();
				}
				list.get(0).put("use_time", times);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	

	@Override
	public List<PcState> getPcStatePage(PcState pcState) {
		return pcMonitoringDao.getPcStatePage(pcState);
	}

	@Override
	public int updateWebState(WebState webState) {
		return pcMonitoringDao.updateWebState(webState);
	}

	@Override
	public int updateSoftwareState(SoftwareState softwareState) {
		return pcMonitoringDao.updateSoftwareState(softwareState);
	}

	@Override
	public PcState getPcStateById(Long id) {
		return pcMonitoringDao.getPcStateById(id);
	}

	@Override
	public List<SoftwareState> getSoftwareStatePage(Long schoolCode,String pcCode, Date openDate, Date closeDate) {
		return pcMonitoringDao.getSoftwareStatePage(schoolCode, pcCode, openDate, closeDate);
	}

	@Override
	public List<WebState> getWebStatePage(Long schoolCode, String pcCode,Date openDate, Date closeDate) {
		return pcMonitoringDao.getWebStatePage(schoolCode, pcCode, openDate, closeDate);
	}

}
