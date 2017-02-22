package cn.com.didi.thirdExt.message;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import cn.com.didi.core.message.DefaultMessageResolver;
import cn.com.didi.core.message.IMessageResolver;
import cn.com.didi.core.message.Message;
import cn.com.didi.core.message.MessageFactory;
import cn.com.didi.core.message.ResolvableMessage;


public class MessageApplicationContext extends MessageFactory implements ApplicationContextAware, InitializingBean {
    // 默认从配置文件中获取bean的名称
    private static final String DEFAULT_MESSAGE_MAP_NAME = "errorMessage";
    private static final IMessageResolver DEFAULT_RESOLVER = new DefaultMessageResolver();
    private IMessageResolver resolver;
    // 从配置文件中获取bean的名称
    private String messageName=DEFAULT_MESSAGE_MAP_NAME+"(_?|_.+)";

    // context
    private static ApplicationContext context;

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    public Object getMessage() {
        if (null == messageName || (messageName.length() == 0)) {
            return context.getBean(DEFAULT_MESSAGE_MAP_NAME);
        }else{
        	Pattern pt=Pattern.compile(messageName);
        	String[] beanNames=BeanFactoryUtils.beanNamesForTypeIncludingAncestors(context,Map.class);
        	Map<String,String> map=new HashMap<String,String>();
        	if(beanNames!=null){
        		for(String beanName:beanNames){
        			Matcher matcher=pt.matcher(beanName);
        			if(matcher.matches()){
        				map.putAll((Map)context.getBean(beanName));
        			}
        		}
        	}
        	return map;
        	
        }
        //return context.getBean(messageName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    @Override
    public Message createMessage(String code) {
        Map<String, String> messageCodeMap = (Map<String, String>) getMessage();
        String message = messageCodeMap.get(code);
        return new ResolvableMessage(code, message, resolver);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    protected void init() {
        IMessageResolver temp = getResolver();
        if (temp == null) {
            String[] resolvers = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(context, IMessageResolver.class);
            if(resolvers!=null&&resolvers.length>0){
                setResolver((IMessageResolver)context.getBean(resolvers[0]));
            }else{
                setResolver(DEFAULT_RESOLVER);
            }
        }
        setMessagefactory(this);
    }

    /**
     * @return the resolver
     */
    public IMessageResolver getResolver() {
        return resolver;
    }

    /**
     * @param resolver the resolver to set
     */
    public void setResolver(IMessageResolver resolver) {
        this.resolver = resolver;
    }

}
