package cn.com.didi.app.order.domain;

import cn.com.didi.core.select.impl.SimplePageBound;

public class PagedOrderJAO extends OrderJAO{
	private SimplePageBound pageBounds;
	public PagedOrderJAO(SimplePageBound pageBounds) {
		super();
		this.pageBounds = pageBounds;
	}
	public PagedOrderJAO(){
		this(true);
	}
	public PagedOrderJAO(boolean created) {
		if(created){
			this.pageBounds = new SimplePageBound();
		}
	}

	public SimplePageBound pageBounds() {
		return pageBounds;
	}

	public void pageBounds(SimplePageBound pageBounds) {
		this.pageBounds = pageBounds;
	}
	public int getPageSize() {
		return pageBounds.getPageSize();
	}
	public void setPageSize(int pageSize) {
		pageBounds.setPageSize(pageSize);
	}
	public int getPageIndex() {
		return pageBounds.getPageIndex();
	}
	public void setPageIndex(int pageIndex) {
		pageBounds.setPageIndex(pageIndex);
	}
	
}
