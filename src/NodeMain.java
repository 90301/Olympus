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
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		startSimulation();

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
