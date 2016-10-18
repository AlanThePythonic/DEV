package multithreading;

import java.util.concurrent.Phaser;

public class PhaserImpl3 {

	public static void main(String[] args) {

		Phaser phaser = new Phaser(3) { // No of Working thread

			@Override
			protected boolean onAdvance(int phase, int registeredParties) {

				System.out.println("\n ----------- Seperation Line ------------");
				// When remaining a thread, that should be a main thread, return
				// true for representing completed
				return registeredParties == 1;
			}
		};
		System.out.println("Program started");
		for (int i = 0; i < 3; i++) { // Create and Start 3 threads
			new TestPhaserThread2((char) (97 + i), phaser).start();
		}

		phaser.register(); // Register the nain thread to phaser, the phaser
							// will manage 4 threads

		while (!phaser.isTerminated()) {// While a pharser is not completed,
										// main thread will be waiting until the
										// phaser ended
			int n = phaser.arriveAndAwaitAdvance();
		}
		// Phaser and 3 threads are also completed
		System.out.println("Program ended");
	}
}

class TestPhaserThread2 extends Thread {
	
	private char c;
	private Phaser phaser;

	public TestPhaserThread2(char c, Phaser phaser) {
		this.c = c;
		this.phaser = phaser;
	}

	@Override
	public void run() {
		while (!phaser.isTerminated()) {
			for (int i = 0; i < 10; i++) { // print the current letter for 10
											// times
				System.out.print(c + " ");
			}
			// After printed the current letter，make it updates to the 3 after
			// letters，B will be updated to E，to be used to printed on next step

			c = (char) (c + 3);
			if (c > 'z') {
				// If the char is more than Z, the phaser will reduce a thread
				// and quit the loop for exiting current thread
				// When 3 working threads executed this statement，There is only
				// one thread on phaser
				phaser.arriveAndDeregister();
				break;
			} else {
				/*
				 * Else， wait other threads to complete, and start the next step
				 * together(other threads)
				 */
				phaser.arriveAndAwaitAdvance();
			}
		}
	}
}
