package com.alimu.probe.web;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.alimu.probe.entity.ProbeDownloadInfo;
import com.alimu.probe.entity.ProbeInfo;
import com.alimu.probe.service.VersionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("version")
public class VersionController {
	@Autowired
	private VersionService versionService;

	/**
	 * 保存或者更新版本信息
	 * 
	 * @author OuYang
	 * @date 2019-09-09
	 * @version 1.0
	 * @throws Exception
	 **/
	@ResponseBody
	@RequestMapping("/saveOrUpdateVersion")
	public Map<String, Object> saveOrUpdateVersion(@RequestBody ProbeInfo probeInfo){
		Map<String, Object> _res = new HashMap<String, Object>();
		setRes(versionService.saveOrUpdateProbe(probeInfo), _res);
		return _res;
	}

	/**
	 * 分页查询所有版本信息
	 * 
	 * @author OuYang
	 * @date 2019-09-09
	 * @version 1.0
	 **/
	@ResponseBody
	@RequestMapping(value = "/getAllProbeInfo")
	public PageInfo<ProbeInfo> getAllProbeInfo(Integer page,Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		PageInfo<ProbeInfo> pageList = new PageInfo<ProbeInfo>(versionService.getAllProbeInfo(), pageSize);
		return pageList;
	}

	/**
	 * 删除探针版本信息
	 * 
	 * @author OuYang
	 * @date 2019-09-09
	 * @version 1.0
	 **/
	@ResponseBody
	@RequestMapping("/delectProbeInfoById")
	public Map<String, Object> delectProbeInfoById(@RequestParam("id") Long id) {
		Map<String, Object> _res = new HashMap<String, Object>();
		setRes(versionService.deleteProbeInfoById(id), _res);
		return _res;

	}

	/**
	 * 根据id查询探针版本信息
	 * 
	 * @author OuYang
	 * @date 2019-09-09
	 * @version 1.0
	 **/
	@ResponseBody
	@RequestMapping("/getProbeInfoById")
	public ProbeInfo getProbeInfoById(@RequestParam Long id) {
		return versionService.getProbeInfoById(id);
	}

	@ResponseBody
	@RequestMapping("/getAllProbeDownloadInfoByFactor")
	public PageInfo<ProbeDownloadInfo> getProbeDownloadInfoByFactor(int page,int pageSize,
			ProbeDownloadInfo probeDownloadInfo) {
		PageHelper.startPage(page,pageSize);
		PageInfo<ProbeDownloadInfo> pageList = new PageInfo<ProbeDownloadInfo>(
				versionService.getProbeDownloadInfoByFactor(probeDownloadInfo), pageSize);
		return pageList;
	}

	/**
	 * 上传探针文件至服务器
	 * 
	 * @author OuYang
	 * @date 2019-09-09
	 * @version 1.0
	 **/
	@RequestMapping("uploadFile")
	@ResponseBody
	public String uploadfile(HttpServletRequest request) throws Exception {
		Map<String, Object> _res = new HashMap<String, Object>();
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("file");

			//获取服务器图片存储路径
			String rootDir = request.getSession().getServletContext().getRealPath("/");
			rootDir = rootDir.substring(0, rootDir.lastIndexOf(File.separator));
			rootDir = rootDir.substring(0, rootDir.lastIndexOf(File.separator)+1) + "files"+File.separator;
			System.out.println("路径为："+rootDir);

			Random random = new Random();
			int randomName = random.nextInt(100)+1;
			String origName = file.getOriginalFilename();
			String bkName = origName.substring(origName.indexOf("."), origName.length());
			String newName = new Date().getTime()+"_"+randomName+bkName;
			//生成文件路径和名字，存储在项目根目录下的
			File tempFile = new File(rootDir+File.separator+newName);
			if (!tempFile.exists()) {
				tempFile.mkdirs();
			}
			file.transferTo(tempFile);
			//返回文件路径url
	        StringBuffer url = request.getRequestURL();  
	        String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();
			_res.put("imgPath", tempContextUrl +"files/"+newName);
			_res.put("code", 1);
			_res.put("msg", "操作成功！");
		} catch (Exception e) {
			_res.put("code", 2);
			_res.put("msg", "操作失败！");
			e.printStackTrace();
		}
		return JSONObject.toJSONString(_res);
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
