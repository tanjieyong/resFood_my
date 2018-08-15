package com.yc.web.servlets;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.CartItem;
import com.yc.bean.Resorder;
import com.yc.bean.Resuser;
import com.yc.biz.ResorderBizImpl;

import static com.yc.utils.YcConstants.ERROR_404;
import static com.yc.utils.YcConstants.RESFOOD;
import static com.yc.utils.YcConstants.ERROR_500;
import static com.yc.utils.YcConstants.SESSIONCART;
import static com.yc.utils.YcConstants.SESSIONTOTAL;
import static com.yc.utils.YcConstants.LOGINUSER;

@WebServlet("/resuser/resorder.action")
public class ResorderServlet extends BaseServlet{
	private ResorderBizImpl resorderBiz = new ResorderBizImpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			if("toFillForm".equals(op)){
				toFillFormOp(req,resp);
			}else if("makeOrder".equals(op)){
				makeOrderOp(req,resp);
			}else{
				resp.sendRedirect("../"+ERROR_404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("../" + ERROR_500);
		}
	}

	private void makeOrderOp(HttpServletRequest req, HttpServletResponse resp) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ServletException, IOException {
		//1.添加resorder,  address userid,tel,ps 
		//2.取出resorder的roid 订单号
		//3.循环购物车  sessionCart,向resorderitem中添加数据  
			//roid,fid,dealprice,num
		
		Resorder resorder = (Resorder) super.parseParameterToT(req, Resorder.class);
		Map<Integer,CartItem> shopCart = (Map<Integer, CartItem>) session.getAttribute(SESSIONCART);
		Resuser resuser = (Resuser) session.getAttribute(LOGINUSER);
		try {
			resorderBiz.makeOrder(resuser, resorder, shopCart);
			session.removeAttribute(SESSIONCART);
			session.setAttribute(SESSIONTOTAL, 0.0);
			req.getRequestDispatcher("/WEB-INF/pages/seeYou.jsp").forward(req, resp);
		} catch (SQLException e) {
			e.printStackTrace();
			req.setAttribute("msg", "make order failed,please contact the administrator...");
			req.getRequestDispatcher("/WEB-INF/pages/seeYou.jsp").forward(req, resp);
		}
	}

	private void toFillFormOp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(session.getAttribute(SESSIONCART)==null || ((Map)session.getAttribute(SESSIONCART)).size() <= 0 ){
			session.setAttribute("msg", "cart should not be empty");
			req.getRequestDispatcher("/WEB-INF/pages/shopCart.jsp").forward(req, resp);
		}else{
			req.getRequestDispatcher("/WEB-INF/pages/checkOut.jsp").forward(req, resp);
		}
	}
}
