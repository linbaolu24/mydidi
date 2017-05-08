package cn.com.didi.thirdExt.property;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.didi.core.cache.IHashAbleCache;
import cn.com.didi.thirdExt.util.ThirdConstants;
import cn.com.didi.user.system.domain.CodeDictionaryDto;
import cn.com.didi.user.system.domain.SystemParameterDto;
import cn.com.didi.user.system.service.ICodeDicService;
import cn.com.didi.user.system.service.ISysParamService;

/**
 * @author xlm
 *
 */
public class CachedDeracotorSysParamAndCodeDicService implements ICodeDicService,ISysParamService{
	private static final Logger LOGGER=LoggerFactory.getLogger(CachedDeracotorSysParamAndCodeDicService.class);
	protected ICodeDicService wrappedCodeDicService;
	protected ISysParamService wrappedSysParamService;
	public IHashAbleCache<String, Object, Object> getHashAble() {
		return hashAble;
	}
	public void setHashAble(IHashAbleCache<String, Object, Object> hashAble) {
		this.hashAble = hashAble;
	}
	protected IHashAbleCache<String, Object, Object> hashAble;
	@Override
	public List<SystemParameterDto> selectAllParams() {
		List<SystemParameterDto> lists=(List<SystemParameterDto>) hashAble.get(ThirdConstants.CACHED_ENV_LIST);
		if(lists==null){
			LOGGER.debug("系统参数列表不存在,从底层加载");
			lists=wrappedSysParamService.selectAllParams();
			hashAble.put(ThirdConstants.CACHED_ENV_LIST, lists);
			if (lists!=null) {
				Map<String,String> map=new HashMap<>();
				Map<String,Object> objMap=new HashMap<>();
				for (int i = 0; i < lists.size(); i++) {
					map.put(lists.get(i).getParamCode(),lists.get(i).getParamValue());
					objMap.put(lists.get(i).getParamCode(),lists.get(i));
				}
				hashAble.putAll(ThirdConstants.CACHED_ENV, map);
				hashAble.putAll(ThirdConstants.CACHED_ENV_OBJECT, objMap);
			}
		}
		return lists;
		
	}

	@Override
	public SystemParameterDto selectSysparams(String paramCode) {
		SystemParameterDto value=(SystemParameterDto) hashAble.hget(ThirdConstants.CACHED_ENV_OBJECT, paramCode);
		if(value==null){
			LOGGER.debug("系统参数{}对象不存在,从底层加载",paramCode);
			value=wrappedSysParamService.selectSysparams(paramCode);
			if(value==null){
				value=new SystemParameterDto();
			}
			 hashAble.hput(ThirdConstants.CACHED_ENV_OBJECT,paramCode, value);
		}
		return value;
	}

	@Override
	public List<CodeDictionaryDto> selectCodes(String cname) {
		String cachedName=ThirdConstants.CODE_LIST_PREFIX+cname;
		List<CodeDictionaryDto> lists=(List<CodeDictionaryDto>) hashAble.get(cachedName);
		if(lists==null){
			LOGGER.debug("字典{}列表不存在,从底层加载",cname);
			lists=wrappedCodeDicService.selectCodes(cname);
			hashAble.put(cachedName, lists);
			if (lists!=null) {
				Map<String,Object> objMap=new HashMap<>();
				for (int i = 0; i < lists.size(); i++) {
					objMap.put(lists.get(i).getDcode(),lists.get(i));
				}
				hashAble.putAll(ThirdConstants.CODE_OBJ_PREFIX+cname, objMap);
			}
		}
		return lists;
	}

	@Override
	public CodeDictionaryDto selectCode(String cname, String code) {
		String cachedName=ThirdConstants.CODE_OBJ_PREFIX+cname;
		CodeDictionaryDto value=(CodeDictionaryDto) hashAble.hget(cachedName, code);
		if(value==null){
			LOGGER.debug("字典{}的code【{}】对象不存在,从底层加载",cname,code);
			value=wrappedCodeDicService.selectCode(cname,code);
			if(value==null){
				value=new CodeDictionaryDto();
			}
			 hashAble.hput(cachedName,code, value);
		}
		return value;
	}

	public ICodeDicService getWrappedCodeDicService() {
		return wrappedCodeDicService;
	}

	public void setWrappedCodeDicService(ICodeDicService wrappedCodeDicService) {
		this.wrappedCodeDicService = wrappedCodeDicService;
	}

	public ISysParamService getWrappedSysParamService() {
		return wrappedSysParamService;
	}

	public void setWrappedSysParamService(ISysParamService wrappedSysParamService) {
		this.wrappedSysParamService = wrappedSysParamService;
	}
	@Override
	public String selectSysparamsValue(String paramCode) {
		String value=(String) hashAble.hget(ThirdConstants.CACHED_ENV, paramCode);
		if(value==null){
			LOGGER.debug("系统参数{}值不存在,从底层加载",paramCode);
			value=wrappedSysParamService.selectSysparamsValue(paramCode);
			if(value==null){
				value="";
			}
			 hashAble.hput(ThirdConstants.CACHED_ENV,paramCode, value);
		}
		return value;
	}

}
