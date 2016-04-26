package buildings;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import goods.Food;
import goods.good;
import topLevel.NodeMain;
import topLevel.simulateable;

/**
 * Class to hold growing crop data
 * @author inhaler
 *
 */
public class Crop implements simulateable {
	public static final String BASE_CROP_ID = "base crop";
	/**
	 * Holds all invented crops.
	 */
	public static ConcurrentHashMap<String,Crop> baseCrops = new ConcurrentHashMap<String,Crop>();
	String id;
	private String cropType;//refers to base crop id.
	
	private int growTimeLeft;//the amount of time left for a crop to grow.
	private int cropDeathTime;//the time (<0) where the crop dies, yielding nothing.
	private Boolean destoryOnHarvest;//determines if the crop is destoryed on harvest or not
	private int cropRegrowTime;//time to regrow a crop after harvesting, not applicable to destory on harvest plants.
	
	public void setId(String id) {
		this.id = id;
	}

	ArrayList<good> harvestableGoods = new ArrayList<good>();//what will be returned when the good finishes growing.
	
	/*
	 * Set at crop creation.
	 */
	
	
	public Crop() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<good> harvest() {
		if (!destoryOnHarvest) {
			growTimeLeft = cropRegrowTime;
		}
		System.out.println("Harvesting goods: " + harvestableGoods);
		return harvestableGoods;
	}
	
	public Boolean isHarvestable() {
		return (growTimeLeft<=0);
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void generate() {
		this.id = NodeMain.genID();
		
	}

	@Override
	public void simulateStep() {
		growTimeLeft--;
		if (growTimeLeft < cropDeathTime) {
			this.harvestableGoods.clear();
		}
	}

	public String getCropType() {
		return cropType;
	}
	/**
	 * Sets the crop type and duplicates the data from the
	 * base crop. Also calls generate (so there's no need to
	 * call that manually)
	 * TODO: For a randomization of the crop, call another method.
	 * @param cropType
	 */
	public void setCropType(String cropType) {
		this.cropType = cropType;
		
		if (this.id != BASE_CROP_ID) {
		
		Crop c = baseCrops.get(cropType);
		//set stats based on crop type.
		//TODO: add randomization
		this.cropDeathTime = c.cropDeathTime;
		this.growTimeLeft = c.growTimeLeft;
		//this.harvestableGoods = c.harvestableGoods;
		this.destoryOnHarvest = c.destoryOnHarvest;
		this.cropRegrowTime = c.cropRegrowTime;
		
		for (good g:c.harvestableGoods) {
			if (g.getGoodType()==good.GOOD_TYPE_FOOD) {
				//gen new food item
				Food genFood = new Food();
				Food f = (Food) g;
				genFood.generateFrom(f);
				this.addHarvestableGood(genFood);
				
			}
		}
		this.generate();
		}
		
	}

	public void addHarvestableGood(good g) {
		harvestableGoods.add(g);
	}
	public int getGrowTimeLeft() {
		return growTimeLeft;
	}

	public void setGrowTimeLeft(int growTimeLeft) {
		this.growTimeLeft = growTimeLeft;
	}

	public int getCropDeathTime() {
		return cropDeathTime;
	}

	public void setCropDeathTime(int cropDeathTime) {
		this.cropDeathTime = cropDeathTime;
	}

	public ArrayList<good> getHarvestableGoods() {
		return harvestableGoods;
	}

	public void setHarvestableGoods(ArrayList<good> harvestableGoods) {
		this.harvestableGoods = harvestableGoods;
	}

	public Boolean getDestoryOnHarvest() {
		return destoryOnHarvest;
	}

	public void setDestoryOnHarvest(Boolean destoryOnHarvest) {
		this.destoryOnHarvest = destoryOnHarvest;
	}

	public int getCropRegrowTime() {
		return cropRegrowTime;
	}

	public void setCropRegrowTime(int cropRegrowTime) {
		this.cropRegrowTime = cropRegrowTime;
	}

	public static void addBaseCrop(Crop c1) {
		baseCrops.put(c1.cropType, c1);
		
	}
	
	public String toString() {
		String rtrn = "Crop: " + this.cropType;
		rtrn += " id: " + this.id;
		rtrn += " Stats: " + " Grow Time Left: " + growTimeLeft + " Crop Death Time: " + getCropDeathTime();
		//regrow stats
		rtrn += " Destroy on Harvest: " + destoryOnHarvest;
		if (!destoryOnHarvest) {
			rtrn += " Regrow Time: " + cropRegrowTime;
		}
		rtrn += " Produces: ";
		for (good g : harvestableGoods) {
			rtrn += g.toString();
		}
		return rtrn;
	}
	

}
