package com.alimu.probe.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alimu.probe.remote.EquipRemoteService;
import com.alimu.probe.resultEntity.AbnormalInfo;
import com.alimu.probe.resultEntity.PcUseStatisticsDetail;
import com.alimu.probe.service.PcStatisticsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("pcStatistics")
public class PcStatisticsController {
	@Autowired
	private PcStatisticsService pcStatisticsService;
	@Autowired
	private EquipRemoteService equipRemoteService; 
	/**
	 * PC设备使用统计
	 * 
	 * @author OuYang
	 * @date 2019-09-18
	 * @version 1.0
	 **/
	@RequestMapping("/pcUseStatistics")
	@ResponseBody
	public Map<String, Object> pcUseStatistics(@DateTimeFormat (pattern="yyyy-MM-dd")@RequestParam Date date,@RequestParam Long schoolCode) throws Exception {
		int tatalCount=equipRemoteService.getPcCount(schoolCode);
		return pcStatisticsService.getPcUseStatistics(schoolCode, date, tatalCount);
	}
	
	@RequestMapping("/getPcUseStatisticsDetail")
	@ResponseBody
	public PageInfo<PcUseStatisticsDetail> getPcUseStatisticsDetail(int page,int pageSize,@DateTimeFormat (pattern="yyyy-MM-dd")Date date,Long buildCode,Long schoolCode){		
		PageHelper.startPage(page,pageSize);
		PageInfo<PcUseStatisticsDetail> pageList = new PageInfo<PcUseStatisticsDetail>(pcStatisticsService.getPcUseStatisticsDetail(schoolCode,date,buildCode), pageSize);// 分页
		System.out.println(JSONObject.toJSONString(pageList));
		return pageList;	
	}
	/**
	 * 软件使用统计
	 * 
	 * @author OuYang
	 * @date 2019-09-18
	 * @version 1.0
	 **/
	@RequestMapping("/getSoftStatistics")
	@ResponseBody
	public Map<String,Object>  getSoftStatistics(@DateTimeFormat (pattern="yyyy-MM-dd")Date date,Long schoolCode){
		Map<String ,Object> _res=new HashMap<String,Object>();
		List<Map<String,Object>>  whiteSoftStatistics=pcStatisticsService.getWhiteSoftStatistics(schoolCode, date);
		List<Map<String, Object>> softAbnormalCount=pcStatisticsService.getSoftAbnormalCount(schoolCode, date);
		for(Map<String, Object> m:softAbnormalCount) {
			if(((int )m.get("level"))==1) {
				m.put("level", "记录用时");
			}else if(((int )m.get("level"))==2) {
				m.put("level", "警报");
			}else if(((int )m.get("level"))==3){
				m.put("level", "截图");
			}else if(((int )m.get("level"))==4){
				m.put("level", "录屏");
			}
		}
		_res.put("softAbnormalCount", softAbnormalCount);
		_res.put("whiteSoftStatistics", whiteSoftStatistics);
		return _res;
	}
	@RequestMapping("/getBlackSoftStatisticsDetail")
	@ResponseBody
	public  PageInfo<AbnormalInfo> getBlackSoftStatisticsDetail(int page,int pageSize,@DateTimeFormat (pattern="yyyy-MM-dd")Date date,Long buildCode,Long schoolCode){
		PageHelper.startPage(page,  pageSize);
		PageInfo<AbnormalInfo> pageList = new PageInfo<AbnormalInfo>(pcStatisticsService.getSoftAbnormalInfo(schoolCode, date, buildCode), pageSize);// 分页
		return pageList;		
	}
	/**
	 * 网站使用统计
	 * 
	 * @author OuYang
	 * @date 2019-09-18
	 * @version 1.0
	 **/
	@RequestMapping("/getWebStatistics")
	@ResponseBody
	public Map<String ,Object> getWebStatistics(@DateTimeFormat (pattern="yyyy-MM-dd")Date date,Long schoolCode){
		Map<String ,Object> _res=new HashMap<String,Object>();
		List<Map<String, Object>> whiteWebStatistics=pcStatisticsService.getWhiteWebStatistics(schoolCode, date);
		List<Map<String, Object>> webAbnormalCount=pcStatisticsService.getWebAbnormalCount(schoolCode, date);
		for(Map<String, Object> m:webAbnormalCount) {
			if(((int )m.get("level"))==1) {
				m.put("level", "警报");
			}else if(((int )m.get("level"))==2) {
				m.put("level", "截图");
			}else if(((int )m.get("level"))==3){
				m.put("level", "录屏");
			}
		}
		_res.put("whiteWebStatistics", whiteWebStatistics);
		_res.put("webAbnormalCount", webAbnormalCount);
		return _res;
	}
	@RequestMapping("/getBlackWebStatisticsDetail")
	@ResponseBody
	public PageInfo<AbnormalInfo> getBlackWebStatisticsDetail(int page,int pageSize, String date, Long buildCode, Long schoolCode){
		PageHelper.startPage(page, pageSize);
		PageInfo<AbnormalInfo> pageList = new PageInfo<AbnormalInfo>(pcStatisticsService.getWebAbnormalInfo(schoolCode, date, buildCode), pageSize);// 分页
		return pageList;			
	}
	/**
	 * pc性能统计
	 * 
	 * @author OuYang
	 * @date 2019-09-18
	 * @version 1.0
	 **/
	@ResponseBody
	@RequestMapping("/getPcPerfStatistics")
	public  Map<String ,Object> getPcPerfStatistics(@DateTimeFormat (pattern="yyyy-MM-dd")@RequestParam Date date,Long schoolCode){
		Map <String ,Object> _res=new HashMap<String,Object>();
		Map <String ,Object> basicInfo=pcStatisticsService.pcPerfStatistics(schoolCode, date);
		Map <String ,Object> abnormalInfo=pcStatisticsService.pcAbnormalStatistics(schoolCode, date);
		_res.put("basicInfo", basicInfo);
		_res.put("abnormalCount", abnormalInfo);
		return _res;
	}
	
	/**
	 * cpu异常信息
	 * 
	 * @author OuYang
	 * @date 2019-09-18
	 * @version 1.0
	 **/
	@ResponseBody
	@RequestMapping("/getPcPerfStatisticsDetail")
	public PageInfo<Map <String,Object>> getPcPerfStatisticsDetail(int page,int pageSize,@DateTimeFormat (pattern="yyyy-MM-dd")@RequestParam Date date, Long schoolCode, Long buildCode){	
		PageHelper.startPage(page, pageSize);
		PageInfo<Map <String,Object>> pageList = new PageInfo<Map <String,Object>>(pcStatisticsService.getPcPerfStatisticsDetail(date,schoolCode,buildCode), pageSize);// 分页
		return pageList;		
	}
}
