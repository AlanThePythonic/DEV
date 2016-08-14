package multithreading;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class ThreadPoolExecImpl {
	public static void main(String[] args) {

		ExecutorService pool = Executors.newFixedThreadPool(2);
		Thread t1 = new ThreadExec();
		Thread t2 = new ThreadExec();
		Thread t3 = new ThreadExec();
		Thread t4 = new ThreadExec();
		Thread t5 = new ThreadExec();

		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);
		pool.shutdown();
	}
}

class ThreadExec extends Thread {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " is executing ...");
	}
}