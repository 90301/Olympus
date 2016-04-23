
/**
 * The class that holds a piece of land.
 * Various actions and buildings can be placed on this piece of land
 * 
 * @author inhaler
 *
 */
public class Land implements simulateable {
	
	private String id;
	private String ownerId;
	public static final String NO_OWNER = "No Owner";
	private int assessedValue;
	public static final int NO_ASSESSMENT = -1;
	private Building building;
	
	
	
	
	public Land() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * This sets the value of the Land (includes the building)
	 */
	public void assess() {
		assessedValue = 100;
	}
	
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}
	@Override
	public void generate() {
		this.id = NodeMain.genID();
		this.setOwnerId(NO_OWNER);
		
	}
	@Override
	public void simulateStep() {
		// TODO Auto-generated method stub
		
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public int getAssessedValue() {
		return assessedValue;
	}
	public void setAssessedValue(int assessedValue) {
		this.assessedValue = assessedValue;
	}
	

}
