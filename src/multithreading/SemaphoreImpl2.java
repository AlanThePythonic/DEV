package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreImpl2 {
	public static void main(String[] args) {

		final Semaphore semaphore = new Semaphore(2);
		ExecutorService executorService = Executors.newCachedThreadPool();

		for (int i = 0; i < 10; i++) {

			final int index = i;

			executorService.execute(new Runnable() {
				public void run() {
					try {
						semaphore.acquire();
						System.out.println(
								"Thread [" + Thread.currentThread().getName() + "] has gotten a permit : " + index);
						TimeUnit.SECONDS.sleep(1);
						semaphore.release();
						System.out.println("Tasks Avaliable : " + semaphore.availablePermits());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		executorService.shutdown();
	}
}
