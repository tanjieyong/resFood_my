package com.yc.biz;

import java.util.ArrayList;
import java.util.List;

import com.yc.bean.PageBean;
import com.yc.bean.Resfood;
import com.yc.dao.DBUtil;

public class ResfoodBizImpl {
	
	public Long findResfoodCount(Resfood resfood){
		String sql = "select count(*) as num from resfood where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if(resfood != null){
			
		}
		Object num = DBUtil.get(sql, params.toArray()).get("NUM");
		if(num != null){
			return Long.parseLong(num.toString());
		}else{
			return 0L;
		}
	}
	
	
	public PageBean findResfoodByPage(Resfood resfood){
		long total = findResfoodCount(resfood);
		List<Resfood> allResfoodList = findResfood(resfood);
		PageBean pageBean = new PageBean();
		pageBean.setList(allResfoodList);
		pageBean.setPages(resfood.getPages());
		pageBean.setPagesize(resfood.getPagesize());
		pageBean.setTotal(total);
		long totalPage = total%resfood.getPagesize()==0?total/resfood.getPagesize():(total/resfood.getPagesize()+1);
		pageBean.setTotalPages(totalPage);
		return pageBean;
	}


	private List<Resfood> findResfood(Resfood resfood) {
		String sql = "select * from resfood where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if(resfood != null){
			
		}
		if(resfood != null && resfood.getOrderby() != null && resfood.getOrder() != null){
			sql += " order by " + resfood.getOrderby() + " " + resfood.getOrder();
		}
		if(resfood != null && resfood.getPages() != null && resfood.getPagesize() != null){
			int pages = resfood.getPages();
			int pagesize = resfood.getPagesize();
			int start = (pages - 1) * pagesize;
			sql += " limit ?,? ";
			params.add(start);
			params.add(pagesize);
			
		}
		return DBUtil.list(Resfood.class, sql, params.toArray());
	}


	public List<Resfood> findResfoodSellCount(Resfood resfood) {
		String sql = "select resfood.fid as fid,fname,ifnull(sum(num),0) as sellcount "
				+ " from resfood left join resorderitem on resfood.fid=resorderitem.fid "
				+ " group by resfood.fid,fname ";
		if(resfood != null && resfood.getOrderby() != null && resfood.getOrder() != null){
			sql += " order by " + resfood.getOrderby() + " " + resfood.getOrder();
		}
		if(resfood != null && resfood.getPages() != null && resfood.getPagesize() != null){
			int pages = resfood.getPages();
			int pagesize = resfood.getPagesize();
			int start = (pages - 1) * pagesize;
			sql += " limit "+start+","+pagesize+" ";
		}
		System.out.println(sql);
		return DBUtil.list(Resfood.class, sql);
	}
}
