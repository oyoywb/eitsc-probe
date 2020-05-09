package com.alimu.probe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alimu.probe.dao.BindDao;
import com.alimu.probe.entity.ProbeBind;
import com.alimu.probe.service.BindService;

@Service
public class BindServiceImpl implements BindService {
	@Autowired
	BindDao bindDao;

	@Override
	public List<ProbeBind> getProbeBind(Long schoolCode) {
		return bindDao.getProbeBind(schoolCode);
	}

	@Override
	public int saveOrUpdateProbeBind(ProbeBind probeBind) {
		if (probeBind.getId() == null)
			return bindDao.saveProbeBind(probeBind);
		else {
			return bindDao.updateProbeBind(probeBind);
		}
	}

	@Override
	public int delProbeBind(Long id) {
		return bindDao.delProbeBind(id);
	}


	@Override
	public ProbeBind getProbeBindById(Long id) {
		return bindDao.getProbeBindById(id);
	}

}
