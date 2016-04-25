package buildings;

import java.util.ArrayList;
import java.util.HashMap;

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
	private Person owner;
	/**
	 * This holds all the workers for this farm. the key field is the id
	 * of the worker, the Person field holds the worker. (this data structure may change
	 * to a set of ids when global lookups are implemented.)
	 */
	private HashMap<String, Person> workers = new HashMap<String, Person>();
	/**
	 * This data structure holds all the special title workers for a particular building.
	 * It is possible for a special worker to not be a worker, and rather be an owner.
	 * A single worker can have multiple special roles. the first id is the role, the 2nd id is the
	 * Persons id.
	 */
	private HashMap<String, String> specialWorkers = new HashMap<String,String>();
	/**
	 * The pay owed to specific workers, key is worker id, value is pay owed.
	 * if nothing is owed, the value may be null.
	 */
	private HashMap<String, Integer> payOwed = new HashMap<String,Integer>();
	/**
	 * Grow crops
	 */
	@Override
	public void simulateStep() {
		crops.stream().forEach(s -> s.simulateStep());
		
	}
	
	public void plantCrop(Crop baseCrop) {
		if (crops.size()<plots) {
		Crop c = new Crop();
		c.setCropType(baseCrop.getCropType());
		c.generate();
		crops.add(c);
		}
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

	public int getPlots() {
		return plots;
	}

	public void setPlots(int plots) {
		this.plots = plots;
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

}
