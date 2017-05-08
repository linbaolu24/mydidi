package cn.com.didi.core.select.impl;

import cn.com.didi.core.select.IPageBound;

public class SimplePageBound implements IPageBound{
	private int pageSize;
	private int pageIndex;
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	@Override
	public int from() {
		if(pageIndex<=1){
			return 0;
		}
		return (pageIndex-1)*pageSize;
	}
	@Override
	public int end() {
		return pageIndex*pageSize;
	}
	
	@Override
	public IPageBound prePage() {
		if(pageIndex<=1){
			return null;
		}
		SimplePageBound simple= new SimplePageBound();
		simple.setPageIndex(pageIndex-1);
		simple.setPageSize(pageSize);
		return simple;
	}
	@Override
	public int end(int count) {
		int iend=end();
		return iend>count?count:iend;
	}
	
}
