package strategy_design_pattern;

public class Bird extends Animal{
	
	// The constructor initializes all objects
	
	public Bird(){
		
		super();
		
		setSound("Tweet");
		
		// set the Flys interface polymorphically
		// This sets the behavior as a non-flying Animal
		
		flyingType = new ItFlys();
		
	}
	
}