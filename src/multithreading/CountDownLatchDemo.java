package multithreading;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Labour implements Runnable {

	private CountDownLatch downLatch;
	private String name;

	public Labour(CountDownLatch downLatch, String name) {
		this.downLatch = downLatch;
		this.name = name;
	}

	public void run() {
		this.doWork();
		try {
			TimeUnit.SECONDS.sleep(new Random().nextInt(10));
		} catch (InterruptedException ie) {
		}
		System.out.println(this.name + " has done the work！");
		this.downLatch.countDown();

	}

	private void doWork() {
		System.out.println(this.name + " is working ...");
	}

}

class Boss implements Runnable {

	private CountDownLatch downLatch;

	public Boss(CountDownLatch downLatch) {
		this.downLatch = downLatch;
	}

	public void run() {
		System.out.println("Boss is waiting all the labours have done their work ......");
		try {
			this.downLatch.await();
		} catch (InterruptedException e) {
		}
		System.out.println("All the labours have done their works, boss can check now");
	}

}

public class CountDownLatchDemo {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();

		CountDownLatch latch = new CountDownLatch(3);

		Labour w1 = new Labour(latch, "Alan");
		Labour w2 = new Labour(latch, "Jenny");
		Labour w3 = new Labour(latch, "Petter");

		Boss boss = new Boss(latch);

		executor.execute(w3);
		executor.execute(w2);
		executor.execute(w1);
		executor.execute(boss);

		executor.shutdown();
	}

}