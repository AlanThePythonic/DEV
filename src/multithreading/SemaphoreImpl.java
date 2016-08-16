package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreImpl {

	public static void main(String[] args) {

		ExecutorService exec = Executors.newCachedThreadPool();

		final Semaphore semph = new Semaphore(3);

		for (int index = 1; index < 10; index++) {

			final int NO = index;
			Runnable ru = () -> {

				try {

					semph.acquire();
					System.out.println("accessing: " + NO);

					Thread.sleep((long) (Math.random() * 1000));

					System.out.println("release: " + NO);
					semph.release();

				} catch (InterruptedException e) {

				}

			};
			exec.execute(ru);
		}

		exec.shutdown();

	}

}
