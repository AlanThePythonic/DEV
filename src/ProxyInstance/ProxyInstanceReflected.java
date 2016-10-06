package ProxyInstance;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface instanceInterface {

	void doSomething();

	void somethingElse(String arg);
}

class RealObject implements instanceInterface {

	public void doSomething() {
		System.out.println("Do Something");
	}

	@Override
	public void somethingElse(String arg) {
		System.out.println("Do Something else " + arg);
	}
}

class DynamicProxyHandler implements InvocationHandler {

	private Object proxyed;

	public DynamicProxyHandler(Object proxyed) {
		this.proxyed = proxyed;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		System.out.println("Proxy is working now ..");
		return method.invoke(proxyed, args);
	}

}

public class ProxyInstanceReflected {

	public static void main(String[] args) {

		RealObject real = new RealObject();
		instanceInterface proxy = (instanceInterface) Proxy.newProxyInstance(instanceInterface.class.getClassLoader(),
				new Class[] { instanceInterface.class }, new DynamicProxyHandler(real));

		proxy.doSomething();
		proxy.somethingElse("ABC");
	}
}
