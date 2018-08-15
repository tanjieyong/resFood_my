package com.yc.web.servlets;

import static com.yc.utils.YcConstants.ERROR_404;
import static com.yc.utils.YcConstants.ERROR_500;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.biz.ResuserBizImpl;

@WebServlet(urlPatterns={"/resadmin/resuser.action"})
public class BackResuserServlet extends BaseServlet{
	private ResuserBizImpl resuserBiz = new ResuserBizImpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			if("showUserContributionList".equals(op)){
				showUserContributionListOp(req,resp);
			}else{
				resp.sendRedirect(ERROR_404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_500);
		}
	}

	private void showUserContributionListOp(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		Map<String,Object> map = resuserBiz.findResuserContribution(page, rows, sort, order);
		super.outJsonString(map, resp);
	}
}
