package edu.ncsu.csc216.wolf_hire.model.manager;
import edu.ncsu.csc216.wolf_hire.model.application.Application;
import edu.ncsu.csc216.wolf_hire.model.command.Command;

import java.util.ArrayList;
import java.util.List;
/**
 * The Position Class represents one specific position. It maintains its name, 
 * and the list of applications for the one specific position.
 * @author John Eakes
 */
public class Position {
	/** This is the variable for the position's name */
	private String positionName;
	
	/** This is the variable name for the number of hours per week for a position */
	private int hoursPerWeek;
	
	/** This is the rate of pay for a position */
	private int payRate;
	
	/** This is the list of applications for a position */
	private List<Application> applications;
	
	/**
	 * This is the constructor for the Position Class. It takes in the three position 
	 * variables and assigns them values.
	 * @param positionName the name of a specific position
	 * @param hoursPerWeek the number of hours of a specific position
	 * @param payRate the rate of pay of a specific position
	 * @throws IllegalArgumentException if positionName is null or empty, hours are less than 5 or more than 20,
	 * 									or payRate is less than 7 or greater than 35
	 */
	public Position(String positionName, int hoursPerWeek, int payRate) {
		if(positionName == null || "".equals(positionName) ||
		   hoursPerWeek < 5 || hoursPerWeek > 20 ||
		   payRate < 7 || payRate > 35) {
			throw new IllegalArgumentException("Position cannot be created.");
		}
		
		setPositionName(positionName);
		setHoursPerWeek(hoursPerWeek);
		setPayRate(payRate);
		this.applications = new ArrayList<>();
	}
	
	/**
	 * This method sets the applicationId, which is a unique identifier for every application in a position
	 */
	public void setApplicationId() {
		int maxId = 0;
		for(int i = 0; i < applications.size(); i++) {
			if(applications.get(i).getId() > maxId) {
				maxId = applications.get(i).getId();
			}
		}
		Application.setCounter(maxId + 1);
	}
	
	/**
	 * This method sets the position name to a chosen name
	 * @param positionName the name of the specific position
	 */
	private void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	
	/**
	 * This method gets the position name
	 * @return a string of the position's name
	 */
	public String getPositionName() {
		return positionName;
	}
	
	/**
	 * This method sets the number of hours per week for a position
	 * @param hoursPerWeek the number of hours worked for a specific position 
	 */
	private void setHoursPerWeek (int hoursPerWeek) {
		this.hoursPerWeek = hoursPerWeek;
	}
	
	/**
	 * This method returns the number of hours per week for a position
	 * @return an integer representing how many hours
	 */
	public int getHoursPerWeek() {
		return hoursPerWeek;
	}
	
	/**
	 * This method sets the rate of pay for a position
	 * @param payRate an integer representing the hourly pay rate
	 */
	private void setPayRate(int payRate) {
		this.payRate = payRate;
	}
	
	/**
	 * This method gets the rate of pay for a position
	 * @return an integer representing the rate of pay
	 */
	public int getPayRate() {
		return payRate;
	}
	
	/**
	 * This method adds an application to the specific position
	 * @param firstName the applicant's first name
	 * @param surname the applicant's last name
	 * @param unityId the applicant's unity ID
	 * @return an integer representing the unique identification number of the application
	 */
	public int addApplication(String firstName, String surname, String unityId) {
		Application newApplication = new Application(firstName, surname, unityId);
	
		int i = 0;
		while (i < applications.size() && applications.get(i).getId() < newApplication.getId()) {
			i++;
		}
		applications.add(i, newApplication);
		return newApplication.getId();
	}
	
	/**
	 * This method adds an application to the specific position 
	 * @param application the already-made application that's being added
	 * @return an integer representing the unique identification number of the application
	 * @throws IllegalArgumentException if application with the same id already exists
	 */
public int addApplication(Application application) {
		for(int i = 0; i < applications.size(); i++) {
			if(applications.get(i).getId() == application.getId()) {
				throw new IllegalArgumentException("Application cannot be created.");
			}
		}
		
		int i = 0;
		while (i < applications.size() && applications.get(i).getId() < application.getId()) {
			i++;
		}
		applications.add(i, application);
		return application.getId();

	}
	
	/**
	 * This method finds an application in the list by the unique identification number of the application
	 * @param id the unique identification number of the application
	 * @return the Application that fits the unique identification number
	 */
	public Application getApplicationById(int id) {
		for(Application app : applications) {
			if(app.getId() == id) {
				return app;
			}
		}
		return null;
	}
	
	/**
	 * Gets a list of all applications of a position
	 * @return a list of applications
	 */
	public List<Application> getApplications() {		
		return this.applications;
	}
	
	/**
	 * This method "executes a command", meaning that it changed the state of an application
	 * @param id the id of the application that's having its state changed
	 * @param c the soon-to-be state of the application
	 */
	public void executeCommand(int id, Command c) {
		for(Application app : applications) {
			if(app.getId() == id) {
				app.update(c);
				return;
			}
		}
	}
	
	/**
	 * This method removes an application from the list of applications for a specific position
	 * @param id the id the application that's being removed
	 */
	public void deleteApplicationById(int id) {
		Application toRemove = null;
		for(Application app : applications) {
			if(app.getId() == id) {
				toRemove = app;
				break;
			}
		}
		
		if(toRemove != null) {
			applications.remove(toRemove);
		}
	}
	
	/**
	 * This method returns a position in a string format
	 * @return a string format of the position
	 */
	public String toString() {
		String s = "";
		s += getPositionName() + "," + getHoursPerWeek() + "," + getPayRate();
		return s;
	}
}
