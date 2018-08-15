package com.yc.web.tags;

import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class GetmapbykeyTag extends TagSupport{
	private Object key;
	private String var;
	private Map map;
	public Object getKey() {
		return key;
	}
	public void setKey(Object key) {
		this.key = key;
	}
	public String getVar() {
		return var;
	}
	public void setVar(String var) {
		this.var = var;
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	
	@Override
	public int doStartTag() throws JspException {
		Object obj = null;
		//将来根据map反射得到键的类型，再来转换key的类型
		Integer k = new Integer(key.toString());
		obj = map.get(k);
		super.pageContext.setAttribute(var, obj);
		return super.EVAL_BODY_INCLUDE;
	}
}
