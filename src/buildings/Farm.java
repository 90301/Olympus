package buildings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import topLevel.Inventory;
import topLevel.Person;

/**
 * The farm, this is a "building" that produces food
 * given labor and time.
 * 
 * There are various levels of farm which generally provides more
 * food for less labor. Higher level farms may be able to produce
 * more exotic food, and will have a much higher average quality of food.
 * @author inhaler
 *
 */
public class Farm implements SubBuilding {

	public Farm() {
		
	}
	//data structures
	private int plots = 20;//this depends on the size of the farm, indicates how many crops can be grown concurrently.
	private ConcurrentHashMap<String,Crop> crops = new ConcurrentHashMap<String,Crop>();
	private Person owner;
	//This is just a shortcut for accessing the owner of the building
	//Later incarnations may have it so the owner of the sub building may not be the owner
	//of the land, so rent would be imposed.
	
	//land can be accessed through the building.
	private Building building;
	/*
	 * TODO: Add banking system to extract payments from accounts
	 */
	private int funds;
	/**
	 * This holds all the workers for this farm. the key field is the id
	 * of the worker, the Person field holds the worker. (this data structure may change
	 * to a set of ids when global lookups are implemented.)
	 * 
	 * TODO: This may not be needed anymore
	 */
	private ConcurrentHashMap<String, Person> workers = new ConcurrentHashMap<String, Person>();
	/**
	 * This data structure holds all the special title workers for a particular building.
	 * It is possible for a special worker to not be a worker, and rather be an owner.
	 * A single worker can have multiple special roles. the first id is the role, the 2nd id is the
	 * Persons id.
	 */
	private HashMap<String, String> specialWorkers = new HashMap<String,String>();

	
	private boolean OwnerManaging;//set to false if someone else manages the farm
	
	private int basePayRate = 10;//TODO: make this into an "employment" class which details information
	//about how an employee will be paid, and other information
	//put that information into a map.
	private ConcurrentHashMap<String,EmploymentInfo> employmentInfo = new ConcurrentHashMap<String,EmploymentInfo>();
	private ConcurrentHashMap<String, JobListing> jobListings = new ConcurrentHashMap<>();
	private Inventory inventory = new Inventory();
	
	//WORK CODES for FARM
	public static final int MANAGE_WORKERS = 0;
	public static final int PLANT_CROPS = 1;
	public static final int HARVEST_CROPS = 2;
	public static final int TEND_CROPS = 3;
	public static final int IDLE = 4;//if action can not be completed anymore, wait for new orders.
	public static final int SELL_CROPS = 5;
	private static final int DEDICATED_MANAGEMENT_START = 10;//The point at which a dedicated manager is required.
	/**
	 * Grow crops
	 */
	@Override
	public void simulateStep() {
		//grow crops
		//crops.stream().forEach(s -> s.simulateStep());
		for (Crop c : crops.values()) {
			if (c!=null)
				c.simulateStep();
		}
		//System.out.println("Simulating farm: " + this);
		
		
	}
	
	public void plantCrop(Crop baseCrop) {
		if (crops.size()<plots) {
		Crop c = new Crop();
		c.setCropType(baseCrop.getCropType());
		c.generate();
		crops.put(c.getId(), c);
		}
	}

	@Override
	public void build() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int assess() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void demolish() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void generate() {
		funds = 500;//STARTING CAPITAL
		OwnerManaging = true;//this may need to be changed
		inventory.generate();
		
	}
	

	
	@Override
	public Boolean hire(Person hirePerson) {
		workers.put(hirePerson.getId(), hirePerson);
		
		EmploymentInfo eInfo = new EmploymentInfo();
		eInfo.setBuilding(this);
		eInfo.setComissionTotal(0);
		eInfo.setPerson(hirePerson);
		eInfo.setPayPerWorkCycle(basePayRate);
		eInfo.setPayOwed(0);
		eInfo.setWorkCode(IDLE);
		employmentInfo.put(hirePerson.getId(), eInfo);
		return true;
	}
	@Override
	public void hire(EmploymentInfo eInfo) {
		employmentInfo.put(eInfo.getPerson().getId(), eInfo);
		
	}

