package com.alimu.probe.service;

import java.util.List;

import com.alimu.probe.entity.BroadcastInfo;
import com.alimu.probe.entity.ConfInfo;
import com.alimu.probe.entity.SoftList;
import com.alimu.probe.entity.WebList;

public interface ProfileService {

	int saveOrUpdateConfInfo(ConfInfo confInfo) ;
	ConfInfo getConfInfoByschoolCode(Long schoolCode);
	
	int saveOrUpdateSoftList(SoftList softList);
	List<SoftList> getSoftList(SoftList softList);
	int delSoftListById(Long id);
	SoftList getSoftListById(Long id);
	
	int saveOrUpdateWebList(WebList webList);
	List<WebList> getWebList(WebList webList);
	int delWebListById(Long id);
	WebList getWebListById(Long id);
	
	int saveBroadcastInfo(BroadcastInfo broadcastInfo);
	List<BroadcastInfo> getBroadcastInfoPage(BroadcastInfo broadcastInfo);
	BroadcastInfo getBroadcastById(Long id);
	int deleteBroadcastById(String ids);
	
}
