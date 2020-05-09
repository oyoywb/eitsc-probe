package com.alimu.probe.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class ProbeBind implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long schoolCode;
	private String pcCode;
	private String pcIp;
	private String pcMac;
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
	public String getPcCode() {
		return pcCode;
	}
	public void setPcCode(String pcCode) {
		this.pcCode = pcCode;
	}
	public String getPcIp() {
		return pcIp;
	}
	public void setPcIp(String pcIp) {
		this.pcIp = pcIp;
	}
	public String getPcMac() {
		return pcMac;
	}
	public void setPcMac(String pcMac) {
		this.pcMac = pcMac;
	}
		
}
