package cn.com.didi.core.filter;

public interface IFilter<T> {
	boolean filter(T obj);
	IFilter<?> TRUE_FILTER=new BooleanFilter(true);
	
	IFilter<?> FALSE_FILTER=new BooleanFilter(false);
	
	class BooleanFilter<T> implements IFilter<T>{
		private boolean value;
		public BooleanFilter(boolean value) {
			super();
			this.value = value;
		}

		public boolean filter(T obj) {
			return value;
		}
	}
}
