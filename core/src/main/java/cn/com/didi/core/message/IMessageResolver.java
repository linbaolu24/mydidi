package cn.com.didi.core.message;

public interface IMessageResolver {
    /**
     * @param code
     * @param arg
     * @param defaultMsg
     * @return
     */
    public String resolveMessage(String code,String defaultMsg,Object ...arg);
    /**
     * @param message
     * @param arg
     * @return
     */
    public String resolveMessage(Message message,Object... arg);
}
