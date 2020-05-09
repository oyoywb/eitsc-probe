package com.alimu.probe.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class SoftList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  Long id;
	private Integer isWhite;
	private String softwareName;
	private Integer level;
	private String warning;
	private Long schoolCode;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getIsWhite() {
		return isWhite;
	}
	public void setIsWhite(Integer isWhite) {
		this.isWhite = isWhite;
	}
	public String getSoftwareName() {
		return softwareName;
	}
	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getWarning() {
		return warning;
	}
	public void setWarning(String warning) {
		this.warning = warning;
	}
	public Long getSchoolCode() {
		return schoolCode;
	}
	public void setSchoolCode(Long schoolCode) {
		this.schoolCode = schoolCode;
	}
	
}
