package com.yc.biz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.bean.CartItem;
import com.yc.bean.Resfood;
import com.yc.bean.Resorder;
import com.yc.bean.Resorderitem;
import com.yc.bean.Resuser;
import com.yc.dao.DBUtil;

public class ResorderBizImpl {
	public void makeOrder(Resuser resuser,Resorder order,Map<Integer,CartItem> cart) throws SQLException{
		String sql = "insert into resorder(userid,address,tel,ordertime,deliverytime,ps,status) values(?,?,?,now(),date_add(now(),interval 1 hour),?,0)";
		Connection conn = DBUtil.getConn();
		
		try {
			conn.setAutoCommit(false);  //关闭事务（一条sql语句自动提交一次）
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, resuser.getUserid() + "");
			pstmt.setString(2, order.getAddress());
			pstmt.setString(3, order.getTel());
			pstmt.setString(4, order.getPs());
			pstmt.executeUpdate();
			
			sql = "select max(roid) as roid from resorder ";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			int roid = 0;
			if(rs.next()){
				roid = rs.getInt(1);
			}
			for(Map.Entry<Integer, CartItem> entry : cart.entrySet()){
				int fid = entry.getKey();
				CartItem ci = entry.getValue();
				Resfood resfood = ci.getResfood();
				int count = ci.getCount();
				Double smallCount = ci.getSmallCount();
				sql="insert into resorderitem(roid,fid,dealprice,num) values(?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, roid + "");
				pstmt.setString(2, fid+"");
				pstmt.setString(3, resfood.getRealprice()+"");
				pstmt.setString(4, count+"");
				pstmt.executeUpdate();
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally{
			if(conn != null){
				conn.setAutoCommit(true);
				conn.close();
			}
		}
	}
	
	public List<Resorder> findResorder(Resorder resorder){
		String sql = "select roid,userid,address,tel,date_format(ordertime,'%Y-%c-%d %h:%i:%s') as ordertime, "
				+ " date_format(deliverytime,'%Y-%c-%d %h:%i:%s') as deliverytime,ps,status from resorder where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if(resorder != null){
			if(resorder.getUserid() != null && !resorder.getUserid().equals("")){
				sql += " and userid=? ";
				params.add(resorder.getUserid());
			}
			if(resorder.getStatus() != null && !resorder.getStatus().equals("")){
				sql += " and status=? ";
				params.add(resorder.getStatus());
			}
			if(resorder.getSort() != null && !resorder.getSort().equals("")){
				sql += " order by " + resorder.getSort();
			}
			if(resorder.getOrder() != null && !resorder.getOrder().equals("")){
				sql += " " + resorder.getOrder();
			}
			if(resorder.getPage() != null && !resorder.getPage().equals("")){
				int start = (resorder.getPage()-1)*resorder.getRows();
				sql += " limit " + start + ", " + resorder.getRows();
			}
		}
		return DBUtil.list(Resorder.class, sql, params.toArray());
	}
	
	public Integer findCount(Resorder resorder){
		String sql = "select count(*) as total from resorder where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if(resorder != null){
			if(resorder.getUserid() != null && !"".equals(resorder.getUserid())){
				sql += " and userid=? ";
			}
			if(resorder.getStatus() != null && !"".equals(resorder.getStatus())){
				sql += " and status=? ";
			}
		}
		return Integer.parseInt( DBUtil.get(sql, params.toArray()).get("TOTAL").toString());
	}
	
	public List<Resorderitem> findResorderItem(Resorder resorder){
		String sql = "select roid,fname,dealprice,num,dealprice*num as smallcount from resorderitem "
				+ " left join resfood on resorderitem.fid=resfood.fid where roid=?";
		System.out.println(sql);
		return DBUtil.list(Resorderitem.class, sql, resorder.getRoid());
	}

	public void transfer(Resorder resorder) {
			String sql = "update resorder set status=1 where roid=" + resorder.getRoid();
			DBUtil.doUpdate(sql);
	}
	
	
	
}
