package com.yc.web.servlets;

import static com.yc.utils.YcConstants.ERROR_404;
import static com.yc.utils.YcConstants.ERROR_500;
import static com.yc.utils.YcConstants.LOGINUSER;
import static com.yc.utils.YcConstants.LOGINPAGE;
import static com.yc.utils.YcConstants.REGPAGE;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.bean.Resuser;
import com.yc.biz.ResuserBizImpl;

@WebServlet(urlPatterns={"/resuser.action","/resuser/resuser.action"})
public class ResuserServlet extends BaseServlet{
	private ResuserBizImpl rbi = new ResuserBizImpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			if("login".equals(op)){
				login(req,resp);
			}else if("logout".equals(op)){
				logout(req,resp);
			}else if("isResuserLogin".equals(op)){
				isResuserLogin(req,resp);
			}else if("toReg".equals(op)){
				toRegOp(req,resp);
			}else if("toLogin".equals(op)){
				toLoginOp(req,resp);
			}else{
				resp.sendRedirect(ERROR_404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_500);
		}
	}

	private void toLoginOp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session.removeAttribute("msg");
		req.getRequestDispatcher(LOGINPAGE).forward(req, resp);
	}

	private void toRegOp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(REGPAGE).forward(req, resp);
	}

	private void isResuserLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Resuser user = (Resuser) session.getAttribute(LOGINUSER);
		if(user != null){
			jm.setCode(1);
			user.setPwd("");  
			jm.setObj(user);
		}else{
			jm.setCode(0);
		}
		super.outJsonString(jm, resp);
	}

	private void logout(HttpServletRequest req, HttpServletResponse resp) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ServletException, IOException {
		session.removeAttribute(LOGINUSER);
		resp.sendRedirect("../index.jsp");
	}

	private void login(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		session.removeAttribute("msg");
		Resuser resuser = (Resuser) super.parseParameterToT(req,Resuser.class );
		HttpSession session = req.getSession();
		String rand = (String) session.getAttribute("sRand");
		String valcode = req.getParameter("valcode");
		if(rand.equals(valcode)==false){
			session.setAttribute("msg", "valide code failed");
			req.getRequestDispatcher(LOGINPAGE).forward(req, resp);
		}else{
			resuser = rbi.login(resuser);
			if(resuser!=null){
				session.setAttribute(LOGINUSER, resuser);
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
			}else{
				session.setAttribute("msg", "username or password is wrong,login failed,please login again");
				req.getRequestDispatcher(LOGINPAGE).forward(req, resp);
			}
		}
	}
}
