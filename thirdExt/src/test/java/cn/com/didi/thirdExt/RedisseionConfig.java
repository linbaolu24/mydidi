package cn.com.didi.thirdExt;

import java.util.Random;

import org.apache.commons.lang.math.RandomUtils;
import org.redisson.config.Config;

import cn.com.didi.core.lock.ILock;
import cn.com.didi.thirdExt.lock.RedissonLockManager;

public class RedisseionConfig {
	public static void main(String[] args) {
		Config config = new Config();
		config.useSingleServer().setAddress("118.178.226.138:6380").setPassword("Didifuwu2017redis");
		RedissonLockManager manager = new RedissonLockManager();
		manager.setConfig(config);
		manager.init();
		for (int i = 0; i < 5; i++) {

			new Thread(new Runnable() {

				@Override
				public void run() {
					ILock lock = manager.accquireLock("lockTest");
					boolean looked = false;
					try {
						Long start=System.currentTimeMillis();
						looked = lock.lock(1L);
						
						if (looked) {
							System.out.println(Thread.currentThread().getName() + "==========上锁成功===========");
							while (true) {
								//System.out.println(Thread.currentThread().getName() + "==========上锁成功===========");
								if (System.currentTimeMillis()-start>1022) {
									System.out.println(Thread.currentThread().getName() + "==========跳出循环===========");
									break;
								}
							}
						} else {

							System.out.println(Thread.currentThread().getName() + "==========上锁超时===========");

							return;
						}
					} finally {
						if (looked) {
							System.out.println(Thread.currentThread().getName() + "==========解锁===========");
							lock.unlock();
						}
					}
				}
			}, "线程[" + i + "]").start();

		}

	}
}
