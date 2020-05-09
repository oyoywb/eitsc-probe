package com.alimu.probe.service;

import java.util.List;

import com.alimu.probe.entity.ProbeBind;

public interface BindService {
	List<ProbeBind> getProbeBind(Long schoolCode);
	int saveOrUpdateProbeBind(ProbeBind probeBind);
	int delProbeBind(Long id);
	ProbeBind getProbeBindById(Long id);
}
