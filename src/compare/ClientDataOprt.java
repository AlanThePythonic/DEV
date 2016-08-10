package compare;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

// Comparable
class ClientInfo implements Comparable<ClientInfo>, Comparator<ClientInfo> {

	private String name;

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	private int age;

	public ClientInfo() {
	}

	public ClientInfo(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public int compareTo(ClientInfo o) {
		return this.age - o.age; // Sorted by age
	}

	@Override
	public String toString() {
		return String.format("ClientInfo(%s, %d)", name, age);
	}

	@Override
	public int compare(ClientInfo o1, ClientInfo o2) {
		return o1.getName().compareTo(o2.getName()); // Sorted by First Char of
	}

}

// Comparator
class AgeComparator implements Comparator<ClientInfo> {
	public int compare(ClientInfo op1, ClientInfo op2) {
		ClientInfo eOp1 = (ClientInfo) op1;
		ClientInfo eOp2 = (ClientInfo) op2;

		return eOp1.getAge() - (eOp2.getAge());
	}
}

class NameComparator implements Comparator<ClientInfo> {
	public int compare(ClientInfo op1, ClientInfo op2) {
		ClientInfo eOp1 = (ClientInfo) op1;
		ClientInfo eOp2 = (ClientInfo) op2;

		return eOp1.getName().compareTo(eOp2.getName());
	}
}

public class ClientDataOprt {

	public static void main(String[] args) {

		List<ClientInfo> storage = Arrays.asList(new ClientInfo("Alan", 25), new ClientInfo("Staryea", 23),
				new ClientInfo("Xigor", 23), new ClientInfo("Michael", 29));

		Collections.sort(storage);
		System.out.println(storage);

		Collections.sort(storage, new ClientInfo());
		System.out.println(storage);

	}
}
