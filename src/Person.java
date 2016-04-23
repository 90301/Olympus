/**
 * The class that holds all the data re
 * @author inhaler
 *
 */
public class Person implements simulateable {
	
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
		
	}

	public void generatePerson() {
		this.id = NodeMain.genID();
		this.name = "Person: " + this.id;
		this.age = (int) (Math.random()*100);
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}
	
	
	
}
