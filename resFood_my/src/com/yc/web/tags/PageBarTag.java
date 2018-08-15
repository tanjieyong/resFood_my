package com.yc.web.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.yc.bean.PageBean;

public class PageBarTag extends TagSupport{
	private PageBean pageBean;
	private String href;
	
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = super.pageContext.getOut();
		try {
			out.println("共"+pageBean.getTotal() +"条记录/每页"+pageBean.getPagesize()+"条 共"+pageBean.getTotalPages()+"页/当前第"+pageBean.getPages()+"页 ");
			out.println("<a href=\""+href+"&pages=1\">第一页 </a>");
			out.println("<a href=\""+href+"&pages="+pageBean.getPrePages()+"\">上一页 </a>");
			out.println("<a href=\""+href+"&pages="+pageBean.getNextPages()+"\">下一页 </a>");
			out.println("<a href=\""+href+"&pages="+pageBean.getTotalPages()+"\">最后一页 </a>");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.EVAL_BODY_INCLUDE;
	}



	public PageBean getPageBean() {
		return pageBean;
	}



	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}



	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}


	
	
}
