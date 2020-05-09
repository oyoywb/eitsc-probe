package com.alimu.probe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alimu.probe.dao.ProfileDao;
import com.alimu.probe.entity.BroadcastInfo;
import com.alimu.probe.entity.ConfInfo;
import com.alimu.probe.entity.SoftList;
import com.alimu.probe.entity.WebList;
import com.alimu.probe.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {
	@Autowired
	private ProfileDao profileDao;

	@Override
	public int saveOrUpdateConfInfo(ConfInfo confInfo) {
		if (profileDao.getConfInfoBySchoolId(confInfo.getSchoolCode()) != null) {
			return profileDao.updateConfInfo(confInfo);
		} else
			return profileDao.saveConfInfo(confInfo);
	}

	@Override
	public ConfInfo getConfInfoByschoolCode(Long schoolCode) {
		return profileDao.getConfInfoBySchoolId(schoolCode);
	}

	@Override
	public int saveOrUpdateSoftList(SoftList softList) {
		if (softList.getId()!=null) {
			return profileDao.updateSoftList(softList);
		} else {
			return profileDao.saveSoftList(softList);
		}
	}

	@Override
	public List<SoftList> getSoftList(SoftList softList) {
		return profileDao.getSoftList(softList);
	}

	@Override
	public int delSoftListById(Long id) {
		return profileDao.delSoftListById(id);
	}

	@Override
	public int saveOrUpdateWebList(WebList webList) {
		if (webList.getId()!=null) {
			return profileDao.updateWebList(webList);
		} else {
			return profileDao.saveWebList(webList);
		}
	}

	@Override
	public List<WebList> getWebList(WebList webList) {
		return profileDao.getWebList(webList);
	}

	@Override
	public int delWebListById(Long id) {
		return profileDao.delWebListById(id);
	}

	@Override
	public int saveBroadcastInfo(BroadcastInfo broadcastInfo) {		
		return profileDao.saveBroadcastInfo(broadcastInfo);
	}

	@Override
	public SoftList getSoftListById(Long id) {
		return profileDao.getSoftListById(id);
	}

	@Override
	public WebList getWebListById(Long id) {
		return profileDao.getWebListById(id);
	}

	@Override
	public List<BroadcastInfo> getBroadcastInfoPage(BroadcastInfo broadcastInfo) {
		return profileDao.getBroadcastInfoPage(broadcastInfo);
	}

	@Override
	public BroadcastInfo getBroadcastById(Long id) {
		return profileDao.getBroadcastById(id);
	}

	@Override
	public int deleteBroadcastById(String ids) {
		return profileDao.deleteBroadcastById(ids);
	}

}
