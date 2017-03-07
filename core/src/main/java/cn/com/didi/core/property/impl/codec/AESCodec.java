package cn.com.didi.core.property.impl.codec;

import cn.com.didi.core.property.ICodec;
import cn.com.didi.core.utils.AESUtils;

/**
 * @author xlm
 *
 */
public class AESCodec implements ICodec {
	/**
	 * 默认AES key
	 */
	private String key = "DIDI_PCODE_AES_JHL";

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public byte[] code(byte[] b) {
		try {
			return AESUtils.encrypt(b, key);
		} catch (Exception e) {
			throw new RuntimeException("加密失败",e);
		}
	}

	@Override
	public byte[] decode(byte[] b) {
		try{
			return AESUtils.decrypt(b, key);
		}catch(Exception e){
			throw new RuntimeException("解密失败",e);
		}
	}

	@Override
	public String getType() {
		return "AES";
	}

}
