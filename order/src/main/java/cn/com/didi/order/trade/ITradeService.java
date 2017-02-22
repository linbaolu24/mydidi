package cn.com.didi.order.trade;

/**
 * @author xlm
 *
 */
public interface ITradeService {
	/**
	 * 转账
	 */
	void transferAccounts();
	/**
	 * 收到钱
	 */
	void charge();
}
