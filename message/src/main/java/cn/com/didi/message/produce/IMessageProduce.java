package cn.com.didi.message.produce;

import cn.com.didi.message.IMessage;

public interface IMessageProduce {
	public void pushStringMessage(IMessage<String> message);
}
