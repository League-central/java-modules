package _01_intro_to_generics;


public class IntroToGenericsRunner {
	public static void main(String[] args) {
		
		//In order to create an object with a generic variable we have to define
		//the type of Object we want the Generic to be
		
		//Defined to be String
		IntroToGenerics<String> g1 = new IntroToGenerics<String>();
		g1.setThing("Hello");
		System.out.println(g1.getThing());
		
		//Defined to be Integer
		IntroToGenerics<Integer> g2 = new IntroToGenerics<Integer>();
		g2.setThing(123);
		System.out.println(g2.getThing());
		
		//Defined to be Double
		IntroToGenerics<Double> g3 = new IntroToGenerics<Double>();
		g3.setThing(456.789);
		System.out.println(g3.getThing());
		
	}
}
