package edu.ncsu.csc216.wolf_hire.model.manager;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc216.wolf_hire.model.application.Application;
import edu.ncsu.csc216.wolf_hire.model.command.Command;
import edu.ncsu.csc216.wolf_hire.model.io.PositionReader;
import edu.ncsu.csc216.wolf_hire.model.io.PositionWriter;

/**
 * The WolfHire Class maintains the list of positions, active position, and handles all GUI commands
 * @author John Eakes
 */
public class WolfHire {
	/** This variable declares the singleton for the class */
	private static WolfHire singleton;
	/** This creates an array list of positions */
	private ArrayList<Position> positions;
	/** This creates the current active position */
	private Position activePosition;
	
	
	/**
	 * This is the private constructor for the WolfHire Class
	 */
	private WolfHire() {
		positions = new ArrayList<>();
		activePosition = null;
	}
	
	/**
	 * This method gets the instance of an object
	 * @return a WolfHire object
	 */
	public static synchronized WolfHire getInstance() {
		if (singleton == null) {
			singleton = new WolfHire();
		}
		return singleton;
	}
	
	/**
	 * This methods loads a list of positions from a given file
	 * @param fileName the file being loaded from
	 */
	public void loadPositionsFromFile(String fileName) {
		positions = PositionReader.readPositionFile(fileName);
		if(!positions.isEmpty()) {
			activePosition = positions.get(0);
			activePosition.setApplicationId();
		}
	}
	
	/**
	 * This method saves a list of positions to a given file
	 * @param fileName the name of the file being saved to
	 */
	public void savePositionsToFile(String fileName) {
		if(activePosition == null) {
			throw new IllegalArgumentException("Unable to save file.");
		}
		PositionWriter.writePositionsToFile(fileName, positions);
	}
	
	/**
	 * This method sets the activePosition
	 * @param position the position being set to active
	 */
	public void setActivePosition(Position position) {
		this.activePosition = position;
	}
	
	/**
	 * This method adds a new position to the general program
	 * @param positionName the name of the position
	 * @param hoursPerWeek the number of hours that will be worked
	 * @param payrate the rate of pay of the position
	 */
	public void addNewPosition(String positionName, int hoursPerWeek, int payrate) {
		if(positionName == null || "".equals(positionName)) {
			throw new IllegalArgumentException("Position cannot be created.");
		}
		for(Position position : positions) {
			if(position.getPositionName().equalsIgnoreCase(positionName)) {
				throw new IllegalArgumentException("Position cannot be created.");
			}
		}
		
		Position position = new Position(positionName, hoursPerWeek, payrate);
		positions.add(position);
		loadPosition(positionName);
	}
	
	/**
	 * This method loads a position into the GUI from the given name
	 * @param positionName the name of the position being loaded
	 */
	public void loadPosition(String positionName) {
		for(Position position : positions) {
			if(position.getPositionName().equalsIgnoreCase(positionName)) {
				activePosition = position;
				position.setApplicationId();
				return;
			}
		}
		throw new IllegalArgumentException("Position not available.");
	}
	
	/**
	 * This method gets the name of the "active" position
	 * @return the name of the active position
	 */
	public String getActivePositionName() {
		if(activePosition == null) {
			return null;
		}
		return activePosition.getPositionName();
	}
	
	/**
	 * Returns the current active position
	 * @return the active position
	 */
	public Position getActivePosition() {
		return activePosition;
	}
	
	/**
	 * This method gets the list of all available positions
	 * @return the array list of positions
	 */
	public String[] getPositionList() {
		String[] names = new String[positions.size()];
		for(int i = 0; i < names.length; i++) {
			names[i] = positions.get(i).getPositionName();
		}
		return names;
	}
	
	/**
	 * This method adds an application to a specific position to be processed
	 * @param firstName the applicant's first name
	 * @param surname the applicant's last name
	 * @param unityId the applicant's unity ID
	 */
	public void addApplicationToPosition(String firstName, String surname, String unityId) {
		try {
			activePosition.addApplication(new Application(firstName, surname, unityId));
		} catch(Exception e) {
			//Do nothing for now
		}
	}
	
	/**
	 * This method changed a specific position's state
	 * @param id the id of the position being changed
	 * @param c the state that it's being changed to
	 */
	public void executeCommand(int id, Command c) {
		
		
		try {
			Application application = activePosition.getApplicationById(id);
			
			if(application == null) {
				throw new IllegalArgumentException("Application ID could not be found");
			}
			application.update(c);
		} catch(NullPointerException e) {
			//Do nothing
		}
	}
	
	/**
	 * This method removes an application from the list of applications for a specific position
	 * @param id the id the application that's being removed
	 */
	public void deleteApplicationById(int id) {
		try {	
			for(Application application : activePosition.getApplications()) {
				if(application.getId() == id) {
					activePosition.deleteApplicationById(id);
				}
			}
		} catch(Exception e) {
			//Do nothing
		}
		
	}
	
	/**
	 * This method returns a 2d array list of applications for a given state
	 * @param stateName the state that we're looking for
	 * @return a 2d array list of the applications for that given state
	 */
	public String[][] getApplicationsAsArray(String stateName) {
		if(activePosition == null) {
			return null;
		}
		
		List<Application> applications = new ArrayList<>();
		for(Application application : activePosition.getApplications()) {
			if("All".equalsIgnoreCase(stateName) || application.getState().equalsIgnoreCase(stateName)) {
				applications.add(application);
			}
		}
		
		String[][] applicationsArray = new String[applications.size()][4];
		for(int i = 0; i < applications.size(); i++) {
			Application app = applications.get(i);
			applicationsArray[i][0] = String.valueOf(app.getId());
			applicationsArray[i][1] = app.getState();
			applicationsArray[i][2] = app.getUnityId();
			applicationsArray[i][3] = (app.getReviewer() != null) ? app.getReviewer() : "";
		}
		return applicationsArray;
	}
	
	/**
	 * This method finds an application in the list by the unique identification number of the application
	 * @param id the unique identification number of the application
	 * @return the Application that fits the unique identification number
	 */
	public Application getApplicationById(int id) {
		if(activePosition == null) {
			return null;
		}
		for(Application application : activePosition.getApplications()) {
			if(application.getId() == id) {
				return application;
			}
		}
		return null;
	}
	
	/**
	 * This method resets the WolfHire manager
	 */
	protected void resetManager() {
		WolfHire.singleton = null;
	}
}
