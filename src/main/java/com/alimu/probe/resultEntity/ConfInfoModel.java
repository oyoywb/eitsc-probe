package com.alimu.probe.resultEntity;

import java.io.Serializable;

public class ConfInfoModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -48903474467485568L;
	private Long id;
	private Long schoolcode;
	private Float cpurate;
	private Float ramlevel;
	private Float disklevel;
	private Integer vediodur;
	private Integer screennum;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSchoolcode() {
		return schoolcode;
	}
	public void setSchoolcode(Long schoolcode) {
		this.schoolcode = schoolcode;
	}
	public Float getCpurate() {
		return cpurate;
	}
	public void setCpurate(Float cpurate) {
		this.cpurate = cpurate;
	}
	public Float getRamlevel() {
		return ramlevel;
	}
	public void setRamlevel(Float ramlevel) {
		this.ramlevel = ramlevel;
	}
	public Float getDisklevel() {
		return disklevel;
	}
	public void setDisklevel(Float disklevel) {
		this.disklevel = disklevel;
	}
	public Integer getVediodur() {
		return vediodur;
	}
	public void setVediodur(Integer vediodur) {
		this.vediodur = vediodur;
	}
	public Integer getScreennum() {
		return screennum;
	}
	public void setScreennum(Integer screennum) {
		this.screennum = screennum;
	}
}
