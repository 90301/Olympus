/**
 * This is a class that holds buildings, which are placed on land.
 * The land itself can have an owner.
 * 
 * Most buildings will be owned by the land owner, with the exception
 * of sub buildings like skyscrapers.
 * 
 * @author inhaler
 *
 */
public class Building implements simulateable {
	
	String id;
	String buildingName;
	int buildingType = 0;
	
	//building constants
	public static final int VACANT_LOT = 0;
	public static final int FARMLAND = 1;
	public static final int HOME = 2;
	public static final int STORE = 3;
	public static final int SKYSCRAPER = 4;
	
	//building specific variables?
	private SubBuilding subBuilding;
	
	
	public Building() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void generate() {
		this.id = NodeMain.genID();
		this.buildingName = "building: " + this.id;
		this.buildingType = VACANT_LOT;

	}

	@Override
	public void simulateStep() {
		

	}

}
