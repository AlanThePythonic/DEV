package multithreading;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class ThreadPoolExecImpl {
	public static void main(String[] args) {

		ExecutorService pool = Executors.newFixedThreadPool(2);
		Thread t1 = new ThreadExec();
		t1.setName("t1");
		Thread t2 = new ThreadExec();
		t2.setName("t2");
		Thread t3 = new ThreadExec();
		t3.setName("t3");
		Thread t4 = new ThreadExec();
		t4.setName("t4");
		Thread t5 = new ThreadExec();
		t5.setName("t5");

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
		System.out.println(this.getName() + " is executing ... by " + Thread.currentThread().getName());
	}
}