package cn.com.didi.core.message;

public  class ResolvableMessage extends Message{
    private IMessageResolver resolver;

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
    public   String getMessage(Object ...arg){
        if(resolver!=null){
            return resolver.resolveMessage(this, arg);
        }
        return getMessage();
    }

    public ResolvableMessage(String code,String message,IMessageResolver resolver) {
        super(code,message);
        this.resolver = resolver;
    }
    
}
