package cn.com.didi.domain.util;

import org.apache.commons.lang.StringUtils;

import cn.com.didi.core.property.ICodeAble;

public enum ComStateEnum implements ICodeAble {
	WATTING("0"), // 表示认证待认证
	NOT_PASSING("1"), // 表示认证不通过
	ED("2"), // 上架
	UNED("3");//下架
	
	private String code;

	private ComStateEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public static ComStateEnum getFromSate(String state){
		if(state==null||state.isEmpty()){
			state=State.VALID.getCode();
		}
		return ICodeAble.getCode(ComStateEnum.values(),String.valueOf(Integer.parseInt(state)+2));
	}
	public static State getSate(String comState){
		if(comState==null||comState.isEmpty()){
			return null;
		}
		ComStateEnum comEnums= ICodeAble.getCode(ComStateEnum.values(),comState);
		if(comEnums.ordinal()>=ComStateEnum.ED.ordinal()){
			return ICodeAble.getCode(State.values(),String.valueOf(Integer.parseInt(comEnums.getCode())-2));
		}
		return null;
	}
	public static CrEnum getCr(String comState){
		if(comState==null||comState.isEmpty()){
			return null;
		}
		ComStateEnum comEnums= ICodeAble.getCode(ComStateEnum.values(),comState);
		if(comEnums.ordinal()<ComStateEnum.ED.ordinal()){
			return ICodeAble.getCode(CrEnum.values(),String.valueOf(Integer.parseInt(comEnums.getCode())+1));
		}
		return null;
	}
	public static ComStateEnum getFromCr(String state){
		if(state==null||state.isEmpty()){
			state=CrEnum.WATTING.getCode();
		}
		return ICodeAble.getCode(ComStateEnum.values(),String.valueOf(Integer.parseInt(state)-1));
	}
}
