package java8;

@FunctionalInterface
interface SimpleFuncInterface {

	// Only one method and the overriding methods can be abstract here

	public void doWork();

	public String toString();

	public boolean equals(Object o);
}

@FunctionalInterface
interface ComplexFunctionalInterface extends SimpleFuncInterface {

	// Default Methods
	default public void doSomeWork() {
		System.out.println("Doing some work in interface impl...");
	}

	default public void doSomeOtherWork() {
		System.out.println("Doing some other work in interface impl...");
	}

	// Static default method
	static void doPublicWork() {
		System.out.println("Doing public work in interface impl...");
	}
}

class TestClass implements ComplexFunctionalInterface {

	// Have to override the method - doWork() on here
	// Due to the SimpleFuncInterface has been implemented by
	// ComplexFunctionalInterface
	@Override
	public void doWork() {
		System.out.println("Doing work initially ..");
	}

	public void print() {
		this.doWork();
		ComplexFunctionalInterface.super.doSomeWork();
		ComplexFunctionalInterface.super.doSomeOtherWork();
		ComplexFunctionalInterface.doPublicWork();
	}
}

public class FunctionalInterfaceDefaultMethodsImpl {

	public static void carryOutWork(SimpleFuncInterface sfi) {
		sfi.doWork();
	}

	public static void main(String[] args) {
		TestClass tc = new TestClass();
		tc.print();
		carryOutWork(() -> System.out.println("Doing work initially again"));
		carryOutWork(new SimpleFuncInterface() {
			@Override
			public void doWork() {
				System.out.println("Doing work initially again by old style");
			}
		});
	}
}