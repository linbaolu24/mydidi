package cn.com.didi.thirdExt.select;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.com.didi.core.select.IPage;

public class MybatisPaginatorPage<T> implements IPage<T> {
	private List<T> list;
	private Integer totalCount;

	public MybatisPaginatorPage(PageList<T> pageList) {
		this(pageList, pageList.getPaginator().getTotalCount());

	}

	public MybatisPaginatorPage(PageList<T> pageList, Integer totalCount) {
		super();
		this.list = pageList;
		this.totalCount = totalCount;
	}

	@Override
	public int getCount() {
		if (totalCount == null) {
			return -1;
		}
		return totalCount;
	}

	@Override
	public List<T> getList() {
		return list;
	}

	public void setCount(int count) {
		this.totalCount = count;
	}

	@Override
	public void setList(List<T> list) {
		this.list = list;

	}

}
