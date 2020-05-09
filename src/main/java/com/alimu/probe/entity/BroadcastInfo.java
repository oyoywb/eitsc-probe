package com.alimu.probe.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class BroadcastInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long schoolCode;
	private String schoolName;
	private Long buildCode;
	private String buildName;
	private Long classCode;
	private String className;
	private Date beginTime;
	private Date endTime;
	private String broadcastSpeed;
	private String broadcastText;
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
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public Long getBuildCode() {
		return buildCode;
	}
	public void setBuildCode(Long buildCode) {
		this.buildCode = buildCode;
	}
	public String getBuildName() {
		return buildName;
	}
	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}
	public Long getClassCode() {
		return classCode;
	}
	public void setClassCode(Long classCode) {
		this.classCode = classCode;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getBroadcastSpeed() {
		return broadcastSpeed;
	}
	public void setBroadcastSpeed(String broadcastSpeed) {
		this.broadcastSpeed = broadcastSpeed;
	}
	public String getBroadcastText() {
		return broadcastText;
	}
	public void setBroadcastText(String broadcastText) {
		this.broadcastText = broadcastText;
	}
	
	
	
}
