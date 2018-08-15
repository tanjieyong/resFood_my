package com.yc.web.servlets;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.Resadmin;
import com.yc.biz.ResadminBizImpl;

import static com.yc.utils.YcConstants.ERROR_404;
import static com.yc.utils.YcConstants.RESFOOD;
import static com.yc.utils.YcConstants.ERROR_500;
import static com.yc.utils.YcConstants.SESSIONCART;
import static com.yc.utils.YcConstants.SESSIONTOTAL;
import static com.yc.utils.YcConstants.LOGINUSER;

@WebServlet("/backresadmin.action")
public class ResadminServlet extends BaseServlet{
	private ResadminBizImpl resadminBiz = new ResadminBizImpl();
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			if("login".equals(op)){
				login(req,resp);
			}else{
				resp.sendRedirect(ERROR_404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_500);
		}
	}

	private void login(HttpServletRequest req, HttpServletResponse resp) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ServletException, IOException {
		Resadmin resadmin = (Resadmin) super.parseParameterToT(req, Resadmin.class);
		String valcode = req.getParameter("valcode");
		String sRand = (String) session.getAttribute("sRand");
		if(!valcode.equals(sRand)){
			req.setAttribute("msg", "验证码不一致，登录失败！！！");
			req.getRequestDispatcher("/WEB-INF/pages/backLogin.jsp").forward(req, resp);
		}else{
			resadmin = resadminBiz.login(resadmin);
			System.out.println(resadmin);
			if(resadmin != null){
				session.setAttribute("login_resadmin", resadmin);
				resp.sendRedirect("resadmin/main.jsp");
			}else{
				req.setAttribute("msg", "uaername or password is wrong,please login again");
				req.getRequestDispatcher("backLogin.jsp").forward(req, resp);
			}
		}
	}
}
