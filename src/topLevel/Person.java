package topLevel;
import java.util.ArrayList;
import java.util.HashSet;

import buildings.*;
/**
 * The class that holds all the data re
 * @author inhaler
 *
 */
public class Person implements simulateable {
	
	private static final double MAX_BASE_MONEY = 100;
	private static final double MAX_BASE_AGE = 100;
	private static final int MAX_AGE = 1000;
	private static final int MAX_JOBS = 3;
	private String name;
	private String id;
	private int money;
	private int age;


	private City city;//The city currently in.
	private HashSet<Land> landOwned = new HashSet<Land>();
	private HashSet<Building> buildingsOwned = new HashSet<Building>();
	private HashSet<EmploymentInfo> employmentInfos = new HashSet<EmploymentInfo>();
	/*
	 * TODO: add inventory system
	 */
	public Person() {
		
		// TODO Auto-generated constructor stub
	}
	
	
	public void simulateStep() {
		this.age ++;
		//simulate the person here
		//If the person has any land with buildings that have nothing on them
		for (Land l : landOwned) {
			Building building = l.getBuilding();
			if (building.getBuildingType()==Building.VACANT_LOT) {
				building.setBuildingType(Building.FARMLAND);
				//System.out.println(building);
			}
			building.getSubBuilding().work(this);
		}
		if (landOwned.size() <=0 && employmentInfos.size()<=0) {
			applyForJob();
		} else {
			//work
			for (EmploymentInfo eInfo : employmentInfos) {
				eInfo.getBuilding().work(this);
			}
		}
		if (age > MAX_AGE) {
			//die
		}
	}
	
	private void applyForJob() {
		//TODO: update this
		//apply for all avaliable jobs at the same time.
		for (JobListing j : this.city.getJobListings().values()) {
			if (!j.alreadyApplied(this)) {
				j.submitApplication(this);
			}
		}
		
	}
	/**
	 * This method generates people at the start of the simulation
	 * Another method should be used for generating a person during
	 * the simulation.
	 */
	public void generatePerson() {
		this.id = NodeMain.genID();
		this.name = "Person: " + this.id;
		this.age = (int) (Math.random()*MAX_BASE_AGE);
		this.money = (int) (Math.random()*MAX_BASE_MONEY);
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}


	@Override
	public void generate() {
		generatePerson();
		
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getMoney() {
		return money;
	}


	public void setMoney(int money) {
		this.money = money;
	}


	public City getCity() {
		return city;
	}


	public void setCity(City city) {
		this.city = city;
	}

	public void addOwnedLand(Land l) {
		landOwned.add(l);
	}
	public void removedOwnedLand(Land l) {
		landOwned.remove(l);
	}


	public Boolean offerJob(EmploymentInfo eInfo) {
		Boolean acceptJob = false;
		if (employmentInfos.size()<MAX_JOBS) {
			this.employmentInfos.add(eInfo);
			acceptJob = true;
		}
		return acceptJob;
	}

	
	
	
}
