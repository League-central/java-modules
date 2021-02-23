package _02_generics_vs_object;

// Here we have a custom list implemented using Generics(T object)
// This allows us to create a list of any object(String, Integer, Double, etc)
@SuppressWarnings("unchecked")
public class GenericsList<T> {
	private T[] list;
	
	public GenericsList() {
		list = (T[])new Object[0];
	}
	
	public T get(int loc) throws IndexOutOfBoundsException {
		if(loc < 0 || loc > list.length) throw new IndexOutOfBoundsException();
		return list[loc];
	}
	
	public void add(T val) {
		T[] tList = (T[])new Object[list.length + 1];
		for(int i = 0; i < list.length; i++) {
			tList[i] = list[i];
		}
		tList[list.length] = val;
		list = tList;
	}
	
	public int length() {
		return list.length;
	}
	
	public String toString() {
		String s = "[";
		for(int i = 0; i < list.length-1; i++) {
			s+=list[i]+", "; 
		}
		s+=list[list.length-1]+"]";
		return s;
	}
}
