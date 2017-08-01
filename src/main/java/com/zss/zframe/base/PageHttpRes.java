package com.zss.zframe.base;

public class PageHttpRes extends ObjectHttpRes {

	public long total = 0; //总记录数

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
}
