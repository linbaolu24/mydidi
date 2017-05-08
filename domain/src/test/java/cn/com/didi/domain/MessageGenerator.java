package cn.com.didi.domain;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import cn.com.didi.domain.util.DomainMessageConstans;

public class MessageGenerator {
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		Field[] fields= DomainMessageConstans.class.getFields();
		StringBuilder sb=new StringBuilder();
		for(Field one:fields){
			get(sb,one);
		}
		System.out.println(sb.toString());
	}
	public static void get(StringBuilder sb,Field field) throws IllegalArgumentException, IllegalAccessException{
		if(Modifier.isStatic(field.getModifiers())){
		String value=(String) field.get(null);
		 sb.append("<entry key=").append("\"").append(value).append("\"").append(" value=\"${msg.").append(value).append(":}\"></entry>\r\n");
		}
	}
}
