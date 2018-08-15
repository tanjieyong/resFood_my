package com.yc.web.servlets;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.bean.JsonModel;
import com.yc.bean.Resadmin;
import com.yc.bean.Resorder;
import com.yc.bean.Resorderitem;
import com.yc.biz.ResorderBizImpl;
import com.yc.printer.SaledTicket;
import com.yc.printer.YcPrinter;

@WebServlet(urlPatterns={"/resadmin/resorder.action"})
public class BackResorderServlet extends BaseServlet{
	private ResorderBizImpl resorderBiz = new ResorderBizImpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			if("showOrderList".equals(op)){
				showOrderListOp(req,resp);
			}else if("showOrderItemList".equals(op)){
				showOrderItemListOp(req,resp);
			}else if("transfer".equals(op)){
				transferOp(req,resp);
			}else if("printOrder".equals(op)){
				printOrderOp(req,resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void printOrderOp(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		JsonModel jm = new JsonModel();
		Resadmin resadmin = null;
		String admin = "admin";
		if(session.getAttribute("login_resadmin") != null){
			resadmin = (Resadmin) session.getAttribute("login_resadmin");
			admin = resadmin.getRaname();
		}
		try {
			Resorder resorder = (Resorder) super.parseParameterToT(req, Resorder.class);
			List<Resorderitem> list = resorderBiz.findResorderItem(resorder);
			double count = 0;
			for(int i=0;i<list.size();i++){
				Resorderitem ritem = list.get(i);
				count += ritem.getDealprice()*ritem.getNum();
			}
			SaledTicket stk = new SaledTicket(list,admin,
					list.get(0).getRoid()+"",list.size()+"",count+"",count+"","0");
			YcPrinter p = new YcPrinter(stk);
			p.printer();
			jm.setCode(1);
		} catch (Exception e) {
			jm.setErrmsg(e.getMessage());
			jm.setCode(0);
			e.printStackTrace();
		} 
		super.outJsonString(jm, resp);
		
	}

	private void transferOp(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			Resorder resorder = (Resorder) super.parseParameterToT(req, Resorder.class);
			resorderBiz.transfer(resorder);
			jm.setCode(1);
		} catch (Exception e) {
			jm.setCode(0);
			jm.setErrmsg(e.getMessage());
			e.printStackTrace();
		}
		super.outJsonString(jm, resp);
	}

	private void showOrderItemListOp(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		Map  map = new HashMap();
		try {
			Resorder resorder = (Resorder) super.parseParameterToT(req, Resorder.class);
			List<Resorderitem> resorderitemList = resorderBiz.findResorderItem(resorder);
			System.out.println(resorderitemList);
			map.put("total", resorderitemList.size()); //没有分页，total没用
			map.put("rows", resorderitemList);
			
			if(session.getAttribute("listResorder") != null){
				List<Resorder> listResorder = (List<Resorder>) session.getAttribute("listResorder");
				for (Resorder r : listResorder) {
					if(r.getRoid() == resorder.getRoid()){
						map.put("msg", r.getPs());
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		super.outJsonString(map, resp);
	}

	private void showOrderListOp(HttpServletRequest req,
			HttpServletResponse resp) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		Map map = new HashMap();
		//?得到了什么参数
		try {
			Resorder resorder = (Resorder) super.parseParameterToT(req, Resorder.class);
			resorder.setOrder(this.order);
			resorder.setSort(sort);
			resorder.setPage(page);
			resorder.setRows(rows);
			List<Resorder> listResorder = resorderBiz.findResorder(resorder);
			int total = resorderBiz.findCount(resorder);
			map.put("total", total);
			map.put("rows", listResorder);
			HttpSession session = req.getSession();
			session.setAttribute("listResorder", listResorder);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		super.outJsonString(map, resp);
	}
}
