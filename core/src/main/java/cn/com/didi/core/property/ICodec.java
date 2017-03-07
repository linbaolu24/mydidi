package cn.com.didi.core.property;

public interface ICodec {
	/**
	 * 编码
	 * @param b
	 * @return
	 */
	public byte[] code(byte[] b);
	/**
	 * 解码
	 * @param b
	 * @return
	 */
	public byte[] decode(byte[] b);
	/**
	 * 大写字符描述
	 * @return
	 */
	public String getType();
}
