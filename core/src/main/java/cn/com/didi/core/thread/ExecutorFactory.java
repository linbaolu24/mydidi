package cn.com.didi.core.thread;

import java.util.concurrent.Executor;

/**
 * @author xlm
 */
public interface ExecutorFactory {
	public Executor createExecutor(String name);
	/**
	 * 创建默认执行器
	 * @return
	 */
	public Executor createExecutor();
	public String getName();
}
