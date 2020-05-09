package com.alimu.probe.dao;

import java.util.List;


import com.alimu.probe.entity.ProbeInfo;
import com.alimu.probe.entity.ProbeDownloadInfo;

public interface VersionDao {

	List<ProbeInfo> getAllProbeInfo();
	int deleteProbeInfoById(Long id);
	ProbeInfo getProbeInfoById(Long id);	
	int saveProbe(ProbeInfo probeInfo);
	int updateProbe(ProbeInfo probeInfo);
	List<ProbeDownloadInfo> getProbeDownloadInfo();	
	List<ProbeDownloadInfo> getProbeDownloadInfoByFactor(ProbeDownloadInfo probeDownloadInfo);
	
	//hessian
	String getProbeVersion();
	String getProbeFileUrl();
	int saveProbeDownloadInfo(ProbeDownloadInfo probeDownloadInfo);
	
}
