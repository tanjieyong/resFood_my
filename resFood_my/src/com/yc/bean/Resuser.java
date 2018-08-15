package com.yc.bean;

import java.io.Serializable;

public class Resuser implements Serializable {
	private static final long serialVersionUID = 1676684284807690359L;
	private Integer userid;
	private String username;
	private String pwd;
	private String email;
	private String repwd;
	private double dealCount;

	public double getDealCount() {
		return dealCount;
	}

	public void setDealCount(double dealCount) {
		this.dealCount = dealCount;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRepwd() {
		return repwd;
	}

	public void setRepwd(String repwd) {
		this.repwd = repwd;
	}

	@Override
	public String toString() {
		return "Resuser [userid=" + userid + ", username=" + username
				+ ", pwd=" + pwd + ", email=" + email + ", repwd=" + repwd
				+ ", dealCount=" + dealCount + "]";
	}

}
