package design_pattern;

public class Dog extends Animal{
	public void digHole(){
		System.out.println("Dig a hole");
	}
	public Dog(){
		super();
		setSound("Bark");
		flyingType = new CantFly();
	}
	public void changeVar(int randNum) {
		randNum = 12;
		System.out.println("in method randNum: "+ randNum);
	}
}
