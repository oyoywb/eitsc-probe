package com.alimu.probe.dao;

import java.util.List;




import org.apache.ibatis.annotations.Param;

import com.alimu.probe.entity.ProbeBind;

public interface BindDao {
	List<ProbeBind> getProbeBind(@Param(value="schoolCode")Long schoolCode);
	int saveProbeBind(ProbeBind probeBind);
	int updateProbeBind(ProbeBind probeBind);
	int delProbeBind(Long id);
	ProbeBind getProbeBindById(Long id);
	ProbeBind getProbeBindByMac(String pcMac);
}
