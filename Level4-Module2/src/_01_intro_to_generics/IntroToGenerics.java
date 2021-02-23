package _01_intro_to_generics;

// Generics in Java are a way to define one variable that can act 
// as different types.
// Generics can be used in methods and classes. 

// The syntax for Generics are the angle brackets <>
// Think about it..you've used Generics without knowing

// ArrayList<String> aList = new ArrayList<String>()
// The Generic type is defined to be String

public class IntroToGenerics<T> {
	//This variable can be any object type because it is type T(our Generic)
	private T thing;
	
	public void setThing(T thing) {
		this.thing = thing;
	}
	
	public T getThing() {
		return this.thing;
	}
}
