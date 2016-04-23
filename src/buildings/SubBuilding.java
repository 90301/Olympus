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
	public void demolish();

}
