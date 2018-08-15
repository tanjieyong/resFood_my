package com.yc.bean;

import java.io.Serializable;

//关注：分页、排序的参数
public class CommonBean implements Serializable {
	private static final long serialVersionUID = -1220734587096028186L;
	private Integer pages = 1;
	private Integer pagesize = 6;
	private String orderby; // order by 列名  desc
	private String order; // desc / asc

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "CommonBean [pages=" + pages + ", pagesize=" + pagesize
				+ ", orderby=" + orderby + ", order=" + order + "]";
	}

}
