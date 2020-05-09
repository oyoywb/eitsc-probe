package com.alimu.probe.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class CpuState implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long schoolCode;
	private Long buildCode;
	private Long classCode;
	private String schoolName;
	private String buildName;
	private String className;
	private String pcCode;
	private String pcName;
	private Float cpuRate;
	private Float ramRate;
	private Float diskSpace;
	private String cpuWarning;
	private String ramWarning;
	private String spaceWarning;
	private Date date;

	public CpuState() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(Long schoolCode) {
		this.schoolCode = schoolCode;
	}

	public Long getBuildCode() {
		return buildCode;
	}

	public void setBuildCode(Long buildCode) {
		this.buildCode = buildCode;
	}

	public Long getClassCode() {
		return classCode;
	}

	public void setClassCode(Long classCode) {
		this.classCode = classCode;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getBuildName() {
		return buildName;
	}

	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getPcCode() {
		return pcCode;
	}

	public void setPcCode(String pcCode) {
		this.pcCode = pcCode;
	}

	public String getPcName() {
		return pcName;
	}

	public void setPcName(String pcName) {
		this.pcName = pcName;
	}

	public Float getCpuRate() {
		return cpuRate;
	}

	public void setCpuRate(Float cpuRate) {
		this.cpuRate = cpuRate;
	}

	public Float getRamRate() {
		return ramRate;
	}

	public void setRamRate(Float ramRate) {
		this.ramRate = ramRate;
	}

	public Float getDiskSpace() {
		return diskSpace;
	}

	public void setDiskSpace(Float diskSpace) {
		this.diskSpace = diskSpace;
	}

	public String getCpuWarning() {
		return cpuWarning;
	}

	public void setCpuWarning(String cpuWarning) {
		this.cpuWarning = cpuWarning;
	}

	public String getRamWarning() {
		return ramWarning;
	}

	public void setRamWarning(String ramWarning) {
		this.ramWarning = ramWarning;
	}

	public String getSpaceWarning() {
		return spaceWarning;
	}

	public void setSpaceWarning(String spaceWarning) {
		this.spaceWarning = spaceWarning;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
