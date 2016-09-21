package multithreading;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueImpl {

	public static void test01() {

		PriorityQueue<String> pq = new PriorityQueue<String>();
		pq.offer("J");
		pq.offer("B");
		pq.offer("H");
		pq.offer("Z");
		pq.offer("M");
		pq.offer("K");
		String s;
		while ((s = pq.poll()) != null) {
			System.out.print(s + ", ");
		}
	}

	public static void test02() {

		Comparator<String> compare = new Comparator<String>() {
			public int compare(String a, String b) {
				return a.compareTo(b) * -1; // Using descending order
			}
		};

		PriorityQueue<String> pq = new PriorityQueue<String>(3, compare);

		pq.offer("J");
		pq.offer("B");
		pq.offer("H");
		pq.offer("Z");
		pq.offer("M");
		pq.offer("K");

		String s;

		while ((s = pq.poll()) != null) { // default is ascending order.
			System.out.print(s + ", ");
		}
	}

	public static void main(String[] args) {

		test01();
		System.out.println();
		test02();
	}
}