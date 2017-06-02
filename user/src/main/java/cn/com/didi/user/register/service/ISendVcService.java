package cn.com.didi.user.register.service;

public interface ISendVcService {
	public void send(String phone,int vc,int valid);
	public void send(String[] phone,String message);
}
