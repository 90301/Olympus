/**
 * The class that holds all the data re
 * @author inhaler
 *
 */
public class Person implements simulateable {
	
	private static final double MAX_BASE_MONEY = 100;
	private static final double MAX_BASE_AGE = 100;
	private static final int MAX_AGE = 1000;
	private String name;
	private String id;
	private int money;
	private int age;
	/*
	 * TODO: add inventory system
	 */
	public Person() {
		
		// TODO Auto-generated constructor stub
	}
	
	
	public void simulateStep() {
		this.age ++;
		//simulate the person here
		
		if (age > MAX_AGE) {
			//die
		}
	}
	/**
	 * This method generates people at the start of the simulation
	 * Another method should be used for generating a person during
	 * the simulation.
	 */
	public void generatePerson() {
		this.id = NodeMain.genID();
		this.name = "Person: " + this.id;
		this.age = (int) (Math.random()*MAX_BASE_AGE);
		this.money = (int) (Math.random()*MAX_BASE_MONEY);
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}


	@Override
	public void generate() {
		generatePerson();
		
	}
	
	
	
}
