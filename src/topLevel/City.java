package topLevel;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import buildings.Land;

public class City implements simulateable {

	
	private static final int INITAL_POP = 100;
	private String cityName;
	/**
	 * The people currently in the city
	 */
	private Map<String,Person> people = new ConcurrentHashMap<String,Person>();
	private Map<String,Land> land = new ConcurrentHashMap<String,Land>();
	
	
	private String id;
	public City() {
		
		// TODO Auto-generated constructor stub
	}
	
	public void generateCity(int initalPopulation) {
		this.id = NodeMain.genID();
		this.setCityName("City: " + this.id);
		for (int i=0;i<initalPopulation;i++) {
			Person p = new Person();
			p.generatePerson();
			people.put(p.getId(), p);
			NodeMain.people.put(p.getId(), p);
		}
		for (int i=0;i<NodeMain.LAND_PER_CITY;i++) {
			Land l = new Land();
			l.generate();
			land.put(l.getId(), l);
			NodeMain.land.put(l.getId(), l);
			System.out.println(l);
		}
		
		//generate land (city level?)
	}
	
	/**
	 * The simulation step process for a city
	 */
	public void simulateStep() {
		people.values().stream().forEach(s -> s.simulateStep());
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

}
