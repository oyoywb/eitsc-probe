package com.alimu.probe.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alimu.probe.entity.BroadcastInfo;
import com.alimu.probe.entity.ConfInfo;
import com.alimu.probe.entity.SoftList;
import com.alimu.probe.entity.WebList;

public interface ProfileDao {

	ConfInfo getConfInfoBySchoolId(Long schoolCode);
	int saveConfInfo(ConfInfo confInfo);
	int updateConfInfo(ConfInfo confInfo);
	
	List<SoftList> getSoftList(SoftList softList);
	int delSoftListById(@Param(value="id") Long id);
	int saveSoftList(SoftList softList);
	int updateSoftList(SoftList softList);
	SoftList getSoftListById(Long id);
	
	int updateWebList(WebList webList);
	int saveWebList(WebList webList);
	List<WebList> getWebList(WebList webList);
	int delWebListById(@Param(value="id") Long id);
	WebList getWebListById(Long id);
	List<BroadcastInfo> getBroadcastInfoPage(BroadcastInfo broadcastInfo);
	BroadcastInfo getBroadcastById(Long id);
	int deleteBroadcastById(@Param(value="ids")String id);
	
	int saveBroadcastInfo(BroadcastInfo broadcastInfo);
	List<BroadcastInfo> getBroadcastInfo(@Param("schoolCode")Long schoolCode);
	//远程接口
	List<Map<String, Object>> getBroadcastInfoByPcMac(@Param("pcMac") String pcMac);
	//远程接口
	Map<String, Object> getConfInfoModelBySchoolCode(Long schoolCode);
	//远程接口
	List<Map<String, Object>> getRemoteWebList(@Param("schoolCode")Long schoolCode);
	//远程接口
	List<Map<String, Object>> getRemoteSoftList(@Param("schoolCode")Long schoolCode);

}
