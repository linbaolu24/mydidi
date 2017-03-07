package cn.com.didi.core.select;

import java.util.List;

public interface IPage<T> {
	int getCount();
	List<T> getList();
}
