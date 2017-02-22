package cn.com.didi.core.message;

import java.text.MessageFormat;

public class DefaultMessageResolver implements IMessageResolver {

    @Override
    public String resolveMessage(String code, String defaultMsg, Object... arg) {
        if (arg == null||arg.length==0) {
            return defaultMsg;
        } else {
            return MessageFormat.format(defaultMsg, arg);
        }
    }

    @Override
    public String resolveMessage(Message message, Object... arg) {
        return resolveMessage(message.getCode(), message.getMessage(), arg);
    }

}
