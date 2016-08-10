package multithreading;

import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

public class LockImpl {
	private final ReentrantLock lock;

	public LockImpl() {
		lock = new ReentrantLock();
	}

	public static void main(String[] args) throws InterruptedException {
		LockImpl lockDemo = new LockImpl();
		Runnable runnable = () -> {
			try {
				lockDemo.lock.lock();
				System.out.println(String.format("%s %s locked", new Date(System.currentTimeMillis()),
						Thread.currentThread().getName()));
			} finally {
				lockDemo.lock.unlock();
			}

		};

		Thread threadA = new Thread(runnable, "Thread A");
		Thread threadB = new Thread(runnable, "Thread B");

		threadA.start();
		threadB.start();
	}
}