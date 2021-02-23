package _02_generics_vs_object;

// Below we have made an ObjectsList and a GenericsList to see the differences
// READ through the code and comments
public class GenericsVsObjects {
	public static void main(String[] args) {
		
		// Notice we have a compile error. Why is this?
		// Comment out that line of code once you understand the error
		GenericsList<String> generics = new GenericsList<String>();
		generics.add("Bob");
		generics.add("Anne");
		generics.add(5);
		generics.add("Tom");
		
		// Why don't we have the same error here?
		// This is because any object(String,Integer,etc) 
		// falls under the Object class
		ObjectsList objects = new ObjectsList();
		objects.add("Bob");
		objects.add("Anne");
		objects.add(5);
		objects.add("Tom");
		
		System.out.println(generics);
		System.out.println(objects);
		
		for(int i = 0; i < generics.length(); i++) {
			String s = generics.get(i);
			System.out.println(s);
		}
		for(int i = 0; i < objects.length(); i++) {
			String s = (String) objects.get(i);
			System.out.println(s);
		}
		
		// AFTER looking over the code, run it and see what happens
		// This is one of the reasons Generics are preferred over simply 
		// declaring something as an Object
		
	}
}
