import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class City implements simulateable {

	
	private String cityName;
	private Map<String,Person> people = new ConcurrentHashMap<String,Person>();
	private String id;
	public City() {
		
		// TODO Auto-generated constructor stub
	}
	
	public void generateCity(int initalPopulation) {
		this.id = NodeMain.genID();
		this.cityName = "City: " + this.id;
		for (int i=0;i<initalPopulation;i++) {
			Person p = new Person();
			p.generatePerson();
			people.put(p.getId(), p);
		}
	}
	
	/**
	 * The simulation step process for a city
	 */
	public void simulateStep() {
		people.values().parallelStream().forEach(s -> s.simulateStep());
	}
	
	
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

}
