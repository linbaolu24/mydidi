package cn.com.didi.core.lock;

import java.util.concurrent.TimeUnit;

public interface ILock {
	ILock NULL_LOCK=new NullLock();
	public void lock();
	public boolean lock(Long timeOut);
	public boolean lock(Long timeOut,TimeUnit unit);
	public void unlock();
	public static class NullLock implements ILock{

		@Override
		public void lock() {
		}

		@Override
		public boolean lock(Long timeOut) {
			return true;
		}

		@Override
		public boolean lock(Long timeOut, TimeUnit unit) {
			return true;
		}

		@Override
		public void unlock() {
			// TODO Auto-generated method stub
			
		}
		
	}
}
