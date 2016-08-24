package multithreading;

import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/*
 * This framework is designed to solve problems that can be broken into smaller tasks using  
 * the divide and conquer technique. Inside a task, 
 * you check the size of the problem you want to resolve and, if it's bigger than an established size, 
 * you divide it in smaller tasks that are executed using the framework. 
 * If the size of the problem is smaller than the established size,
 * you solve the problem directly in the task and then, optionally, it returns a result.
 */

class Task extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 1L;

	private List<Integer> numberList;
	private int start;
	private int end;
	private int THRESHOLD = 2;

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
		// Calculate directly if lower than THRESHOLD
		if (end - start <= THRESHOLD) {
			return sum();
		} else {
			// 1. Big task is separated by 2 tasks
			int pivot = (end + start) / 2;
			Task task1 = new Task(numberList, start, pivot);
			Task task2 = new Task(numberList, pivot + 1, end);
			
			// 2. Calculate individually
			task1.fork();
			task2.fork();
			
			// 3. Join the result from all tasks
			System.out.println(task1.join() + " : " + task2.join());
			return task1.join() + task2.join();
		}

	}

	private Integer sum() {
		Integer sum = 0;
		for (int i = start; i <= end; i++) {
			sum += this.numberList.get(i);

		}
		System.out.println(Thread.currentThread().getName() + " : " + sum);
		return sum;
	}
}

public class ForkJoinImpl {

	public static void main(String[] args) throws Exception {

		ForkJoinPool pool = new ForkJoinPool();

		Task task = new Task(getData(10));
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
