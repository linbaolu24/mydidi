package cn.com.didi.core.property.impl.codec;

import cn.com.didi.core.property.ICodec;

public class ArrayCodec implements ICodec{
	private ICodec[] codecArray;
	
	public ArrayCodec() {
		super();
	}

	public ArrayCodec(ICodec[] codecArray) {
		super();
		this.codecArray = codecArray;
	}

	@Override
	public byte[] code(byte[] b) {
		if(b==null){
			return null;
		}
		byte[] temp=b;
		for(int i=0;i<codecArray.length;i++){
			temp=codecArray[i].code(temp);
		}
		return temp;
	}

	@Override
	public byte[] decode(byte[] b) {
		if(b==null){
			return null;
		}
		byte[] temp=b;
		for(int i=codecArray.length-1;i>=0;i--){
			temp=codecArray[i].decode(temp);
		}
		return temp;
	}

	@Override
	public String getType() {
		return "ARRAY";
	}

	public ICodec[] getCodecArray() {
		return codecArray;
	}

	public void setCodecArray(ICodec[] codecArray) {
		this.codecArray = codecArray;
	}

}
