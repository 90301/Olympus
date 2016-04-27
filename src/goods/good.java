package goods;
import java.util.concurrent.ConcurrentHashMap;

import topLevel.*;
/**
 * The interface for an economic good, or
 * something that is able to be traded and has value.
 * @author inhaler
 *
 */
public interface good extends simulateable {
	//GOOD TYPE CONSTANTS
	public static int GOOD_TYPE_FOOD = 1;
		
		
		
	public static ConcurrentHashMap<String, good> allGoods = new ConcurrentHashMap<String, good>();
	
	public static void addBaseGood(good g) {
		allGoods.put(g.getStaticId(), g);
	}
	
	public default void generateFrom(good g) {
		//set stats to be the same
		//could be generating from a base good, or generating for a transfer.
		this.setGoodStats(g.getVolume(), g.getMass());
		this.setQuantity(g.getQuantity());
		this.generate();
	}
	
	public String getStaticId();
	
	public int getMass();
	
	public int getVolume();
	
	public void setGoodStats(int volume, int mass);
	
	public default String goodStatToString() {
		return " Mass: " +  getMass() + " Volume: " + getVolume();
	}
	
	public int assessValue();
	
	public int getGoodType();
	
	//Quantity
	public int getQuantity();
	/**
	 * adds an amount to the quantity of the good
	 * @param i 
	 * @return the current quantity of the good (after addition)
	 */
	public int addQuantity(int quantity);
	/**
	 * Removes a quantity of an item
	 * TODO: mark object for removal if quantity <=0
	 * @return the quantity remaining
	 */
	public int removeQuantity(int quantity);
	/**
	 * Sets the quantity variable
	 */
	public void setQuantity(int quantity);
	
	

}
