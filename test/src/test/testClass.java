package test;

public class testClass {	// does a class need (or can have) a constructor?
	
	String name;
	int age;
	
	public void meow() {	// Not static: method can only be called on an object
		System.out.println("Meow");
	}
	
	public static void dingDong() {		// Public: method can be called anywhere (?)
										// Static: method can be called directly on the class, doesn't need to be called on a class object
		System.out.println("Ding Dong");
	}

}
