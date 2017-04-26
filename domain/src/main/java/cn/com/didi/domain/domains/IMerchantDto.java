package cn.com.didi.domain.domains;

import java.math.BigDecimal;

public interface IMerchantDto {
	String getMasterName();
	String getMci();
	BigDecimal getLat();
	BigDecimal getLng();
	Long getMerchantId();
}
