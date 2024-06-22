package _04_animals_inheritance;

public class Dog extends Things{

	public Dog(String string, String string2, boolean b) {
		// TODO Auto-generated constructor stub
		super(string,string2, b);
		
	}
	@Override
	public void play() {
		// TODO Auto-generated method stub
		System.out.println(name+ " plays");
	}
}
