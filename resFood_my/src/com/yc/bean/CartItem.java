package com.yc.bean;

import java.io.Serializable;

/**
 * 一个购物项
 *
 */
public class CartItem implements Serializable {
	private static final long serialVersionUID = 4504467454602268472L;

	private Resfood resfood;
	private Integer count;
	
	//el表达式调用地是  getXxx方法，但gson生成json字符串是生成的属性，为了让客户端拿到smallCount地值，所以必须加入属性smallCount
	private Double smallCount;

	public Double getSmallCount() {
		smallCount = resfood.getRealprice() * count;
		return smallCount;
	}

	public void setSmallCount(Double smallCount) {
		this.smallCount = smallCount;
	}

	public Resfood getResfood() {
		return resfood;
	}

	public void setResfood(Resfood resfood) {
		this.resfood = resfood;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
