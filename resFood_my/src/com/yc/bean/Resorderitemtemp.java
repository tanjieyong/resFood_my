package com.yc.bean;

import java.io.Serializable;

public class Resorderitemtemp implements Serializable{
	private static final long serialVersionUID = -236532532182917643L;
	private Integer roitid;
	private Integer fid;
	private Integer num;
	private String quittime;
	private Integer userid;
	public Integer getRoitid() {
		return roitid;
	}
	public void setRoitid(Integer roitid) {
		this.roitid = roitid;
	}
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getQuittime() {
		return quittime;
	}
	public void setQuittime(String quittime) {
		this.quittime = quittime;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "Resorderitemtemp [roitid=" + roitid + ", fid=" + fid + ", num="
				+ num + ", quittime=" + quittime + ", userid=" + userid + "]";
	}
	
	
}
