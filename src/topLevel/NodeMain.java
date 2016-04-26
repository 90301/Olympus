package topLevel;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import buildings.Land;
import goods.Food;

/**
 * This class is run on a node in the simulation It is intended to have methods
 * to start the simulation, and hold top level datastructures for that node.
 * 
 * @author inhaler
 *
 */
public class NodeMain {
	// Simulation variables and parameters
	public static String simulationState;
	public static final String SIMULATION_RUNNING = "Running";
	public static final String SIMULATION_PAUSED = "Paused";
	public static final String SIMULATION_ENDING = "Ending";
	public static final int NUM_CITIES = 1;
	public static final int LAND_PER_CITY = 1;
	public static final int PEOPLE_PER_CITY = 25;

	public static final boolean RANDOM_LAND_ASSIGNMENT = true;
	
	// Top level data structures

	public static ConcurrentHashMap<String, City> cities = new ConcurrentHashMap<String, City>();
	public static ConcurrentHashMap<String, Person> people = new ConcurrentHashMap<String, Person>();
	public static ConcurrentHashMap<String, Land> land = new ConcurrentHashMap<String, Land>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		startSimulation();
		generate();
		mainSimulation();

	}

	/**
	 * Looping code that keeps running until the program is done running.
	 */
	public static void mainSimulation() {
		while (simulationState != SIMULATION_ENDING) {
			if (simulationState == SIMULATION_RUNNING) {
				// main simulation code
				cities.values().parallelStream().forEach(s -> s.simulateStep());
			} else if (simulationState == SIMULATION_PAUSED) {
				// simulation "paused" code
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	// Generation code
	public static void generate() {
		InitalSetup.setupCities();
		//setup base foods
		InitalSetup.setupInitalFoods();
		InitalSetup.setupInitalCrops();
	}

	private static long nextId = 0;

	public static String genID() {
		return "" + nextId++;
	}

	/**
	 * This starts the simulation. TODO: Make this work with remote method
	 * invocation to start simulations on multiple nodes.
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
	
	/*
	 * NOTES:
	 * 
	 * Hiring mechanics
	 * each person can work at most 3 buildings at a time.
	 * the building has a hire function.
	 * before hiring, the building will broadcast a call for applications
	 * each application will test the person being hired in a randomized stats test
	 * where a multiplier between .5 and 2 is multiplied by the applicants stat in question.
	 * an application can test 1 to 3 stats, some stats on the application are fixed for that job
	 * others are randomized.
	 * 
	 * STATS:
	 * There are likely to be a number of stats/personality traits for people. here are the major categories and
	 * sub stats.
	 * 
	 * Social Stats:
	 * 	Communication - improves understanding, and therefore efficiency in tasks requiring coordination.
	 * 	Management - improves efficiency in management related tasks, which improves the efficiency of all other workers.
	 * 
	 * Intellectual Stats:
	 * 	Math - improves efficiency in tasks requiring math.
	 * 	Engineering - improves ability to come up with solutions or improve designs for improvements to tiles.
	 * 
	 * Blue Collar Stats: (specialized labor)
	 * 	Machine work - efficiency in using machines to create or refine products.
	 * 	Strength - ability to work using ones own body
	 * 	Dexterity - ability to perform intricate work
	 * 
	 * ----------
	 * in addition to stats (which change slowly) there are skills that can have big effect on certain jobs
	 * Skills:
	 * Supply Chain Efficiency - negotiates margins for suppliers/distributors for the business. 
	 * (competition between supply chain position for both business)
	 * 
	 * Salary Negotiation - improves Salary directly (competition between hiring manager and worker)
	 * 
	 * 
	 * ----------
	 * Motivation:
	 * Motivation serves as an overall multiplier for efficiency in working, as well
	 * as a general mood indicator.
	 * 
	 * motivation is a complex variable, that I have not decided how to implement yet
	 * so for right now, motivation will be at 1.0 always. motivation should be able to
	 * go from 0 to 2.0
	 * 
	 * 
	 */

}
