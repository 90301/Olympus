package visualizer;

import sv.gui2.Gui2;
import sv.gui2.drawables2.Triangle;

/**
 * The GUI for the simulation.
 * @author inhaler
 *
 */
public class Visualizer {

	int guiID;
	Gui2 gui;
	
	
	public Visualizer() {
		//create the gui
		guiID = sv.gui2.GuiManager.createGui("Olympus");
		gui = sv.gui2.GuiManager.getGui(guiID);
		
	}
	public void drawGUI() {
		/*
		Triangle triangle = new Triangle(50, 50, 20);
		gui.add(triangle);
		*/
		//draw all sub guis
		
	}
	

}
