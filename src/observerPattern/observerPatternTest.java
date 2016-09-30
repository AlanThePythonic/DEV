package observerPattern;

import java.util.Observable;
import java.util.Observer;

class AxeGangBoss extends Observable {
	private String mName;

	public AxeGangBoss(String name) {
		mName = name;
	}

	public void sendMsg(String msg) {

		setChanged();
		notifyObservers(msg);
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}

}

class AxeGangPeople implements Observer {
	String mName;

	public AxeGangPeople(String name) {
		mName = name;
	}

	@Override
	public void update(Observable observable, Object data) {
		AxeGangBoss writer;
		if (observable instanceof AxeGangBoss) {
			writer = (AxeGangBoss) observable;
			System.out.println(getName() + " get the message from " + writer.getName() + " : " + data.toString());
		}
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}
}

public class observerPatternTest {

	public static void testSendMsg() throws Exception {
		

		AxeGangBoss boss = new AxeGangBoss("Boss");

		AxeGangPeople gangA = new AxeGangPeople("GangA");
		AxeGangPeople gangB = new AxeGangPeople("GangB");

		boss.addObserver(gangA);
		boss.addObserver(gangB);
		boss.sendMsg("Please help me !");
	}

	public static void main(String[] arg) {

		try {
			testSendMsg();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
