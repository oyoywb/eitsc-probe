package com.alimu.probe.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class ConfInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long schoolCode;
	private Float cpuRate;
	private Float ramLevel;
	private Float diskLevel;
	private Integer vedioDur;
	private Integer screenNum;
	private String warningmsg;			//网站/软件黑名单弹幕警告信息
	private Integer duration;		//警告信息时长,单位:秒
	public String getWarningmsg() {
		return warningmsg;
	}
	public void setWarningmsg(String warningmsg) {
		this.warningmsg = warningmsg;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
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
	public Float getCpuRate() {
		return cpuRate;
	}
	public void setCpuRate(Float cpuRate) {
		this.cpuRate = cpuRate;
	}
	public Float getRamLevel() {
		return ramLevel;
	}
	public void setRamLevel(Float ramLevel) {
		this.ramLevel = ramLevel;
	}
	public Float getDiskLevel() {
		return diskLevel;
	}
	public void setDiskLevel(Float diskLevel) {
		this.diskLevel = diskLevel;
	}
	public Integer getVedioDur() {
		return vedioDur;
	}
	public void setVedioDur(Integer vedioDur) {
		this.vedioDur = vedioDur;
	}
	public Integer getScreenNum() {
		return screenNum;
	}
	public void setScreenNum(Integer screenNum) {
		this.screenNum = screenNum;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
