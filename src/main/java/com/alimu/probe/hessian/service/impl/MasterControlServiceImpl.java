package com.alimu.probe.hessian.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alimu.probe.dao.BindDao;
import com.alimu.probe.dao.PcMonitoringDao;
import com.alimu.probe.dao.ProfileDao;
import com.alimu.probe.dao.VersionDao;
import com.alimu.probe.entity.BroadcastInfo;
import com.alimu.probe.entity.CpuState;
import com.alimu.probe.entity.PcState;
import com.alimu.probe.entity.ProbeBind;
import com.alimu.probe.entity.ProbeDownloadInfo;
import com.alimu.probe.entity.SoftwareState;
import com.alimu.probe.entity.WebState;
import com.alimu.probe.hessian.service.MasterControlService;
import com.alimu.probe.remote.EquipRemoteService;
import com.alimu.probe.util.PramUtil;
@Service
public class MasterControlServiceImpl implements MasterControlService{
	@Autowired
	private VersionDao versionDao;
	@Autowired
	private ProfileDao profileDao;
	@Autowired
	private BindDao bindDao;
	@Autowired
	private PcMonitoringDao pcMonitoringDao;
	@Autowired
	private EquipRemoteService equipRemoteService;
	
	@Override
	public String getProbeVersion(String version) {
		Map<String ,Object> _res=new HashMap<String, Object>();
		String Lastest_ver=versionDao.getProbeVersion();
		if(Lastest_ver!=null) {
			setRes(1,_res);
			if(version.equals(Lastest_ver)) {
				_res.put("isnew", "1");
			}else{
				_res.put("isnew", "0");
			}
			_res.put("lastestver", Lastest_ver);			
		}else {
			setRes(0,_res);
		}						
		return JSONObject.toJSONString(_res);
	}

	@Override
	public String getProbeFile() {
		return versionDao.getProbeFileUrl();
	}

	@Override
	public String saveProbeDownloadInfo(String requestStr) {
		Map<String ,Object> _res=new LinkedHashMap<String, Object>();
		ProbeDownloadInfo probeDownloadInfo=JSON.parseObject(requestStr, new TypeReference<ProbeDownloadInfo>() {
		});
		setRes(versionDao.saveProbeDownloadInfo(probeDownloadInfo),_res) ;
		return JSONObject.toJSONString(_res)	;
	}
	
	@Override
	public String getProbeBind(Long schoolCode) {
		Map<String ,Object> _res=new LinkedHashMap<String, Object>();
		List<ProbeBind> probeBindList=bindDao.getProbeBind(schoolCode);
		setRes(1, _res);
		_res.put("list", probeBindList);
		return JSONObject.toJSONString(_res);
	}
	
	@Override
	public String getConfVer(Long schoolCode) {
		Map<String, Object> _res = new HashMap<String, Object>();
		List<Map<String, Object>> webList = profileDao.getRemoteWebList(schoolCode);
		List<Map<String, Object>> softList = profileDao.getRemoteSoftList(schoolCode);
		Map<String, Object> conf = profileDao.getConfInfoModelBySchoolCode(schoolCode);
		setRes(1,_res);
		_res.put("conf",PramUtil.changeObjectString(conf));
		_res.put("weblist",  PramUtil.changeListFrom(webList));
		_res.put("softlist", PramUtil.changeListFrom(softList));
		return JSONObject.toJSONString(_res);
	}
	
	
	@Override
	public String saveWebState(WebState webState,InputStream is,String path) {
		Map<String, Object> _res = new LinkedHashMap<String, Object>();
		if (webState != null) {
			WebState dbwebState = pcMonitoringDao.getLastWebState(webState.getSchoolCode(),webState.getWebName(),webState.getPcCode());
			if (dbwebState != null && (webState.getDate().getTime() - dbwebState.getDate().getTime())/1000 < 60 ) {
				return null;
			}
			//从设备运维补充信息
			Map<String, Object> pcInfo = equipRemoteService.getPcInfoDetail(webState.getPcCode(),webState.getSchoolCode());
			if (pcInfo != null && !pcInfo.isEmpty()) {
				webState.setBuildCode(pcInfo.get("build_code") == null?null:Long.valueOf(pcInfo.get("build_code").toString()));
				webState.setBuildName(pcInfo.get("build_name") == null?"":pcInfo.get("build_name").toString());
				webState.setClassCode(pcInfo.get("class_code") == null?null:Long.valueOf(pcInfo.get("class_code").toString()));
				webState.setClassName(pcInfo.get("class_name") == null?"":pcInfo.get("class_name").toString());
				webState.setSchoolName(pcInfo.get("school_name") == null?"":pcInfo.get("school_name").toString());
			}
			if (!StringUtils.isEmpty(webState.getFile())) {
				uploadFile(webState.getFile(),path);
			}
			setRes(pcMonitoringDao.saveWebState(webState),_res);
		} 
		return JSONObject.toJSONString(_res);
	}


