package com.alimu.probe.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alimu.probe.entity.BroadcastInfo;
import com.alimu.probe.entity.ConfInfo;
import com.alimu.probe.entity.SoftList;
import com.alimu.probe.entity.WebList;
import com.alimu.probe.service.ProfileService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("profile")
public class ProfileController {
	@Autowired
	private ProfileService profileService;

	/**
	 * 保存或修改基本配置信息
	 * 
	 * @author OuYang
	 * @date 2019-09-09
	 * @version 1.0
	 **/
	@ResponseBody
	@RequestMapping("/saveOrUpdateConfInfo")
	public Map<String, Object> saveOrUpdateConfInfo(@RequestBody ConfInfo confInfo) { 
		Map<String, Object> _res = new HashMap<String, Object>();
		setRes(profileService.saveOrUpdateConfInfo(confInfo),_res);
		return _res;
	}
	/**
	 * 根据学校id获取该学校的基本配置信息
	 * 
	 * @author OuYang
	 * @date 2019-09-09
	 * @version 1.0
	 **/
	@ResponseBody
	@RequestMapping("/getConfInfoBySchoolId")
	public ConfInfo getConfInfoBySchoolId(Long schoolCode) { 
		return profileService.getConfInfoByschoolCode(schoolCode);
	}

	/**
	 * 保存或更新软件黑白名单
	 * 
	 * @author OuYang
	 * @date 2019-09-09
	 * @version 1.0
	 **/
	@ResponseBody
	@RequestMapping("/saveOrUpdateSoftList")
	public Map<String, Object> saveOrUpdateSoftList(@RequestBody SoftList softList) { 
		Map<String, Object> _res = new HashMap<String, Object>();
		setRes(profileService.saveOrUpdateSoftList(softList),_res);
		return _res;
	}
	/**
	 * 分页查询设置的软件黑白名单
	 * 
	 * @author OuYang
	 * @date 2019-09-09
	 * @version 1.0
	 **/
	@ResponseBody
	@RequestMapping("/getSoftList")
	public PageInfo<SoftList> getSoftList(int page,int pageSize,SoftList softList) { 
		PageHelper.startPage(page, pageSize);
		PageInfo<SoftList> pageList = new PageInfo<SoftList>(profileService.getSoftList(softList), pageSize);// 分页
		return pageList ;
	}
	
	/**
	 * 根据id获取软件黑白名单
	 * 
	 * @author OuYang
	 * @date 2019-09-09
	 * @version 1.0
	 **/
	@ResponseBody
	@RequestMapping("/getSoftListById")
	public SoftList getSoftListById(@RequestParam Long id) {
		return profileService.getSoftListById(id);
	}
	/**
	 * 根据id删除软件黑白名单
	 * 
	 * @author OuYang
	 * @date 2019-09-09
	 * @version 1.0
	 **/
	@ResponseBody
	@RequestMapping("/delSoftListById")
	public Map<String, Object> delSoftListById(@RequestParam Long id) {
		Map<String, Object> _res = new HashMap<String, Object>();
		setRes(profileService.delSoftListById(id),_res);
		return _res;
	}
	
	/**
	 * 保存或更新网站黑白名单
	 * 
	 * @author OuYang
	 * @date 2019-09-09
	 * @version 1.0
	 **/
	@ResponseBody
	@RequestMapping("/saveOrUpdateWebList")
	public Map<String, Object> saveOrUpdateWebList(@RequestBody WebList webList) { 
		Map<String, Object> _res = new HashMap<String, Object>();
		setRes(profileService.saveOrUpdateWebList(webList),_res);
		return _res;
	}
	/**
	 * 分页查询设置的网站黑白名单
	 * 
	 * @author OuYang
	 * @date 2019-09-09
	 * @version 1.0
	 **/
	@ResponseBody
	@RequestMapping("/getWebList")
	public PageInfo<WebList> getWebList(int page,int pageSize,WebList webList) {      
		PageHelper.startPage(page, pageSize);
		PageInfo<WebList> pageList = new PageInfo<WebList>(profileService.getWebList(webList), pageSize);
		return pageList;
	}

