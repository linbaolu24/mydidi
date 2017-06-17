package cn.com.didi.core.lock;

public interface LockManager {
	LockManager NULL_LOCKMANAGER=new NullLockManager();
	/**
	 * 
	 * @param name
	 * @param timeout 超时
	 * @return 如果返回true 表示锁成功 如果返回false 表示超时
	 */
	//public boolean lock(String name,long timeOut);
	/**
	 * @param name
	 * @return
	 */
	//public boolean unlock(String name);
	public ILock accquireLock(String name);
	public static class NullLockManager implements LockManager{
		
		@Override
		public ILock accquireLock(String name) {
			return ILock.NULL_LOCK;
		}
		
	}
}
