package com.yc.web.servlets;

//静态导入
import static com.yc.utils.YcConstants.ERROR_404;
import static com.yc.utils.YcConstants.RESFOOD;
import static com.yc.utils.YcConstants.ERROR_500;
import static com.yc.utils.YcConstants.SESSIONCART;
import static com.yc.utils.YcConstants.SESSIONTOTAL;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.CartItem;
import com.yc.bean.PageBean;
import com.yc.bean.Resfood;

@WebServlet("/resfood.action")
public class ResfoodServlet extends BaseServlet{
	private static final long serialVersionUID = -2159753898994010466L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			if("details".equals(op)){
				detailsOp(req,resp);
			}else if("addCart".equals(op)){
				addCartOp(req,resp);
			}else if("clearCart".equals(op)){
				clearCart(req,resp);
			}else if("changeCount".equals(op)){
				changeCount(req,resp);
			}else if("toCart".equals(op)){
				toCartOp(req,resp);
			}else{
				resp.sendRedirect(ERROR_404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_500);
		}
	}
	
	
	private void toCartOp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/shopCart.jsp").forward(req, resp);
	}


	private void changeCount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CartItem ci = null;
		int fid = Integer.parseInt(req.getParameter("fid"));
		int num = Integer.parseInt(req.getParameter("num"));
		System.out.println(num+1);
		Map<Integer,CartItem> cart = (Map<Integer,CartItem>)session.getAttribute(SESSIONCART);
		ci = cart.get(fid);
		ci.setCount(ci.getCount()+num);
		if(ci.getCount() <= 0){
			cart.remove(fid);
		}else{
			cart.put(fid, ci);
		}
		ci.getSmallCount();
		session.setAttribute(SESSIONCART, cart);
		double total = countTotal(cart);
		jm.setCode(1);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", total);
		map.put("cartItem", ci);
		jm.setObj(map);
		super.outJsonString(jm,resp);
	}


	private double countTotal(Map<Integer, CartItem> cart) {
		double total = 0.0;
		for(Map.Entry<Integer, CartItem> entry : cart.entrySet()){
			total += entry.getValue().getSmallCount();
		}
		return total;
	}


	private void clearCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session.removeAttribute(SESSIONCART);
		session.removeAttribute(SESSIONTOTAL);
		
		req.getRequestDispatcher("/WEB-INF/pages/shopCart.jsp").forward(req, resp);
	}


	/**
	 * 根据fid到session的pageBean中查找这个商品
	 * @return
	 */
	public Resfood getResfoodFormSession(int fid){
		PageBean pageBean = (PageBean) session.getAttribute("pageBean");
		List<Resfood> list = pageBean.getList();
		for(Resfood r : list){
			if(r.getFid() == fid){
				return r;
			}
		}
		return null;
	}
	
	/**
	 * 添加到购物车
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	
	private void addCartOp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int fid = Integer.parseInt(req.getParameter("fid"));
		Resfood resfood = getResfoodFormSession(fid);
		Map<Integer,CartItem> cart = new HashMap<Integer, CartItem>();
		if(session.getAttribute(SESSIONCART) != null){
			cart = (Map<Integer, CartItem>) session.getAttribute(SESSIONCART);
		}
		CartItem ci = null;
		if(!cart.containsKey(fid)){
			ci = new CartItem();
			ci.setResfood(resfood); 
			ci.setCount(1);
		}else{
			ci = cart.get(fid);
			ci.setCount(ci.getCount()+1);
		}
		cart.put(fid, ci);
		session.setAttribute(SESSIONCART, cart);
		
		double total = 0.0;
		for (Map.Entry<Integer, CartItem> m : cart.entrySet()) {
			total += m.getValue().getSmallCount();
		}
		session.setAttribute(SESSIONTOTAL, total);
		//req.getRequestDispatcher("/WEB-INF/pages/shopCart.jsp").forward(req, resp);
		resp.sendRedirect("resfood.action?op=toCart");
	}

	private void detailsOp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int fid = Integer.parseInt(req.getParameter("fid"));
		Resfood r = getResfoodFormSession(fid);
		session.setAttribute(RESFOOD, r);
		req.getRequestDispatcher("/WEB-INF/pages/details.jsp").forward(req, resp);
	}
}
