package goods;
import topLevel.*;
/**
 * The interface for an economic good, or
 * something that is able to be traded and has value.
 * @author inhaler
 *
 */
public interface good extends simulateable {
	
	public int getMass();
	
	public int getVolume();
	
	public void setGoodStats(int volume, int mass);
	
	public default String goodStatToString() {
		return " Mass: " +  getMass() + " Volume: " + getVolume();
	}
	
	public int assessValue();
	

}
