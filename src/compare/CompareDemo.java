package compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CompareDemo {

	public static void main(String[] args) {

		ArrayList<Person> persons = new ArrayList<Person>();
		String[] names = { "StormMa", "Jack", "Rose", "Mary" };

		int[] ages = { 20, 21, 21, 18 };

		for (int i = 0; i < 4; i++)
			persons.add(new Person(names[i], ages[i]));

		System.out.println("Before sorted: " + persons);

		Collections.sort(persons);
		System.out.println("Sorted by Name： " + persons);

		Collections.sort(persons, new AscAgeComparator());
		System.out.println("Sorted by age desc" + persons);

		Collections.sort(persons, new DscAgeComparator());
		System.out.println("Sorted by age aesc" + persons);

		Collections.sort(persons, new AscAgeAscNameComparator());
		System.out.println("Sorted by name and age desc: " + persons);
	}
}

class Person implements Comparable<Person> {
	private String name;
	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	boolean equals(Person o) {
		if (this.age == o.age && this.name == o.name)
			return true;
		return false;
	}

	@Override
	public int compareTo(Person o) {
		// TODO Auto-generated method stub
		return this.name.compareTo(o.name);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "( name: " + name + "," + "age: " + age + ")";
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

class AscAgeComparator implements Comparator<Person> {

	@Override
	public int compare(Person o1, Person o2) {
		// TODO Auto-generated method stub
		return o1.getAge() - o2.getAge();
	}
}

class DscAgeComparator implements Comparator<Person> {

	@Override
	public int compare(Person o1, Person o2) {
		// TODO Auto-generated method stub
		return -(o1.getAge() - o2.getAge());
	}
}

class AscAgeAscNameComparator implements Comparator<Person> {

	@Override
	public int compare(Person o1, Person o2) {
		// TODO Auto-generated method stub
		if (o1.getAge() == o2.getAge())
			return o1.getName().compareTo(o2.getName());
		else
			return o1.getAge() - o2.getAge();
	}
}