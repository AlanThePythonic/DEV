package multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicImpl {

	AtomicInteger counter = new AtomicInteger(0);
	int result = 0;

	public int count() {

		boolean flag;

		do {

			result = counter.get();
			flag = counter.compareAndSet(result, result + 1);

		} while (!flag); // if(result==result) result = 3;

		// or
		result = counter.getAndAdd(1); // result = i; i += 1;

		// or
		result = counter.addAndGet(1); // i += 3; result = i;

		// or
		counter.getAndDecrement(); // result = i--;

		// or
		counter.getAndIncrement(); // result = i++;

		// or
		counter.decrementAndGet(); // result = --i;

		// or
		counter.incrementAndGet(); // result = ++i;

		return result;
	}

	public static void main(String[] args) {

		final AtomicImpl c = new AtomicImpl();

		for (int i = 0; i < 10000; i++) {
			new Thread() {
				@Override
				public void run() {
					System.out.println(c.count());
				}
			}.start();
		}
	}

}