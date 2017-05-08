package cn.com.didi.core.property;

public interface ICodeAble {
	public String getCode();

	default boolean codeEqual(String code){
		String nowCode=getCode();
		if(nowCode==code){
			return true;
		}
		if(nowCode==null){
			return false;
		}
		return nowCode.equals(code);
	}
	public static  <T extends ICodeAble> T getCode(T[] enumsArray,String code ){
		if(enumsArray!=null){
			for(T one:enumsArray){
				if(one.getCode().equals(code)){
					return one;
				}
			}
		}
		return null;
	}
}
