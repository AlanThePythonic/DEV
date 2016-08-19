package multithreading;

import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

class Task extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 1L;

	private List<Integer> numberList;
	private int start;
	private int end;
	private int THRESHOLD = 10;

	public Task(List<Integer> numberList) {
		this.numberList = numberList;
		start = 0;
		end = this.numberList.size() - 1;
	}

	public Task(List<Integer> numberList, int start, int end) {
		this.numberList = numberList;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		if (end - start <= THRESHOLD) {
			return sum();
		} else {
			int pivot = (end + start) / 2;
			Task task1 = new Task(numberList, start, pivot);
			Task task2 = new Task(numberList, pivot + 1, end);
			task1.fork();
			task2.fork();
			return task1.join() + task2.join();
		}

	}

	private Integer sum() {
		Integer sum = 0;
		for (int i = start; i <= end; i++) {
			sum += this.numberList.get(i);
		}
		return sum;
	}
}

public class ForkJoinImpl {

	public static void main(String[] args) throws Exception {

		ForkJoinPool pool = new ForkJoinPool();

		Task task = new Task(getData(100));
		Future<Integer> result = pool.submit(task);
		System.out.println(result.get());
	}

	private static List<Integer> getData(int len) {

		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= len; i++) {
			list.add(i);
		}
		return list;
	}

}
