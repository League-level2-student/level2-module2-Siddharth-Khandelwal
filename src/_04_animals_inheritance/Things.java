package _04_animals_inheritance;

public class Things {
	String name;
	String furColor;
	boolean isGirl;
	
	Things(String name, String furColor, boolean isGirl) {
		this.name=name;
		this.furColor=furColor;
		this.isGirl=isGirl;
	}
	public void printName() {
		System.out.println("My name is "+name);
	}
	
	public void eat() {
		System.out.println("Eating");
	}
	
	public void sleep() {
		System.out.println("Sleeping");
	}
	
	public void play() {
		System.out.println("Playing");
	}
}
