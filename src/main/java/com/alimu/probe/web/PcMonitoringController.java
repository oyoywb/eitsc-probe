package com.alimu.probe.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.alimu.probe.entity.PcState;
import com.alimu.probe.entity.SoftwareState;
import com.alimu.probe.entity.WebState;
import com.alimu.probe.remote.EquipRemoteService;
import com.alimu.probe.service.PcMonitoringService;
import com.alimu.probe.util.ConvertVideoUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("pcMonitoring")
public class PcMonitoringController {
	@Autowired
	private PcMonitoringService pcMonitoringService;
	@Autowired
	private EquipRemoteService equipRemoteService;

	/**
	 * 获取网站监控记录
	 * 
	 * @author OuYang
	 * @date 2019-09-16
	 * @version 1.0
	 **/
	@ResponseBody
	@RequestMapping(value = "/getWebState")
	public PageInfo<WebState> getWebState(int page,int pageSize,
			 WebState webState) {
		PageHelper.startPage(page, pageSize);
		PageInfo<WebState> pageList = new PageInfo<WebState>(pcMonitoringService.getWebState(webState), pageSize);// 分页
		return pageList;
	}

	
	/**
	 * 根据获取网站监控记录
	 * 
	 * @author OuYang
	 * @date 2019-09-16
	 * @version 1.0
	 **/
	@ResponseBody
	@RequestMapping(value = "/getWebStateById")
	public Map<String, Object> getWebStateById(@RequestParam Long id,HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		WebState webState = pcMonitoringService.getWebStateById(id);
		String fileName = webState.getFile();
		if (!StringUtils.isEmpty(fileName)){
			StringBuffer url = request.getRequestURL();
			if (fileName.indexOf("avi") > 0) {
				String path = request.getSession().getServletContext().getRealPath("/");
				path = path.substring(0, path.lastIndexOf(File.separator));
				path = path.substring(0, path.lastIndexOf(File.separator)+1) + "local"+File.separator;
				String source = path + File.separator + fileName;
				ConvertVideoUtil.convertVideoToMP4(source, source.substring(0, source.lastIndexOf(".")) + ".mp4");
				fileName = fileName.substring(0, fileName.lastIndexOf(".")) + ".mp4";
				webState.setFile(fileName);
				pcMonitoringService.updateWebState(webState);
				url = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/");
				resultMap.put("file", url + "local/" + fileName);
			}else{
				url = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/");
				resultMap.put("file", url + "local/" + fileName);
			}
		}else{
			resultMap.put("file","");
		}
		resultMap.put("webState", webState);
		
		return resultMap;
	}
	
	
	/**
	 * 删除网站监控记录
	 * 
	 * @author OuYang
	 * @date 2019-09-16
	 * @version 1.0
	 **/
	@ResponseBody
	@RequestMapping("/delWebState")
	public Map<String, Object> delWebState(@RequestParam Long id) {
		Map<String, Object> _res = new HashMap<String, Object>();
		setRes(pcMonitoringService.delWebState(id), _res);
		return _res;
	}

	/**
	 * 批量导出网站监控记录
	 * 
	 * @author OuYang
	 * @date 2019-09-16
	 * @version 1.0
	 **/
	@RequestMapping("/exportWebState")
	@ResponseBody
	public Map<String, Object> exportWebState(@DateTimeFormat (pattern="yyyy-MM-dd")@RequestParam Date startDate,
			@DateTimeFormat (pattern="yyyy-MM-dd")@RequestParam Date endDate,Long schoolCode,Long buildCode) {
		
		Map<String, Object> _res = new HashMap<String, Object>();
		try {
			List<WebState> list = pcMonitoringService.getWebStateByFactors(schoolCode, buildCode, startDate, endDate);
			_res.put("code", 1);
			_res.put("msg", "操作成功！");
			_res.put("list", list);
		} catch (Exception e) {
			_res.put("code", 2);
			_res.put("msg", "操作失败！");
			e.printStackTrace();
		}
		return _res;
	}

	/**
	 * 获取软件监控记录
	 * 
	 * @author OuYang
	 * @date 2019-09-16
	 * @version 1.0
	 **/
	@RequestMapping("/getSoftwareState")
	@ResponseBody
	public PageInfo<SoftwareState> getSoftwareState(int page,int pageSize,
			 SoftwareState softwareState) {
		PageHelper.startPage(page, pageSize);
		PageInfo<SoftwareState> pageList = new PageInfo<SoftwareState>(
				pcMonitoringService.getSoftwareState(softwareState), pageSize);
		return pageList;
	}

	/**
	 * 删除软件监控记录
	 * 
	 * @author OuYang
	 * @date 2019-09-16
	 * @version 1.0
	 **/
	@ResponseBody
	@RequestMapping("/delSoftwareState")
	public Map<String, Object> delSoftwareState(@RequestParam Long id) {
		Map<String, Object> _res = new HashMap<String, Object>();
		setRes(pcMonitoringService.delSoftwareState(id), _res);
		return _res;
	}

