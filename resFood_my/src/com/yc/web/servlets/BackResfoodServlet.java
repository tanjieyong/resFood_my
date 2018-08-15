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

import com.yc.bean.Resfood;
import com.yc.biz.ResfoodBizImpl;

@WebServlet(urlPatterns={"/resadmin/resfood.action"})
public class BackResfoodServlet extends BaseServlet{
	private ResfoodBizImpl resfoodBiz = new ResfoodBizImpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if("showFoodSellInfoList".equals(op)){
			showFoodSellInfoListOp(req,resp);
		}
	}
	private void showFoodSellInfoListOp(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		Map map = new HashMap();
		try {
			Resfood resfood = (Resfood) super.parseParameterToT(req, Resfood.class);
			resfood.setOrder(order);
			resfood.setOrderby(sort);
			resfood.setPages(page);
			resfood.setPagesize(rows);
			List<Resfood> listResfood = resfoodBiz.findResfoodSellCount(resfood);
			long total = resfoodBiz.findResfoodCount(resfood);
			map.put("total", total);
			map.put("rows", listResfood);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.outJsonString(map, resp);
	}
}
