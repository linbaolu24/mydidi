package cn.com.didi.thirdExt.select;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.com.didi.core.select.IPage;

public class MybatisPaginatorPage<T> implements IPage<T>{
	private PageList<T> pageList;
	
	public MybatisPaginatorPage(PageList<T> pageList) {
		super();
		this.pageList = pageList;
	}

	@Override
	public int getCount() {
		return pageList.getPaginator().getTotalCount();
	}

	@Override
	public List<T> getList() {
		return pageList;
	}

	public PageList<T> getPageList() {
		return pageList;
	}

	public void setPageList(PageList<T> pageList) {
		this.pageList = pageList;
	}
	
}
