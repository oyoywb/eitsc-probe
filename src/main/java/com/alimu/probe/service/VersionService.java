package com.alimu.probe.service;

import java.util.List;

import com.alimu.probe.entity.ProbeDownloadInfo;
import com.alimu.probe.entity.ProbeInfo;

public interface VersionService {
	int saveOrUpdateProbe(ProbeInfo probeInfo);
	List<ProbeInfo> getAllProbeInfo();
	int deleteProbeInfoById(Long id);
	ProbeInfo getProbeInfoById(Long id);
	List<ProbeDownloadInfo> getProbeDownloadInfoByFactor(ProbeDownloadInfo probeDownloadInfo);
	List<ProbeDownloadInfo> getProbeDownloadInfo();
}
