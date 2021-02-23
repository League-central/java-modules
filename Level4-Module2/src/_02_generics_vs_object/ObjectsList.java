package _02_generics_vs_object;

// Here we have a custom list implemented using the Object object
// This allows us to create a list of any object(String, Integer, Double, etc)
public class ObjectsList {
	private Object[] list;
	
	public ObjectsList() {
		list = new Object[0];
	}
	
	public Object get(int loc) throws IndexOutOfBoundsException {
		if(loc < 0 || loc > list.length) throw new IndexOutOfBoundsException();
		return list[loc];
	}
	
	public void add(Object val) {
		Object[] oList = new Object[list.length + 1];
		for(int i = 0; i < list.length; i++) {
			oList[i] = list[i];
		}
		oList[list.length] = val;
		list = oList;
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
