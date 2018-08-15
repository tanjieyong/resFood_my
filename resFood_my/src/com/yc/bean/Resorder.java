package com.yc.bean;

import java.io.Serializable;

public class Resorder implements Serializable {
	private static final long serialVersionUID = 771590906884603610L;
	private Integer roid;
	private Integer userid;
	private String address;
	private String tel;
	private String ordertime;
	private String deliverytime;
	private String ps;
	private Integer status;

	private Integer page;
	private Integer rows;
	private String sort;
	private String order;

	

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Integer getRoid() {
		return roid;
	}

	public void setRoid(Integer roid) {
		this.roid = roid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}

	public String getDeliverytime() {
		return deliverytime;
	}

	public void setDeliverytime(String deliverytime) {
		this.deliverytime = deliverytime;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Resorder [roid=" + roid + ", userid=" + userid + ", address="
				+ address + ", tel=" + tel + ", ordertime=" + ordertime
				+ ", deliverytime=" + deliverytime + ", ps=" + ps + ", status="
				+ status + "]";
	}

}
