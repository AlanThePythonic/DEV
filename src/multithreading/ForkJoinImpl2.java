package multithreading;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/*
 * 
 * Fork Join Framework core is ForkJoinPool，
 * It can get a ForkJoinTask，and return the result。
 * There are two subclasses of ForkJoinTask: 
 * RecursiveTask（Returned）
 * and RecursiveAction（Non-Returned）， 
 * 
 * */

public class ForkJoinImpl2 extends RecursiveTask<Integer> {
	private static final long serialVersionUID = -6196480027075657316L;

	private static final int THRESHOLD = 500000;

	private long[] array;

	private int low;

	private int high;

	public ForkJoinImpl2(long[] array, int low, int high) {
		this.array = array;
		this.low = low;
		this.high = high;
	}

	@Override
	protected Integer compute() {
		int sum = 0;
		if (high - low <= THRESHOLD) {
			// Calculate directly if lower than THRESHOLD
			for (int i = low; i < high; i++) {
				sum += array[i];
			}
		} else {

			// 1. Big task is separated by 2 tasks
			int mid = (low + high) >>> 1;
			ForkJoinImpl2 left = new ForkJoinImpl2(array, low, mid);
			ForkJoinImpl2 right = new ForkJoinImpl2(array, mid + 1, high);

			// 2. Calculate individually
			left.fork();
			right.fork();

			// 3. Join the result from all tasks
			sum = left.join() + right.join();
		}
		return sum;
	}

	public static void main(String[] args) throws ExecutionException, InterruptedException {

		long[] array = genArray(1000000);

		System.out.println(Arrays.toString(array));

		ForkJoinImpl2 sumTask = new ForkJoinImpl2(array, 0, array.length - 1);

		long begin = System.currentTimeMillis();

		ForkJoinPool forkJoinPool = new ForkJoinPool();
		forkJoinPool.submit(sumTask);

		Integer result = sumTask.get();

		long end = System.currentTimeMillis();
		System.out.println(String.format("Result : %s,  Used Time : %sms", result, end - begin));
	}

	private static long[] genArray(int size) {

		long[] array = new long[size];
		for (int i = 0; i < size; i++) {
			array[i] = new Random().nextLong();
		}
		return array;
	}
}
