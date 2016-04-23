/**
 * This is the interface for sub-buildings, which functions
 * as the subclass for whatever building is on a particular piece of land.
 * This gives the basic functions such as sim step.
 * @author inhaler
 *
 */
public interface SubBuilding {
	public void simulateStep();
	public void build();
	public int assess();
	public void demolish();

}