	/**
	 * 根据id获取网站黑白名单
	 * 
	 * @author OuYang
	 * @date 2019-09-09
	 * @version 1.0
	 **/
	@ResponseBody
	@RequestMapping("/getWebListById")
	public WebList getWebListById(@RequestParam Long id) {
		return profileService.getWebListById(id);
	}
	
	/**
	 * 根据id删除网站黑白名单
	 * 
	 * @author OuYang
	 * @date 2019-09-09
	 * @version 1.0
	 **/
	@ResponseBody
	@RequestMapping("/delWebListById")
	public Map<String, Object> delWebListById(@RequestParam Long id) {
		Map<String, Object> _res = new HashMap<String, Object>();
		setRes(profileService.delWebListById(id),_res);
		return _res;
	}
	
	/**
	 * 保存文字广播
	 * 
	 * @author OuYang
	 * @date 2019-09-09
	 * @version 1.0
	 **/
	@ResponseBody
	@RequestMapping("/saveBroadcastInfo")
	public Map<String, Object> saveBroadcastInfo(@DateTimeFormat (pattern="yyyy-MM-dd HH:mm:ss")@RequestParam Date beginTime,
			@DateTimeFormat (pattern="yyyy-MM-dd HH:mm:ss")@RequestParam Date endTime,String broadcastSpeed,
			String broadcastText,Long schoolCode,String buildClassCodes) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> list = JSONObject.parseObject(buildClassCodes, List.class);
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				List<Map<String, Object>> classList = JSONObject.parseObject(map.get("list").toString(), List.class);
				for (int j = 0; j < classList.size(); j++) {
					Map<String, Object> map2 = classList.get(j);
					BroadcastInfo broadcastInfo = new BroadcastInfo();
					broadcastInfo.setSchoolCode(schoolCode);
					broadcastInfo.setBeginTime(beginTime);
					broadcastInfo.setEndTime(endTime);
					broadcastInfo.setBroadcastSpeed(broadcastSpeed);
					broadcastInfo.setBroadcastText(broadcastText);
					broadcastInfo.setBuildCode(Long.valueOf(map.get("buildCode").toString()));
					broadcastInfo.setBuildName(map.get("buildName").toString());
					broadcastInfo.setClassCode(Long.valueOf(map2.get("classCode").toString()));
					broadcastInfo.setClassName(map2.get("className").toString());
					profileService.saveBroadcastInfo(broadcastInfo);
				}
			}
			result.put("code", 1);
			result.put("msg", "操作成功！");
		} catch (NumberFormatException e) {
			result.put("code", 2);
			result.put("msg", "操作失败！");
			e.printStackTrace();
		}
		return result; 		
	}
	
	
	/**
	 * 根据id获取文字广播
	 * 
	 * @author leaf
	 * @date 2019-11-14
	 * @version 1.0
	 **/
	@ResponseBody
	@RequestMapping("/getBroadcastById")
	public BroadcastInfo getBroadcastById(@RequestParam Long id) {
		return profileService.getBroadcastById(id);
	}
	
	/**
	 * 根据ids删除文字广播
	 * 
	 * @author leaf
	 * @date 2019-11-14
	 * @version 1.0
	 **/
	@ResponseBody
	@RequestMapping("/deleteBroadcastById")
	public Map<String, Object> deleteBroadcastById(String ids) {
		Map<String, Object> _res = new HashMap<String, Object>();
		setRes(profileService.deleteBroadcastById(ids),_res);
		return _res;
	}
	
	/**
	 * 文字广播分页
	 * 
	 * @author leaf
	 * @date 2019-11-14
	 * @version 1.0
	 **/
	@ResponseBody
	@RequestMapping("/getBroadcastInfoPage")
	public PageInfo<BroadcastInfo> getBroadcastInfoPage(int page,int pageSize,BroadcastInfo broadcastInfo) {
		PageHelper.startPage(page, pageSize);
		PageInfo<BroadcastInfo> pageList = new PageInfo<BroadcastInfo>(profileService.getBroadcastInfoPage(broadcastInfo), pageSize);
		return pageList;	
	}
	
	public void setRes(int i, Map<String, Object> _res) {
		if (i != 0) {
			_res.put("code", 1);
			_res.put("msg", "操作成功！");
		} else {
			_res.put("code", 2);
			_res.put("msg", "操作失败！");
		}
	}
}
