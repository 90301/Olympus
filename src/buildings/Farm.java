package buildings;

import java.util.ArrayList;

import topLevel.Person;

/**
 * The farm, this is a "building" that produces food
 * given labor and time.
 * 
 * There are various levels of farm which generally provides more
 * food for less labor. Higher level farms may be able to produce
 * more exotic food, and will have a much higher average quality of food.
 * @author inhaler
 *
 */
public class Farm implements SubBuilding {

	public Farm() {
	}
	//data structures
	private int plots = 20;//this depends on the size of the farm, indicates how many crops can be grown concurrently.
	private ArrayList<Crop> crops = new ArrayList<Crop>();
	
	
	
	/**
	 * Grow crops
	 */
	@Override
	public void simulateStep() {
		
		
	}

	@Override
	public void build() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int assess() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void demolish() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void generate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean hire(Person hirePerson) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Boolean work(Person worker) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int collectPay(Person worker) {
		// TODO Auto-generated method stub
		return 0;
	}

}
