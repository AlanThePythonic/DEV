package multithreading;

import java.util.concurrent.Exchanger;

public class ExchangerImpl {
	public static void main(String[] args) throws InterruptedException {

		Exchanger<String> ex = new Exchanger<>();

		new A(ex).start();

		Thread.sleep(1000);

		new B(ex).start();
	}
}

class A extends Thread {
	private Exchanger<String> ex;

	public A(Exchanger<String> ex) {
		this.ex = ex;
		this.setName("A");
	}

	@Override
	public void run() {
		String str = null;

		try {
			str = ex.exchange("Hello?");
			System.out.println(Thread.currentThread().getName() + " : " + str);

			str = ex.exchange("A");
			System.out.println(Thread.currentThread().getName() + " : " + str);

			/*str = ex.exchange("B");
			System.out.println(Thread.currentThread().getName() + " : " + str);*/
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class B extends Thread {

	private Exchanger<String> ex;

	public B(Exchanger<String> ex) {
		this.ex = ex;
		this.setName("B");
	}

	@Override
	public void run() {
		String str = null;

		try {
			str = ex.exchange("Hi!");
			System.out.println(Thread.currentThread().getName() + " : " + str);

			/*str = ex.exchange("1");
			System.out.println(Thread.currentThread().getName() + " : " + str);*/

			str = ex.exchange("2");
			System.out.println(Thread.currentThread().getName() + " : " + str);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}