package com.yc.web.servlets;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import com.yc.bean.PageBean;
import com.yc.bean.Resfood;
import com.yc.biz.ResfoodBizImpl;
import com.yc.utils.LogUtil;

@WebServlet("/init.action")
public class InitServlet extends BaseServlet{
	private static final long serialVersionUID = 3474216815323251580L;
	private ResfoodBizImpl rbi = new ResfoodBizImpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			Resfood resfood = (Resfood) super.parseParameterToT(req, Resfood.class);
			resfood.setOrder("desc");
			resfood.setOrderby("fid");
			PageBean pageBean = rbi.findResfoodByPage(resfood);
			session.setAttribute("pageBean", pageBean);
			req.getRequestDispatcher("/WEB-INF/pages/show.jsp").forward(req, resp);
		} catch (Exception e) {
			LogUtil.error(e);
			resp.sendRedirect("500.jsp");
		} 
	}
}
