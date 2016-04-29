package topLevel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import buildings.JobListing;
import buildings.Land;

public class City implements simulateable {

	
	private static final int INITAL_POP = 100;
	private String cityName;
	/**
	 * The people currently in the city
	 */
	private Map<String,Person> people = new ConcurrentHashMap<String,Person>();
	private Map<String,Land> land = new ConcurrentHashMap<String,Land>();
	private Map<String,JobListing> jobListings = new ConcurrentHashMap<String,JobListing>();
	
	public Map<String, JobListing> getJobListings() {
		return jobListings;
	}

	private String id;
	public City() {
		
	}
	
	public void generateCity(int initalPopulation) {
		this.id = NodeMain.genID();
		this.setCityName("City: " + this.id);
		//create people
		for (int i=0;i<initalPopulation;i++) {
			Person p = new Person();
			p.generatePerson();
			p.setCity(this);
			people.put(p.getId(), p);
			NodeMain.people.put(p.getId(), p);
		}
		//create land
		for (int i=0;i<NodeMain.LAND_PER_CITY;i++) {
			Land l = new Land();
			l.generate();
			l.setCity(this);
			land.put(l.getId(), l);
			NodeMain.land.put(l.getId(), l);
			//System.out.println(l);
		}
		//RANDOM LAND ASSIGNMENT
		if (NodeMain.RANDOM_LAND_ASSIGNMENT) {
			Random r = new Random(555);
			ArrayList<String> peopleIds = new ArrayList<String>();
			peopleIds.addAll(people.keySet());
			for (Land l : land.values()) {
			
			String randomKey = peopleIds.get(r.nextInt(peopleIds.size()));
			Person p = people.get(randomKey);
			l.setOwnerId(randomKey);
			p.addOwnedLand(l);
			//System.out.println("Person: " + p + " Owns: " + l);
			
			
			}
		}
		
	}
	
	/**
	 * The simulation step process for a city
	 */
	public void simulateStep() {
		people.values().parallelStream().forEach(s -> s.simulateStep());
		land.values().parallelStream().forEach(l -> l.simulateStep());
		jobListings.values().parallelStream().forEach(j -> j.simulateStep());
	}
	
	
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void generate() {
		generateCity(INITAL_POP);
		
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public void listJob(JobListing jobListing) {
		this.jobListings.put(jobListing.getId(), jobListing);
		
	}

}
