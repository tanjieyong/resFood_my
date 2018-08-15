package com.yc.biz;

import com.yc.bean.Resadmin;
import com.yc.bean.Resuser;
import com.yc.dao.DBUtil;
import com.yc.utils.Encrypt;

public class ResadminBizImpl {
	
	public Resadmin login(Resadmin resadmin){
		String sql = "select * from resadmin where raname=? and rapwd=?";
		String pwd = Encrypt.md5(Encrypt.md5(resadmin.getRapwd()));
		return DBUtil.get(Resadmin.class, sql, resadmin.getRaname(),pwd);
	}
}
