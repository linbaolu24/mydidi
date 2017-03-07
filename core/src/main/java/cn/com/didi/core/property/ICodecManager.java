package cn.com.didi.core.property;

public interface ICodecManager {
	/**
	 * @return
	 */
	public ICodec find(String type);
	/**
	 * 添加codec
	 * @param type
	 * @param codec
	 */
	public void addCodec(ICodec codec);
	/**
	 * 查找列表
	 * @param type
	 * @return
	 */
	public ICodec[] find(String[] type);
}
