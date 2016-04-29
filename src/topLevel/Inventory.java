package topLevel;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import goods.good;

/**
 * This class holds goods
 * and can facilitate the transfer of goods.
 * @author inhaler
 *
 */
public class Inventory implements simulateable {
	public Inventory() {
		// TODO Auto-generated constructor stub
	}
	public ConcurrentHashMap<String, good> inventory = new ConcurrentHashMap<String,good>();
	private String id;
	
	/**
	 * Adds a good to the inventory
	 * @param g the good to add
	 */
	public void addGood(good g) {
		if (inventory.get(g.getStaticId())!=null) {
			this.inventory.get(g.getStaticId()).addQuantity(g.getQuantity());
			
		} else {
		inventory.put(g.getStaticId(), g);
		}
	}
	
	/**
	 * Add a list of goods to the inventory
	 * @param goods
	 */
	public void addGoods(Collection<good> goods) {
		for (good g : goods) {
			addGood(g);
		}
	}
	
	public Collection<good> getGoodsCollection() {
		return inventory.values();
		
	}
	
	
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void generate() {
		this.id = NodeMain.genID();
		
	}

	@Override
	public void simulateStep() {
		inventory.values().forEach(i -> i.simulateStep());
		
	}

	public void transferGoods(Inventory fromInventory) {
		this.addGoods(inventory.values());
		fromInventory.inventory.clear();
	}

}
