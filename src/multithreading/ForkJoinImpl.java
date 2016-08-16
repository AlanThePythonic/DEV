package multithreading;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinImpl {
	private static double[] d = { 1.2, 3.45, 5.6 };

	private class ForkJoinTask extends RecursiveTask<Integer> {

		private static final long serialVersionUID = 1L;
		private int first;
		private int last;

		public ForkJoinTask(int first, int last) {
			this.first = first;
			this.last = last;
		}

		protected Integer compute() {
			int subCount;
			if (last - first < 10) {
				subCount = 0;
				for (int i = first; i <= last; i++) {
					if (d[i] < 0.5)
						subCount++;
				}
			} else {
				int mid = (first + last) >>> 1;
				ForkJoinTask left = new ForkJoinTask(first, mid);
				left.fork();
				ForkJoinTask right = new ForkJoinTask(mid + 1, last);
				right.fork();
				subCount = left.join();
				subCount += right.join();
			}
			return subCount;
		}
	}

}