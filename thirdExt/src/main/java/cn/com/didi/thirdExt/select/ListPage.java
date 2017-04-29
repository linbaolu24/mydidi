package cn.com.didi.thirdExt.select;

import java.util.List;

import cn.com.didi.core.select.IPage;

public class ListPage<T> implements IPage<T> {
	private List<T> list;
	private Integer count;

	

	public ListPage(List<T> list, Integer count) {
		super();
		this.list = list;
		this.count = count;
	}

	@Override
	public List<T> getList() {

		return list;
	}

	@Override
	public int getCount() {
		if (count == null) {
			return -1;
		}
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
