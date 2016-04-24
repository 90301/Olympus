package goods;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import topLevel.NodeMain;

/**
 * Holds a food object, which is generated by a farm or some sort of other
 * production.
 * @author inhaler
 *
 */
public class Food implements good {
	
	//the id for the food, or BASE_FOOD_ID if a base food.
	private String id;
	public static final String BASE_FOOD_ID = "Base Food"; 
	public static final int EXPIRED_FOOD_VALUE = -10;
	//the name of the food
	private String foodName;
	/*static list of all base foods. this can be used in food generation/creation
	 * when a new food is created, it must be added to this list
	*/
	public static ConcurrentMap<String,Food> baseFoods = new ConcurrentHashMap<String,Food>();
	
	/*
	 * Food stats
	 */
	private int nutritionValue;
	private int tasteValue;
	private int timeLeft;
	private int volume;
	private int mass;
	
	public static void createBaseFood(Food f) {
		baseFoods.putIfAbsent(f.foodName, f);
	}
	
	public static void setupInitalFoods() {
		String[] names = new String[11];
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
			f.id = BASE_FOOD_ID;
			createBaseFood(f);
		}
		
	}
	
	
	
	public Food() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getMass() {
		// TODO Auto-generated method stub
		return mass;
	}

	@Override
	public int getVolume() {
		// TODO Auto-generated method stub
		return volume;
	}

	@Override
	public int assessValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void simulateStep() {
		this.timeLeft--;
		
		if (timeLeft <0) {
			this.tasteValue = EXPIRED_FOOD_VALUE;
			this.nutritionValue = EXPIRED_FOOD_VALUE;
		}
		
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

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public int getNutritionValue() {
		return nutritionValue;
	}

	public void setNutritionValue(int nutritionValue) {
		this.nutritionValue = nutritionValue;
	}

	public int getTasteValue() {
		return tasteValue;
	}

	public void setTasteValue(int tasteValue) {
		this.tasteValue = tasteValue;
	}

	@Override
	public void setGoodStats(int volume, int mass) {
		this.volume = volume;
		this.mass = mass;
	}

	public int getTimeLeft() {
		return timeLeft;
	}

	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}
	
	public String toString() {
		return "Food: " + this.foodName +" Nurtition: " + nutritionValue;
	}

}
