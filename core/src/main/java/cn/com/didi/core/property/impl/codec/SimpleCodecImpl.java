package cn.com.didi.core.property.impl.codec;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import cn.com.didi.core.property.ICodec;
import cn.com.didi.core.property.ICodecManager;

/**
 * @author xlm
 *
 */
public class SimpleCodecImpl implements ICodecManager{
	public Map<String,ICodec> codecMap;

	@Override
	public ICodec find(String type) {
		if(StringUtils.isEmpty(type)){
			return null;
		}
		return codecMap.get(type.toUpperCase());
	}

	@Override
	public void addCodec(ICodec codec) {
		if(codecMap==null){
			codecMap=new HashMap<>();
		}
		codecMap.put(codec.getType(), codec);
	}

	@Override
	public ICodec[] find(String[] type) {
		if(type==null||type.length==0){
			return null;
		}
		ICodec[] codec=new ICodec[type.length];
		for(int i=0;i<type.length;i++){
			codec[i]=find(type[i]);
		}
		return codec;
	}

	public Map<String, ICodec> getCodecMap() {
		return codecMap;
	}

	public void setCodecMap(Map<String, ICodec> codecMap) {
		this.codecMap = codecMap;
	}
	
}
