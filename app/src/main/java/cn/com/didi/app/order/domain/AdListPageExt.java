package cn.com.didi.app.order.domain;

import java.util.List;

import cn.com.didi.thirdExt.select.ListPage;

public class AdListPageExt< T,S> {
	private ListPage<T> list;

	private List<S> adList;

	public AdListPageExt(ListPage<T> list, List<S> adList) {
		super();
		this.list = list;
		this.adList = adList;
	}

	public List<T> getList() {
		return list.getList();
	}

	public int getCount() {
		return list.getCount();
	}

	public void setCount(int count) {
		list.setCount(count);
	}

	public void setList(List<T> list) {
		this.list.setList(list);
	}

	public void setCount(Integer count) {
		list.setCount(count);
	}

	public List<S> getAdList() {
		return adList;
	}

	public void setAdList(List<S> adList) {
		this.adList = adList;
	}
	
}
