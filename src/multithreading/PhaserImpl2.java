package multithreading;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserImpl2 {

	public static void main(String[] args) {

		Phaser phaser = new Phaser(1);

		for (int i = 0; i < 3; i++) {
			// char(97) = a
			new TestPhaserThread((char) (97 + i), phaser).start();
		}

		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		phaser.arrive();
	}
}

class TestPhaserThread extends Thread {

	private char c;
	private Phaser phaser;

	public TestPhaserThread(char c, Phaser phaser) {
		this.c = c;
		this.phaser = phaser;
	}

	@Override
	public void run() {

		phaser.awaitAdvance(phaser.getPhase());

		for (int i = 0; i < 100; i++) {

			System.out.print(Thread.currentThread().getName() + " : ");
			System.out.print(c + " ");

			if (i % 10 == 9) {
				System.out.println();
			}
		}
	}
}
