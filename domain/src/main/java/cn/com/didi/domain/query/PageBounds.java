package cn.com.didi.domain.query;

public class PageBounds {
	/**
	 * 页索引
	 */
	private int pageIndex;
	/**
	 * 页大小
	 */
	private int pageSize;
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
