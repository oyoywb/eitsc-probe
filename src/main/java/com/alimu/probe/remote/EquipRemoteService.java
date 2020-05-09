package com.alimu.probe.remote;

import java.util.Map;


/**
 * @Description EITSC一期项目--设备运维服务系统1.0--远程接口调用
 * @author QiuXL
 * @date 2019-08-30
 * @version 1.0.9.2
 * **/
public interface EquipRemoteService {
	
	//获取pc设备基本信息
	String getPcInfo(Integer page,Integer pageSize,Long schoolCode,Long buildCode);
	//根据校区编号获取教学楼信息
	String getBuildInfo(Long schoolCode);
	//根据pc编号获取pc位置信息
	Map<String, Object> getPcInfoDetail(String pcCode,Long schoolCode);
	//根据校区编号获取校园pc总数
	Integer getPcCount(Long schoolCode);
	//根据校区编号获取课室信息
	public String getClassroomInfo(Long schoolCode,Long buildCode);
	//获取所有校区列表
	public String getSchoolCodeList();
	//根据学校编码获取设备列表
	String getPcEquipList(Long schoolCode);
	//根据校区编号列表获取课室信息
	String getClassroomPage(Long schoolCode, String buildCode,Integer page,Integer pageSize);
}
