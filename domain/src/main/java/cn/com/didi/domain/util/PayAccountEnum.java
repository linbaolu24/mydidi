package cn.com.didi.domain.util;

import cn.com.didi.core.property.ICodeAble;
import cn.com.didi.domain.domains.PayAccountDto;
import cn.com.didi.user.users.domain.UserLinkIdDto;

public enum PayAccountEnum implements ICodeAble{
	ALIPAY("0") {
		@Override
		public String getAccoutId(PayAccountDto payAccount) {
			return payAccount.getAliAccount();
		}

		@Override
		public String getAccoutId(UserLinkIdDto payAccount) {
			return payAccount.getAlipayAccount();
		}
	}, WECHATPAY("1") {
		@Override
		public String getAccoutId(PayAccountDto payAccount) {
			return payAccount.getWechartAccount();
		}

		@Override
		public String getAccoutId(UserLinkIdDto payAccount) {
			return payAccount.getWechatAccount();
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
	public abstract String getAccoutId(UserLinkIdDto payAccount);
	
}
