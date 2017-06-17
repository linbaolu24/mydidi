package cn.com.didi.core.message;

public abstract class Message {
    // 错误代码
    private String code;

    // 错误消息
    private String message;

    public Message(){
    }

    public Message(String code) {
        this.code = code;
    }

    public Message(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public abstract  String getMessage(Object ...arg);
    public String toString(){
        return code+":"+message;
    }
}
