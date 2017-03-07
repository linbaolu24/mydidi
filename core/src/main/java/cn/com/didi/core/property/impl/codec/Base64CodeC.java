package cn.com.didi.core.property.impl.codec;

import java.util.Base64;

import cn.com.didi.core.property.ICodec;

public class Base64CodeC implements ICodec{

	@Override
	public byte[] code(byte[] b) {
		return Base64.getEncoder().encode(b);
	}

	@Override
	public byte[] decode(byte[] b) {
		return Base64.getDecoder().decode(b);
	}

	@Override
	public String getType() {
		return "BASE64";
	}

}
