package com.alimu.probe.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class WebState implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8031496572943948660L;
	/**
	 * 
	 */

	private Long id;
	private Long schoolCode;
	private String schoolName;
	private Long buildCode;
	private String buildName;
	private Long classCode;
	private String className;
	private String pcCode;
	public String getPcCode() {
		return pcCode;
	}
	public void setPcCode(String pcCode) {
		this.pcCode = pcCode;
	}
	private String title;
	private String webName;
	private Integer level;
	private Date date;
	private String warning ;
	private String ban;
	private String file;
	private Integer isWhite;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWebName() {
		return webName;
	}
	public void setWebName(String webName) {
		this.webName = webName;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getWarning() {
		return warning;
	}
	public void setWarning(String warning) {
		this.warning = warning;
	}
	public String getBan() {
		return ban;
	}
	public void setBan(String ban) {
		this.ban = ban;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public Integer getIsWhite() {
		return isWhite;
	}
	public void setIsWhite(Integer isWhite) {
		this.isWhite = isWhite;
	}
}