	@Override
	public Boolean work(Person worker) {
		if (worker==owner) {
			//owner working
		} else {
			//paid employee
		}
		EmploymentInfo eInfo = employmentInfo.get(worker.getId());
		
		if (eInfo.getWorkCode()==MANAGE_WORKERS) {
			managementAI(worker);
		} else if(eInfo.getWorkCode()==IDLE) {
			idleAI(worker);
		} else if(eInfo.getWorkCode()==PLANT_CROPS) {
			plantCropsAI(worker);
		} else if (eInfo.getWorkCode()==HARVEST_CROPS) {
			harvestCropsAI(worker);
		}
		
		
		return true;
	}
	private void harvestCropsAI(Person worker) {
		//Find the next harvestable plot
		Crop harvestableCrop = null;
		for (Crop c : crops.values()) {
			if (c.isHarvestable()) {
				harvestableCrop = c;
			}
		}
		if (harvestableCrop!=null) {
			inventory.addGoods(harvestableCrop.harvest());
		} else {
			employmentInfo.get(worker.getId()).setWorkCode(IDLE);
		}
	}
	private void plantCropsAI(Person worker) {
		if (crops.size()<plots) {
			//plant crops
			Crop cropToPlant = new Crop();
			String baseCropType = "";
			//pick the crop
			for(Crop baseCrop : Crop.baseCrops.values()) {
				//TODO: add AI here to decide which crop to plant.
				baseCropType = baseCrop.getCropType();
			}
			cropToPlant.setCropType(baseCropType);
			cropToPlant.setFarm(this);
			crops.put(cropToPlant.getId(), cropToPlant);
			System.out.println("Planted crop: " + cropToPlant);
		} else {
			//no available plots, idle or go to management
			employmentInfo.get(worker.getId()).setWorkCode(IDLE);
		}
		
	}

	private void managementAI(Person worker) {
		//DETERMINE NEED
		
		//TODO: improve this to have real AI (with personality traits)
		//survey employment
		Map<Integer,Integer> employment = surveyEmployment();
		//For right now, management should not exceed 1.
		
		//survey plots and crops
		int harvestReq = 0;
		for (Crop crop : crops.values()) {
			if (crop.isHarvestable()) {
				harvestReq++;
			}
		}
		if (employment.get(HARVEST_CROPS) != null) { 
			harvestReq -= employment.get(HARVEST_CROPS);
		}
		int plantReq = 0;
		if (crops.size() < plots) {
			plantReq = plots-crops.size();
		}
		if (employment.get(PLANT_CROPS) != null) {
		plantReq -= employment.get(PLANT_CROPS);
		}
		//TODO: add and check inventory for sales.
		
		
		for (EmploymentInfo e : employmentInfo.values()) {
			//if idle or a manager of a small business.
			if (e.getWorkCode()==IDLE || 
					(e.getWorkCode()==MANAGE_WORKERS && employmentInfo.size()<DEDICATED_MANAGEMENT_START)) {
				//put the employee to work if idle
				if (harvestReq>0) {
					harvestReq--;
					e.setWorkCode(HARVEST_CROPS);
				}
				if (plantReq>0) {
					e.setWorkCode(PLANT_CROPS);
					plantReq--;
				}
			}
		}
		int desiredEmployees = 4;
		//Hire additional people
		if (getTotalEmployees() < desiredEmployees) {
			listJob();
		}
	}
	private int getTotalEmployees() {
		return jobListings.size() + employmentInfo.size();
	}
	private void idleAI(Person worker) {
		//Switch to manager if there are no managers.
		Map<Integer,Integer> employment = surveyEmployment();
		if (employment.get(MANAGE_WORKERS)==null) {
			employmentInfo.get(worker.getId()).setWorkCode(MANAGE_WORKERS);
		}
	}
	/**
	 * survey what people are working.
	 * Returns a map of the number of current positions
	 * key = work code
	 * value = number of workers.
	 * @return a map containing the number of workers for each work code.
	 */
	public Map<Integer,Integer> surveyEmployment() {
		HashMap<Integer,Integer> employmentSurvey = new HashMap<Integer,Integer>();
		for (EmploymentInfo e : employmentInfo.values()) {
			//This holds how many workers have the same work code
			Integer numWorkers = employmentSurvey.get(e.getWorkCode());
			if (numWorkers==null) {
				numWorkers = 1;
			} else {
				numWorkers++;
			}
			
			employmentSurvey.put(e.getWorkCode(), numWorkers);
		}
		
		return employmentSurvey;
	}
	@Override
	public int collectPay(Person worker) {
		return employmentInfo.get(worker).collectPay();
	}

	public int getPlots() {
		return plots;
	}

	public void setPlots(int plots) {
		this.plots = plots;
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
		this.hire(owner);
		this.employmentInfo.get(owner.getId()).setWorkCode(MANAGE_WORKERS);
	}

	public int getFunds() {
		return funds;
	}

	public void setFunds(int funds) {
		this.funds = funds;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}
	public boolean isOwnerManaging() {
		return OwnerManaging;
	}

	public void setOwnerManaging(boolean ownerManaging) {
		OwnerManaging = ownerManaging;
	}

	public void destroyCrop(Crop crop) {
		this.crops.remove(crop);
		
	}
	

	@Override
	public void listJob() {
		EmploymentInfo eInfo = new EmploymentInfo();
		eInfo.setBuilding(this);
		eInfo.setComissionTotal(0);
		eInfo.setPerson(null);
		eInfo.setPayPerWorkCycle(basePayRate);
		eInfo.setPayOwed(0);
		eInfo.setWorkCode(IDLE);
		JobListing jobListing = new JobListing(eInfo);
		this.jobListings.put(jobListing.getId(), jobListing);
		this.getBuilding().getLand().getCity().listJob(jobListing);
		System.out.println(jobListing);
		
	}


}