	/**
	 * 根据id获取软件监控记录
	 * 
	 * @author OuYang
	 * @date 2019-09-16
	 * @version 1.0
	 **/
	@ResponseBody
	@RequestMapping(value = "/getSoftwareStateById")
	public Map<String, Object> getSoftwareStateById(@RequestParam Long id,HttpServletRequest request) {
		SoftwareState softwareState = pcMonitoringService.getSoftwareStateById(id);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String fileName = softwareState.getFile();
		if (!StringUtils.isEmpty(fileName)) {
			StringBuffer url = request.getRequestURL();
			if (fileName.indexOf("avi") > 0) {
				String path = request.getSession().getServletContext().getRealPath("/");
				path = path.substring(0, path.lastIndexOf(File.separator));
				path = path.substring(0, path.lastIndexOf(File.separator)+1) + "local"+File.separator;
				String source = path + File.separator + fileName;
				ConvertVideoUtil.convertVideoToMP4(source, source.substring(0, source.lastIndexOf(".")) + ".mp4");
				fileName = fileName.substring(0, fileName.lastIndexOf(".")) + ".mp4";
				softwareState.setFile(fileName);
				pcMonitoringService.updateSoftwareState(softwareState);
				url = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/");
				resultMap.put("file", url + "local/" + fileName);
			}else{
				url = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/");
				resultMap.put("file", url + "local/" + fileName);
			}
		}else{
			resultMap.put("file","");
		}
		resultMap.put("softwareState", softwareState);
		return resultMap;
	}

	/**
	 * 批量导出软件监控记录
	 * 
	 * @author OuYang
	 * @date 2019-09-16
	 * @version 1.0
	 **/
	@RequestMapping("/exportSoftwareState")
	@ResponseBody
	public Map<String, Object> exportSoftwareState( @DateTimeFormat (pattern="yyyy-MM-dd")@RequestParam Date startDate,
			@DateTimeFormat (pattern="yyyy-MM-dd")@RequestParam Date endDate,Long schoolCode,Long buildCode) {
			
		Map<String, Object> _res = new HashMap<String, Object>();
		try {
			List<SoftwareState> list = pcMonitoringService.getSoftwareStateByFactors(schoolCode, buildCode, startDate, endDate);
			_res.put("code", 1);
			_res.put("msg", "操作成功！");
			_res.put("list", list);
		} catch (Exception e) {
			_res.put("code", 2);
			_res.put("msg", "操作失败！");
			e.printStackTrace();
		}
		return _res;
	}

	/**
	 * 获取教学楼信息
	 * 
	 * @author OuYang
	 * @date 2019-09-16
	 * @version 1.0
	 **/
	@RequestMapping("/getBuildInfo")
	@ResponseBody
	public String getBuildInfo(Long schoolCode) {
		return equipRemoteService.getBuildInfo(schoolCode);
	}

	@RequestMapping("/getClassroomInfo")
	@ResponseBody
	public String getClassroomInfo(Long schoolCode,Long buildCode) {
		return equipRemoteService.getClassroomInfo(schoolCode,buildCode);
	}

	@RequestMapping("/getClassroomPage")
	@ResponseBody
	public String getClassroomPage(Long schoolCode,String buildCode,Integer page,Integer pageSize) {
		return equipRemoteService.getClassroomPage(schoolCode,buildCode,page,pageSize);
	}
	
	/**
	 * 获取Pc基本信息
	 * 
	 * @author OuYang
	 * @date 2019-09-16
	 * @version 1.0
	 **/
	@RequestMapping("/getPcInfo")
	@ResponseBody
	public String getPcInfo(Integer page,Integer pageSize,Long schoolCode,Long buildCode) {
		return equipRemoteService.getPcInfo(page,pageSize,schoolCode,buildCode);
	}

	//pc实时监控
	@RequestMapping("/getPcState")
	@ResponseBody
	public List<Map<String, Object>> getPcState(Long id,Long schoolCode,Long buildCode,Long classCode,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date date,String pcCode) {
		return pcMonitoringService.getPcState(id,schoolCode,buildCode,classCode,date,pcCode);
	}
	
	/**
	 * 当前pc实时监控的网站,软件浏览记录
	 * @Param id pc实时监控id
	 */
	@RequestMapping("/getPcWebSoftPage")
	@ResponseBody
	public String getPcWebSoftPage(Long id,int page,int pageSize) {
		PcState pcState = pcMonitoringService.getPcStateById(id);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		PageHelper.startPage(page, pageSize);
		PageInfo<SoftwareState> softPage = new PageInfo<SoftwareState>(
				pcMonitoringService.getSoftwareStatePage(pcState.getSchoolCode(),pcState.getPcCode(),pcState.getOpenDate(),pcState.getCloseDate()), pageSize);
		PageInfo<WebState> webPage = new PageInfo<WebState>(
				pcMonitoringService.getWebStatePage(pcState.getSchoolCode(),pcState.getPcCode(),pcState.getOpenDate(),pcState.getCloseDate()), pageSize);
		resultMap.put("softPage", softPage);
		resultMap.put("webPage", webPage);
		return JSONObject.toJSONString(resultMap);
	}
	
	@RequestMapping("/getPcStatePage")
	@ResponseBody
	public PageInfo<PcState> getPcStatePage(int page,int pageSize,PcState pcState) {
		PageHelper.startPage(page, pageSize);
		if (pcState.getDate() == null) {
			pcState.setDate(new Date());
		}
		List<PcState> result = removeDuplicateOutputField(pcMonitoringService.getPcStatePage(pcState));
		PageInfo<PcState> pageList = new PageInfo<PcState>(result, pageSize);
		return pageList;
	}
	
	
	 private List<PcState> removeDuplicateOutputField(List<PcState> list) {
        Set<PcState> set = new TreeSet<>(new Comparator<PcState>() {
            @Override
            public int compare(PcState o1, PcState o2) {
            	return o1.getPcCode().compareTo(o2.getPcCode());
            }
        });
        set.addAll(list);
        return new ArrayList<>(set);
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
