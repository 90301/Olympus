import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * This class is run on a node in the simulation
 * It is intended to have methods to start the simulation,
 * and hold top level datastructures for that node.
 * @author inhaler
 *
 */
public class NodeMain {
	//Simulation variables and parameters
	public static String simulationState;
	public static final String SIMULATION_RUNNING = "Running";
	public static final String SIMULATION_PAUSED = "Paused";
	public static final String SIMULATION_ENDING = "Ending";
	private static final int TOP_LEVEL_STRUCTURES = 100;
	
	//Top level datastructures
	
	public static ConcurrentHashMap<String, City> cities = new ConcurrentHashMap<String,City>();
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		startSimulation();
		generate();
		mainSimulation();

	}
	/**
	 * Looping code that keeps running until the program is
	 * done running.
	 */
	public static void mainSimulation() {
		 while (simulationState != SIMULATION_ENDING) {
			if (simulationState==SIMULATION_RUNNING) {
			 //main similation code
				
				
				
				
				
			} else if (simulationState==SIMULATION_PAUSED) {
				//simulation "paused" code
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			 
		 }
	}
	
	//Generation code
	public static void generate() {
		for (int i=0;i<TOP_LEVEL_STRUCTURES;i++) {
			City c = new City();
			c.generateCity(100);
			cities.put(c.getId(), c);
		}
	}
	
	private static long nextId = 0;
	public static String genID() {
		System.out.println(nextId);
		return ""+nextId++;
	}
	
	/**
	 * This starts the simulation.
	 * TODO:
	 * Make this work with remote method invocation
	 * to start simulations on multiple nodes.
	 */
	public static void startSimulation() {
		simulationState = SIMULATION_RUNNING;
	}
	/**
	 * 
	 */
	public static void pauseSimulation() {
		simulationState = SIMULATION_PAUSED;
	}

}
