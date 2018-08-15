package com.yc.biz;

import static org.junit.Assert.*;

import org.junit.Test;

import com.yc.bean.Resfood;

public class ResfoodBizImplTest {
	private ResfoodBizImpl rbi = new ResfoodBizImpl();
	@Test
	public void test() {
		System.out.println(rbi.findResfoodCount(null));
	}
	
	@Test
	public void test2() {
		Resfood r = new Resfood();
		r.setPages(1);
		r.setPagesize(6);
		r.setOrder("desc");
		r.setOrderby("fid");
		System.out.println(rbi.findResfoodByPage(r));
	}

}
