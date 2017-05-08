package cn.com.didi.domain.util;

import cn.com.didi.core.property.ICodeAble;
import cn.com.didi.domain.domains.PayAccountDto;

public enum PayAccountEnum implements ICodeAble{
	ALIPAY("0") {
		@Override
		public String getAccoutId(PayAccountDto payAccount) {
			return payAccount.getAliAccount();
		}
	}, WECHATPAY("1") {
		@Override
		public String getAccoutId(PayAccountDto payAccount) {
			return payAccount.getWechartAccount();
		}
	};
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	private String code;

	private PayAccountEnum(String code) {
		this.code = code;
	}

	public abstract String getAccoutId(PayAccountDto payAccount);
	
}
