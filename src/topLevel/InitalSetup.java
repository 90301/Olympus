package topLevel;

import buildings.Crop;
import goods.Food;

/**
 * This class is used do the inital setup for basically everything that's a linked good
 * So that code can be more direct.
 * @author Josh Benton
 *
 */
public class InitalSetup {
	public static String[] names = new String[11];
	public static void setupInitalFoods() {
		
		//base ingredients
		names[0] = "Apples";
		names[1] = "Wheat";
		names[2] = "Rice";
		names[3] = "Nuts";
		names[4] = "Berries";
		names[5] = "Vegtables";
		//T1 food
		names[6] = "Flour";
		//process wheat or rice
		names[7] = "Fruit Mix";
		names[8] = "Salad";
		//T2 food
		names[9] = "Cookies";
		names[10] = "Granola Bars";
		//basic food generation
		for (int i=0;i<11;i++) {
			Food f = new Food();
			f.setFoodName(names[i]);
			f.setNutritionValue(i);
			f.setTasteValue(15-i);
			f.setGoodStats(2, 1);
			f.setId(Food.BASE_FOOD_ID);
			Food.createBaseFood(f);
			System.out.println(f);
		}
	}
	/**
	 * TODO: make this read from an csv document
	 */
	public static void setupInitalCrops() {
		Crop c1 = new Crop();
		c1.setId(Crop.BASE_CROP_ID);
		c1.setCropType("Apple Tree");
		c1.setDestoryOnHarvest(false);
		c1.setCropDeathTime(-100);
		c1.setGrowTimeLeft(150);
		c1.setCropRegrowTime(15);
		Food f1 = Food.baseFoods.get(names[0]);
		c1.addHarvestableGood(f1);
		System.out.println(f1);
		Crop.addBaseCrop(c1);
		System.out.println(c1);
	}
}
