package com.yc.bean;

import java.io.Serializable;

public class Resorderitem implements Serializable {

	private static final long serialVersionUID = -1999741619907070436L;
	private Integer roiid;
	private Integer roid;
	private Integer fid;
	private double dealprice;
	private Integer num;

	private String fname;		
	
	//为什么不能用Double
	private double smallcount;

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public Double getSmallcount() {
		return smallcount;
	}

	public void setSmallcount(Double smallcount) {
		this.smallcount = smallcount;
	}

	public Integer getRoiid() {
		return roiid;
	}

	public void setRoiid(Integer roiid) {
		this.roiid = roiid;
	}

	public Integer getRoid() {
		return roid;
	}

	public void setRoid(Integer roid) {
		this.roid = roid;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public double getDealprice() {
		return dealprice;
	}

	public void setDealprice(double dealprice) {
		this.dealprice = dealprice;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "Resorderitem [roiid=" + roiid + ", roid=" + roid + ", fid="
				+ fid + ", dealprice=" + dealprice + ", num=" + num
				+ ", fname=" + fname + ", smallcount=" + smallcount + "]";
	}

	

}
