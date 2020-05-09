package com.alimu.probe.web;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alimu.probe.entity.ProbeBind;
import com.alimu.probe.remote.EquipRemoteService;
import com.alimu.probe.service.BindService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("bind")
public class BindController {
	@Autowired
	private BindService bindService;
	
	@Autowired
	private EquipRemoteService equipRemoteService;
	
	/**
	 * 保存或者更新探针绑定信息
	 * 
	 * @author OuYang
	 * @date 2019-09-09
	 * @version 1.0
	 **/
	@RequestMapping("/saveOrUpdateProbeBind")
	@ResponseBody
	public Map<String,Object> saveOrUpdateProbeBind(@RequestBody ProbeBind probeBind) {
		Map<String, Object> _res = new HashMap<String, Object>();
		setRes(bindService.saveOrUpdateProbeBind(probeBind),_res);
		return _res;
	}
	
	/**
	 * 删除探针绑定信息
	 * 
	 * @author OuYang
	 * @date 2019-09-09
	 * @version 1.0
	 **/
	@RequestMapping("/delProbeBindById/{ids}")
	@ResponseBody
	public Map<String,Object> delProbeBind(@PathVariable("ids") String ids) {	
		if (ids.contains(",")){
            String[] strings = ids.split(",");
            //组装id的集合
            for (String id : strings) {
                bindService.delProbeBind(Long.parseLong(id));
            }
        }else {
        	bindService.delProbeBind(Long.parseLong(ids));
        }		
		Map<String, Object> _res = new HashMap<String, Object>();
		setRes(1,_res);
		return _res;
	}
	
	/**
	 * 获取探针绑定信息
	 * 
	 * @author OuYang
	 * @date 2019-09-09
	 * @version 1.0
	 **/
	@RequestMapping(value="/getProbeBind")
	@ResponseBody
	public PageInfo<ProbeBind> getProbeBind(int page,int pageSize,Long schoolCode) {
		PageHelper.startPage(page, pageSize);
		PageInfo<ProbeBind> pageList = new PageInfo<ProbeBind>(bindService.getProbeBind(schoolCode),pageSize);
		return pageList;
	}
	/**
	 *批量导入探针绑定信息
	 * 
	 * @author OuYang
	 * @date 2019-09-09
	 * @version 1.0
	 **/
	@RequestMapping("/importProbeBind")
	@ResponseBody
	public Map<String, Object> importProbeBind(String bindList) {
		Map<String, Object> _res = new HashMap<String, Object>();
		try {
			List<ProbeBind> list = JSONObject.parseArray(bindList, ProbeBind.class);
			if (list != null && !list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					bindService.saveOrUpdateProbeBind(list.get(i));
				}
			}
			_res.put("code", 1);
			_res.put("msg", "操作成功！");
		} catch (Exception e) {
			_res.put("code", 2);
			_res.put("msg", "操作失败！");
			e.printStackTrace();
		}
		return _res;
	}
	/**
	 *根据id获取探针绑定信息
	 * 
	 * @author OuYang
	 * @date 2019-09-09
	 * @version 1.0
	 **/
	@RequestMapping("/getProbeBindById")
	@ResponseBody
	public ProbeBind getProbeBindById(@RequestParam Long id) {
		return bindService.getProbeBindById(id);		
	}
	
	
	/**
	 *获取机构列表
	 * @author leaf
	 * @date 2019-11-22
	 * @version 1.0
	 **/
	@RequestMapping("/getSchoolCodeList")
	@ResponseBody
	public String getSchoolCodeList() {
		return equipRemoteService.getSchoolCodeList();
	}
	
	/**
	 * 根据学校编码获取设备列表
	 * @author leaf
	 * @date 2019-11-22
	 * @version 1.0
	 **/
	@RequestMapping("/getPcEquipList")
	@ResponseBody
	public String getPcEquipList(Long schoolCode) {
		return equipRemoteService.getPcEquipList(schoolCode);
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
