package cn.com.didi.domain.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.com.didi.core.filter.IFilter;
import cn.com.didi.core.property.IConverter;


public class SignatureUtils {

    private static final Log LOGGER = LogFactory.getLog(SignatureUtils.class);
    
    private static final String KEY = "key";

    /**
     * 获取支付签名
     * @param obj
     * @param signKey
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws UnsupportedEncodingException 
     */
    public static  String getPaySign(Object obj, String signKey,String charset,IFilter<Field> filedFilter,IConverter<String, String> nameConvert) throws IllegalArgumentException, IllegalAccessException, UnsupportedEncodingException {
        if (null == obj  || StringUtils.isBlank(signKey)){
            LOGGER.error("签名信息关键信息不能为空 obj:"+obj+"  signKey:"+signKey);
            return "";
        }
        StringBuilder buf = new StringBuilder();
        List<String> list = new ArrayList<String>();
        //取类的签名字段信息值
        Field[] fields = obj.getClass().getDeclaredFields();
        list = getSignContent(fields,obj,filedFilter,nameConvert);
        //取父类的签名字段信息
        Field[] superfields = obj.getClass().getSuperclass().getDeclaredFields();
        list.addAll(getSignContent(superfields,obj,filedFilter,nameConvert));
        if (null != list && !list.isEmpty()){
            //对数组进行字典排序
            Collections.sort(list, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            //拼接成字符串
            for (String str : list) {
                buf.append(str);
            }
            buf.append(KEY).append("=").append(signKey);
        }
        return DigestUtils.md5Hex(buf.toString().getBytes(charset)).toUpperCase();
         
    }

    /**
     * 获取支付签名
     * @param obj
     * @param signKey
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws UnsupportedEncodingException 
     */
    public static  boolean verifySign(Object obj, String signKey,String charset,String signed,IFilter<Field> filedFilter,IConverter<String, String> nameConvert) throws IllegalArgumentException, IllegalAccessException, UnsupportedEncodingException {
        if (null == obj  || StringUtils.isBlank(signKey)){
            LOGGER.error("签名信息关键信息不能为空 obj:"+obj+"  signKey:"+signKey);
            return false;
        }
        StringBuilder buf = new StringBuilder();
        List<String> list = new ArrayList<String>();
        //取类的签名字段信息值
        Field[] fields = obj.getClass().getDeclaredFields();
        list = getSignContent(fields,obj,filedFilter,nameConvert);
        //取父类的签名字段信息
        Field[] superfields = obj.getClass().getSuperclass().getDeclaredFields();
        list.addAll(getSignContent(superfields,obj,filedFilter, nameConvert));
        if (null != list && !list.isEmpty()){
            //对数组进行字典排序
            Collections.sort(list, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            //拼接成字符串
            for (String str : list) {
                buf.append(str);
            }
            buf.append(KEY).append("=").append(signKey);
        }
        return DigestUtils.md5Hex(buf.toString().getBytes(charset)).toUpperCase().equals(signed);
         
    }
    
    
    /**
     * 遍历对象字段.取不是NotSign得字段进行URL参数拼接
     * @param fields
     * @param obj
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    private static List<String> getSignContent(Field[] fields, Object obj,IFilter<Field> filedFilter,IConverter<String, String> nameConvert)
            throws IllegalArgumentException, IllegalAccessException {
        List<String> signList = new ArrayList<String>();
        if (null != fields && null != obj) {
            for (Field f : fields) {
                f.setAccessible(true);
                Object value = f.get(obj);
                if (null != value && !"".equals(value)) {
                    //过滤字段注解是NotSign 并且 NotSign的值是true的
                   /* if (f.isAnnotationPresent(NotSign.class) && f.getAnnotation(NotSign.class).value()) {
                        continue;
                    }*/
                	if(filedFilter!=null&&filedFilter.filter(f)){
                		continue;
                	}
                	String name=f.getName();
                	if(nameConvert!=null){
                		name=nameConvert.convert(name);
                	}
					if (!StringUtils.isEmpty(name)) {
						signList.add(f.getName() + "=" + f.get(obj) + "&");
					}
                }
            }
        }
        return signList;
    }


}
