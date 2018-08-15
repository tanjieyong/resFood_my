package com.yc.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yc.bean.Resuser;
import com.yc.dao.DBUtil;
import com.yc.utils.Encrypt;

public class ResuserBizImpl {
	
	public Resuser login(Resuser user){
		String sql = "select * from resuser where username=? and pwd=?";
		String pwd = Encrypt.md5(Encrypt.md5(user.getPwd()));
		return DBUtil.get(Resuser.class, sql, user.getUsername(),pwd);
	}
	
	public Map<String,Object> findResuserContribution(int page,int rows,String sort,String order){
		int count = this.findCount();
		//ifnull?
		String sql = " select * from ( select resuser.userid,username,ifnull( sum(dealprice*num),0) as dealcount "
				+ " from resuser left join resorder on resuser.userid=resorder.userid "
				+ " left join resorderitem on resorder.roid=resorderitem.roid "
				+ " group by userid,username ) a ";
		if(sort != null && !"".equals(sort)){
			sql += " order by " + sort +" ";
		}
		if(order != null && !"".equals(sort)){
			sql += order + " ";
		}
		int start = (page-1)*rows;
		sql += " limit " + start + "," + rows;
		List<Resuser> list = DBUtil.list(Resuser.class, sql, null);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", list.size());
		map.put("rows", list);
		return map;
		
	}

	private int findCount() {
		return 0;
	}
}
