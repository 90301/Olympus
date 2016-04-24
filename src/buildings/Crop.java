package buildings;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import goods.good;
import topLevel.NodeMain;
import topLevel.simulateable;

/**
 * Class to hold growing crop data
 * @author inhaler
 *
 */
public class Crop implements simulateable {
	/**
	 * Holds all invented crops.
	 */
	public static ConcurrentHashMap<String,Crop> baseCrops = new ConcurrentHashMap<String,Crop>();
	String id;
	private String cropType;//refers to base crop id.
	
	int growTimeLeft;//the amount of time left for a crop to grow.
	int cropDeathTime;//the time (<0) where the crop dies, yielding nothing.
	
	ArrayList<good> harvestableGoods;//what will be returned when the good finishes growing.
	
	/*
	 * Set at crop creation.
	 */
	
	
	public Crop() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<good> harvest() {
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

	public void setCropType(String cropType) {
		this.cropType = cropType;
		Crop c = baseCrops.get(cropType);
		//set stats based on crop type.
		//TODO: add randomization
		this.cropDeathTime = c.cropDeathTime;
		this.growTimeLeft = c.growTimeLeft;
		this.harvestableGoods = c.harvestableGoods;
		this.generate();
		
	}
	

}
