package com.yc.bean;

import java.io.Serializable;

public class Resfood extends CommonBean implements Serializable {

	private static final long serialVersionUID = 5808270400645134597L;
	private Integer fid;
	private String fname;
	private double normprice;
	private double realprice;
	private String detail;
	private String fphoto;
	
	private int sellcount;
	
	

	public int getSellcount() {
		return sellcount;
	}

	public void setSellcount(int sellcount) {
		this.sellcount = sellcount;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public double getNormprice() {
		return normprice;
	}

	public void setNormprice(double normprice) {
		this.normprice = normprice;
	}

	public double getRealprice() {
		return realprice;
	}

	public void setRealprice(double realprice) {
		this.realprice = realprice;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getFphoto() {
		return fphoto;
	}

	public void setFphoto(String fphoto) {
		this.fphoto = fphoto;
	}

	@Override
	public String toString() {
		return "Resfood [fid=" + fid + ", fname=" + fname + ", normprice="
				+ normprice + ", realprice=" + realprice + ", detail=" + detail
				+ ", fphoto=" + fphoto + "]";
	}

}
