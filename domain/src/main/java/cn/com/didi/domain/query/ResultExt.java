package cn.com.didi.domain.query;

import java.util.List;

import cn.com.didi.core.property.impl.result.Result;
import cn.com.didi.core.select.IPage;

public class ResultExt<T, E> extends Result<T> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7561870345066052338L;

	public ResultExt(String message, String code, Exception exception, T data, E ext) {
		super(message, code, exception, data);
		this.ext = ext;
	}
	public ResultExt(T data,E ext){
		this("",SUCCESS_CODE,null,data,ext);
	}
	private E ext;

	public E getExt() {
		return ext;
	}

	public void setExt(E ext) {
		this.ext = ext;
	}
	public static <TT> ResultExt<List<TT>, CountObject> build(IPage<TT> page){
		if(page==null){
			return null;
		}
		CountObject count=new CountObject(page.getCount());
		return new  ResultExt<List<TT>, CountObject>(page.getList(),count);
	}
}
