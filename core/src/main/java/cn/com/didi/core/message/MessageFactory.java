package cn.com.didi.core.message;

public abstract class MessageFactory {
    private static final MessageFactory DEFAULT_MESSAGEFACTORY = new MessageFactory() {
        IMessageResolver resolver=new DefaultMessageResolver();
        @Override
        public Message createMessage(String code) {
            return new ResolvableMessage(code, code, resolver);
        }
		@Override
		public Message createMessage(String code, String defaultMessage) {
			return new ResolvableMessage(code, defaultMessage, resolver);
		}
    };
    private static MessageFactory messagefactory = null;

    public abstract Message createMessage(String code);
    
    public abstract Message createMessage(String code,String defaultMessage);

    public static Message creatMessage(String code) {
        if (messagefactory == null) {
            return DEFAULT_MESSAGEFACTORY.createMessage(code);
        }
        return messagefactory.createMessage(code);
    }
    public static Message creatMessage(String code,String defaultMessage) {
        if (messagefactory == null) {
            return DEFAULT_MESSAGEFACTORY.createMessage(code);
        }
        return messagefactory.createMessage(code);
    }

    /**
     * @return the messagefactory
     */
    public static MessageFactory getMessagefactory() {
        return messagefactory;
    }

    /**
     * @param messagefactory the messagefactory to set
     */
    public static void setMessagefactory(MessageFactory messagefactory) {
        if (MessageFactory.messagefactory == null) {
            MessageFactory.messagefactory = messagefactory;
        }
    }

}