	private void uploadFile(String fileName,String path) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		URL url = null;
        InputStream in = null;
        HttpURLConnection httpUrl = null;
        BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			if (!StringUtils.isEmpty(path)) {
				url = new URL(path);
		        httpUrl = (HttpURLConnection) url.openConnection();
		        httpUrl.connect();
		        httpUrl.getInputStream();
		        in = httpUrl.getInputStream();
				//获取服务器图片存储路径
				String rootDir = request.getSession().getServletContext().getRealPath("/");
				rootDir = rootDir.substring(0, rootDir.lastIndexOf(File.separator));
				rootDir = rootDir.substring(0, rootDir.lastIndexOf(File.separator)+1) + "local"+File.separator;
				System.out.println("路径为："+rootDir);
				//生成文件路径和名字，存储在项目根目录下的
				File tempFile = new File(rootDir+File.separator + fileName);
				System.out.println(tempFile.getParentFile().toString());
				if (!tempFile.getParentFile().exists()) {
					tempFile.getParentFile().mkdirs();
				}
				bis = new BufferedInputStream(in);
				bos = new BufferedOutputStream(new FileOutputStream(tempFile));
				int i;
				byte[] buffer = new byte[8192];
				while ((i = in.read(buffer)) != -1) {
					bos.write(buffer, 0, i);
				}
				bos.close();
				bis.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String saveSoftwareState(SoftwareState softwareState, InputStream is,String path) {
		Map<String, Object> _res = new HashMap<String, Object>();
		if (softwareState != null) {
			//从设备运维补充信息
			Map<String, Object> pcInfo = equipRemoteService.getPcInfoDetail(softwareState.getPcCode(),softwareState.getSchoolCode());
			if (pcInfo != null && !pcInfo.isEmpty()) {
				softwareState.setBuildCode(pcInfo.get("build_code") == null?null:Long.valueOf(pcInfo.get("build_code").toString()));
				softwareState.setBuildName(pcInfo.get("build_name") == null?"":pcInfo.get("build_name").toString());
				softwareState.setClassCode(pcInfo.get("class_code") == null?null:Long.valueOf(pcInfo.get("class_code").toString()));
				softwareState.setClassName(pcInfo.get("class_name") == null?"":pcInfo.get("class_name").toString());
				softwareState.setSchoolCode(pcInfo.get("school_code") == null?null:Long.valueOf(pcInfo.get("school_code").toString()));
				softwareState.setSchoolName(pcInfo.get("school_name") == null?"":pcInfo.get("school_name").toString());
			}
			setRes(pcMonitoringDao.saveSoftwareState(softwareState),_res);
			if (!StringUtils.isEmpty(softwareState.getFile())) {
				uploadFile(softwareState.getFile(),path);
			}
		} 
		return JSONObject.toJSONString(_res);
	}

	@Override
	public String getBroadcastInfo(Long schoolCode) {
		Map<String, Object> _res = new LinkedHashMap<String, Object>();
		List<BroadcastInfo> broadcastInfoList=profileDao.getBroadcastInfo(schoolCode);
		if(broadcastInfoList!=null) {
			setRes(1,_res);
			_res.put("list", broadcastInfoList);
		}else {
			setRes(0,_res);
		}
		return (JSONObject.toJSONString(_res));
	}

	@Override
	public String savePcState(PcState pcState) {
		Map<String, Object> _res = new LinkedHashMap<String, Object>();
		if (pcState != null) {
			//从设备运维补充信息
			Map<String, Object> pcInfo = equipRemoteService.getPcInfoDetail(pcState.getPcCode(),pcState.getSchoolCode());
			if (pcInfo != null && !pcInfo.isEmpty()) {
				pcState.setBuildCode(pcInfo.get("build_code") == null?null:Long.valueOf(pcInfo.get("build_code").toString()));
				pcState.setBuildName(pcInfo.get("build_name") == null?"":pcInfo.get("build_name").toString());
				pcState.setClassCode(pcInfo.get("class_code") == null?null:Long.valueOf(pcInfo.get("class_code").toString()));
				pcState.setClassName(pcInfo.get("class_name") == null?"":pcInfo.get("class_name").toString());
				pcState.setSchoolCode(pcInfo.get("school_code") == null?null:Long.valueOf(pcInfo.get("school_code").toString()));
				pcState.setSchoolName(pcInfo.get("school_name") == null?"":pcInfo.get("school_name").toString());
				pcState.setPcName(pcInfo.get("name") == null?"":pcInfo.get("name").toString());
			}
			setRes(pcMonitoringDao.savePcState(pcState),_res);
		}
		return JSONObject.toJSONString(_res);
	}
	
	public String saveCpuState(CpuState cpuState) {
		Map<String, Object> _res = new LinkedHashMap<String, Object>();
		if (cpuState != null) {
			//从设备运维补充信息
			Map<String, Object> pcInfo = equipRemoteService.getPcInfoDetail(cpuState.getPcCode(),cpuState.getSchoolCode());
			if (pcInfo != null && !pcInfo.isEmpty()) {
				cpuState.setBuildCode(pcInfo.get("build_code") == null?null:Long.valueOf(pcInfo.get("build_code").toString()));
				cpuState.setBuildName(pcInfo.get("build_name") == null?"":pcInfo.get("build_name").toString());
				cpuState.setClassCode(pcInfo.get("class_code") == null?null:Long.valueOf(pcInfo.get("class_code").toString()));
				cpuState.setClassName(pcInfo.get("class_name") == null?"":pcInfo.get("class_name").toString());
				cpuState.setSchoolCode(pcInfo.get("school_code") == null?null:Long.valueOf(pcInfo.get("school_code").toString()));
				cpuState.setSchoolName(pcInfo.get("school_name") == null?"":pcInfo.get("school_name").toString());
				cpuState.setPcName(pcInfo.get("name") == null?"":pcInfo.get("name").toString());
			}
			setRes(pcMonitoringDao.saveCpuState(cpuState),_res);
		}
		return JSONObject.toJSONString(_res);
	}
	
	
	
	public void upload(String fileName, InputStream is) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String path = request.getSession().getServletContext().getRealPath("/");
		path = path.substring(0, path.lastIndexOf(File.separator));
		path = path.substring(0, path.lastIndexOf(File.separator)+1) + "local"+File.separator;
        //String path=request.getSession().getServletContext().getRealPath("/file/")+fileName;
        File filepath=new File(path+File.separator + fileName);
        if (!filepath.getParentFile().exists()) {
			filepath.getParentFile().mkdirs();
		}
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		if (fileName != null) {
			try {
				bis = new BufferedInputStream(is);
				bos = new BufferedOutputStream(new FileOutputStream(filepath));
				int i;
				byte[] buffer = new byte[8192];
				while ((i = is.read(buffer)) != -1) {
					bos.write(buffer, 0, i);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (bos != null) {
					try {
						bos.close();
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
				if (bis != null) {
					try {
						bis.close();
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
			}
		}

	}
	public void setRes(int i,Map<String,Object> _res) {
		if(i!=0) {
			_res.put("code", "0");
			_res.put("info", "success");
		}else {
			_res.put("code", "1");
			_res.put("info", "fail");
		}
	}

	@Override
	public String getBroadcastInfoByPcMac(String pcMac) {
		Map<String, Object> _res = new LinkedHashMap<String, Object>();
		List<Map<String, Object>> list = profileDao.getBroadcastInfoByPcMac(pcMac);
		if(list != null) {
			setRes(1,_res);
			_res.put("list", PramUtil.changeListFrom(list));
		}else {
			setRes(0,_res);
		}
		return (JSONObject.toJSONString(_res));
	}

	@Override
	public String getProbeBindByMac(String pcMac) {
		Map<String, Object> _res = new HashMap<String, Object>();
		try {
			ProbeBind probeBind = bindDao.getProbeBindByMac(pcMac);
			_res.put("pccode", probeBind.getPcCode());
			_res.put("pcip", probeBind.getPcIp());
			_res.put("pcmac", probeBind.getPcMac());
			setRes(1, _res);
		} catch (Exception e) {
			setRes(0, _res);
		}
		return JSONObject.toJSONString(_res);
	}

	@Override
	public String synTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String ,Object> _res = new HashMap<String, Object>();
		_res.put("time", sdf.format(new Date()));
		setRes(1, _res);
		return JSONObject.toJSONString(_res);
	}

	
	@Override
	public void saveMonitorData(Long schoolCode) {
		Date date = new Date();
		Random r = new Random();
		List<PcState> pcStatePage = pcMonitoringDao.getPcStateList(schoolCode);
		for (int i = 0; i < Math.floor(Math.random() * (r.nextInt(pcStatePage.size()) - 4)) + 4.0; i++) {
			pcStatePage.get(i).setDate(date);
			pcMonitoringDao.savePcState(pcStatePage.get(i));
		}
		List<CpuState> cpuStates = pcMonitoringDao.getCpuStateList(schoolCode);
		for (int j = 0; j < Math.floor(Math.random() * (r.nextInt(cpuStates.size()) - 4)) + 4.0; j++) {
			cpuStates.get(j).setDate(date);
			pcMonitoringDao.saveCpuState(cpuStates.get(j));
		}
		List<WebState> webStates = pcMonitoringDao.getWebStateList(schoolCode);
		for (int k = 0; k < Math.floor(Math.random() * (r.nextInt(webStates.size()) - 4)) + 4.0; k++) {
			webStates.get(k).setDate(date);
			pcMonitoringDao.saveWebState(webStates.get(k));
		}
		List<SoftwareState> softList = pcMonitoringDao.getSoftwareStateList(schoolCode);
		for (int m = 0; m < Math.floor(Math.random() * (r.nextInt(softList.size()) - 4)) + 4.0; m++) {
			softList.get(m).setDate(date);
			pcMonitoringDao.saveSoftwareState(softList.get(m));
		}
		
	}

}
