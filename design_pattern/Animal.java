package design_pattern;

public class Animal {
	private String name;
	private int weight;
	private String sound;
	
	public Flys flyingType;

	public void setName(String newName){
		name = newName;
	}
	
	public String getName(){
		return name;
	}
	public void setWeight(int newWeight){
		if(newWeight > 0){
			weight = newWeight;
		}else{
			System.out.println("Weight must be bigger than 0");
		}
	}
	public int getWeight(){
		return weight;
	}
	
	public void setSound(String NewSound) {
		sound = NewSound;
	}
	public String getSound() {
		return sound;
	}

	// Animal pushes off the responsibility for flying to flyingType
	
	public String tryToFly(){
		
		return flyingType.fly();
		
	}
	
	// If you want to be able to change the flyingType dynamically
	// add the following method
	
	public void setFlyingAbility(Flys newFlyType){
		
		flyingType = newFlyType;
		
	}
}
