package multithreading;

import java.util.Random;

public class ThreadLocalImpl implements Runnable {

	private final static ThreadLocal<People> threadLocal = new ThreadLocal<People>();

	public static void main(String[] agrs) {

		ThreadLocalImpl td = new ThreadLocalImpl();
		Thread t1 = new Thread(td);
		Thread t2 = new Thread(td);
		t1.start();
		t2.start();
	}

	public void run() {
		accessStudent();
	}

	public void accessStudent() {

		String currentThreadName = Thread.currentThread().getName();
		System.out.println(currentThreadName + " is running!");

		Random random = new Random();

		int age = random.nextInt(100);
		System.out.println("thread " + currentThreadName + " set age to:" + age);

		People people = getPeople();
		people.setAge(age);

		System.out.println("thread " + currentThreadName + " first read age is:" + people.getAge());

	}

	protected People getPeople() {
		People people = threadLocal.get();

		if (people == null) {
			people = new People();
			threadLocal.set(people);
		}
		return people;
	}
}

class People {
	private int age = 0;

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}