package cn.com.didi.core.thread.impl;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import cn.com.didi.core.thread.ExecutorFactory;

/**
 * @author xlm
 *
 */
public class SimpleExecutorFactory implements ExecutorFactory {
	public boolean isMock() {
		return mock;
	}

	public void setMock(boolean mock) {
		this.mock = mock;
	}

	public int getCorePoolSize() {
		return corePoolSize;
	}

	public void setCorePoolSize(int corePoolSize) {
		this.corePoolSize = corePoolSize;
	}

	public int getMaximumPoolSize() {
		return maximumPoolSize;
	}

	public void setMaximumPoolSize(int maximumPoolSize) {
		this.maximumPoolSize = maximumPoolSize;
	}

	public int getKeepAliveTime() {
		return keepAliveTime;
	}

	public void setKeepAliveTime(int keepAliveTime) {
		this.keepAliveTime = keepAliveTime;
	}

	public Integer getQueueSize() {
		return queueSize;
	}

	public void setQueueSize(Integer queueSize) {
		this.queueSize = queueSize;
	}

	public void setName(String name) {
		this.name = name;
	}

	protected ThreadPoolExecutor threadPoolExecutor;
	protected String name;
	protected int corePoolSize;
	protected int maximumPoolSize;
	protected int keepAliveTime;
	protected Integer queueSize = Integer.MAX_VALUE;
	private boolean mock;

	protected void init() {
		if (isMock()) {
			return;
		}
		threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime==-1?Integer.MAX_VALUE:keepAliveTime, TimeUnit.SECONDS,
				new LinkedBlockingQueue<>(queueSize==-1?Integer.MAX_VALUE:queueSize), new ThreadFactory() {

					@Override
					public Thread newThread(Runnable target) {
						return new Thread(target, "执行器+[" + name + "的线程]");
					}
				});
	}

	@Override
	public Executor createExecutor(String name) {
		return threadPoolExecutor;
	}

	@Override
	public Executor createExecutor() {
		return threadPoolExecutor;
	}

	@Override
	public String getName() {
		return name;
	}

}
