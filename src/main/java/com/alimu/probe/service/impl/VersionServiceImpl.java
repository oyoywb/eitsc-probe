package com.alimu.probe.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.alimu.probe.dao.VersionDao;
import com.alimu.probe.entity.ProbeDownloadInfo;
import com.alimu.probe.entity.ProbeInfo;
import com.alimu.probe.service.VersionService;
@Service
public class VersionServiceImpl implements VersionService{
	@Autowired
	private VersionDao versionDao;
	@Override
	public int saveOrUpdateProbe(ProbeInfo probeInfo) {
		if(probeInfo.getId()==null) {
			probeInfo.setDate(new Date());
			return versionDao.saveProbe(probeInfo);
		}else {
			return versionDao.updateProbe(probeInfo);
		}
	}
	@Override
	public List<ProbeInfo> getAllProbeInfo() {
		return versionDao.getAllProbeInfo();
	}
	@Override
	public int deleteProbeInfoById(Long id) {
		return versionDao.deleteProbeInfoById(id);		
	}
	@Override
	public ProbeInfo getProbeInfoById(Long id) {
		return versionDao.getProbeInfoById(id);
	}
	@Override
	public List<ProbeDownloadInfo> getProbeDownloadInfo() {
		return versionDao.getProbeDownloadInfo();
	}
	@Override
	public List<ProbeDownloadInfo> getProbeDownloadInfoByFactor(ProbeDownloadInfo probeDownloadInfo) {
		return versionDao.getProbeDownloadInfoByFactor(probeDownloadInfo);
	}
	
	
}
