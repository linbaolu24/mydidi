package cn.com.didi.core.select;

public interface IPageBound {
	public int getPageIndex();
	
	public int getPageSize() ;
	public int from();
	public int end();
	public int end(int count);
	public IPageBound prePage();
}
