package com.alimu.probe.hessian.service;

import java.io.InputStream;

import com.alimu.probe.entity.CpuState;
import com.alimu.probe.entity.PcState;
import com.alimu.probe.entity.SoftwareState;
import com.alimu.probe.entity.WebState;

/** 
 * 探针总控远程接口
 */
public interface MasterControlService {
	//获取探针最新版本信息
	String getProbeVersion(String version);
	//安装文件下载
	String getProbeFile();
	//上传探针下载信息
	String saveProbeDownloadInfo(String probeDownloadInfo);
	//获取探针绑定信息
	String getProbeBind(Long schoolCode);
	//获取配置信息
	String getConfVer(Long schoolCode);
	//获取文字广播信息
	String getBroadcastInfo(Long schoolCode);
	//上传网站监控
	String saveWebState(WebState webState,InputStream is,String path);
	//上传软件监控
	String saveSoftwareState(SoftwareState softwareState,InputStream is,String path);
	//上传PC状态信息
	String savePcState(PcState pcState);
	//上传性能信息
	String saveCpuState(CpuState cpuState);
	//根据pcCode地址获取跑马灯信息
	String getBroadcastInfoByPcMac(String pcMac);
	//根据pc mac地址获取pc信息
	String getProbeBindByMac(String pcMac);
	//时间同步
	String synTime();
	//做监控数据
	void saveMonitorData(Long schoolCode);
}
