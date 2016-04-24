package buildings;
import topLevel.*;
/**
 * This is the interface for sub-buildings, which functions
 * as the subclass for whatever building is on a particular piece of land.
 * This gives the basic functions such as sim step.
 * @author inhaler
 *
 */
public interface SubBuilding extends simulateable {
	
	/**
	 * Builds the sub building, this initializes the basic information
	 * for the building.
	 */
	public void build();
	/**
	 * determines the value of the
	 * building
	 * @return the assessed value of the sub building.
	 */
	public int assess();
	/**
	 * This function is used for hiring people
	 * @param hirePerson the person that will work the building.
	 * @return if the building can be worked by more people
	 */
	public Boolean hire(Person hirePerson);
	/**
	 * called by the person who is supposed to work
	 * at a particular sub building.
	 * @param worker the worker (aka this)
	 * @return if the worker was successful (aka still employed)
	 */
	public Boolean work(Person worker);
	/**
	 * Collect all pay for the worker for all work done.
	 * @param worker the worker to collect pay (aka this)
	 * @return the money collected.
	 */
	public int collectPay(Person worker);
	
	
	public void demolish();

}
