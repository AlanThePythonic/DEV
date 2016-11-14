package serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.MessageFormat;

import java.io.Serializable;

class Person implements Serializable {

	private static final long serialVersionUID = -5809782578272943999L;
	private int age;
	private String name;
	private String sex;

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	public String getSex() {
		return sex;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}

public class TestObjSerializeAndDeserialize {

	public static void main(String[] args) throws Exception {
		SerializePerson();
		Person p = DeserializePerson();
		System.out.println(MessageFormat.format("name={0},age={1},sex={2}", p.getName(), p.getAge(), p.getSex()));
	}

	private static void SerializePerson() throws FileNotFoundException, IOException {

		Person person = new Person();
		person.setName("Allen");
		person.setAge(25);
		person.setSex("M");

		try (ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(new File("Person.txt")))) {
			oo.writeObject(person);
			System.out.println("Person object has been succeded to be converted as Serialized Object.");
			oo.close();
		}
	}

	private static Person DeserializePerson() throws Exception, IOException {

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("Person.txt")))) {
			Person person = (Person) ois.readObject();
			System.out.println("Person object has been deserialized");
			return person;
		}
	}

}
