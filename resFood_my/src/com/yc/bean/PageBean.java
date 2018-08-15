package com.yc.bean;

import java.io.Serializable;
import java.util.List;

public class PageBean<T> implements Serializable {

	private static final long serialVersionUID = -9172583037802748848L;
	private Integer pages;
	private Integer pagesize;
	private Long total;
	private Long totalPages;
	private List<T> list;

	public int getPrePages() {
		return pages <= 1 ? 1 : pages - 1;
	}

	public int getNextPages() {
		return pages < totalPages ? pages + 1 : pages;
	}

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

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Long totalPages) {
		this.totalPages = totalPages;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "PageBean [pages=" + pages + ", pagesize=" + pagesize
				+ ", total=" + total + ", totalPages=" + totalPages + ", list="
				+ list + "]";
	}

}
