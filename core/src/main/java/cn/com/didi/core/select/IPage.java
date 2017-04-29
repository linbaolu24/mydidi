package cn.com.didi.core.select;

import java.util.List;

public interface IPage<T> {
	int getCount();
	/**负数表示未知*/
	List<T> getList();
}
