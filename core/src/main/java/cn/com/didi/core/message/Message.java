package cn.com.didi.core.message;

/**
 * <p>标题: Message</p>
 * <p>描述: Message</p>
 * <p>版权: 税友软件集团股份有限公司</p>
 * <p>创建时间:2016/1/25 </p>
 * <p>作者：liqing</p>
 * <p>修改历史记录：</p>
 */
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
