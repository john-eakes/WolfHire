package edu.ncsu.csc216.wolf_hire.model.application;


import edu.ncsu.csc216.wolf_hire.model.command.Command;

/**
 * The Application Class represents a student's application. 
 * This class holds all necessary Application information, including the application's state.
 * In the Application class are also: ApplicationState Interface, and all of its supporting concrete classes. 
 * Their functions will be defined in JavaDoc above their class definitions
 * @author John Eakes
 */
public class Application {
	/** This is the variable for the application's unique identification number */
	private int applicationId;
	
	/** This is the variable for the application's state */
	private ApplicationState state;
	
	/** This is the variable for the applicant's first name */
	private String firstName;
	
	/** This is the variable for the applicant's last name */
	private String surname;
	
	/** This is the variable for the applicant's unity ID */
	private String unityId;
	
	/** This is the variable for the applicant's reviewer */
	private String reviewer;
	
	/** This is the variable for the reviewer's notes for the applicant */
	private String note;
	
	/** This is a static variable for a counter */
	private static int counter = 1;
	
	/** This is a static final variable for the submitted state */
	public static final String SUBMITTED_NAME = "Submitted";
	
	/** This is a static final variable for rejected state */
	public static final String REJECTED_NAME = "Rejected";
	
	/** This is a static final variable for the reviewing state */
	public static final String REVIEWING_NAME = "Reviewing";
	
	/** This is a static final variable for the interviewing state */
	public static final String INTERVIEWING_NAME = "Interviewing";
	
	/** This is a static final variable for the processing state */
	public static final String PROCESSING_NAME = "Processing";
	
	/** This is a static final variable for the hired state */
	public static final String HIRED_NAME = "Hired";
	
	/** This is a static final variable for the inactive state */
	public static final String INACTIVE_NAME = "Inactive";
	
	/** This is a static final variable for a lack of qualifications rejection reasoning */
	public static final String QUALIFICATIONS_REJECTION = "Qualifications";
	
	/** This is a static final variable for an incomplete application rejection reasoning */
	public static final String INCOMPLETE_REJECTION = "Incomplete";
	
	/** This is a static final variable for a positions rejection reasoning */
	public static final String POSITIONS_REJECTION = "Positions";
	
	/** This is a static final variable for a duplicate rejection reasoning */
	public static final String DUPLICATE_REJECTION = "Duplicate";
	
	/** This is a static final variable for completed reasoning for the inactive state */
	public static final String COMPLETED_TERMINATION = "Completed";
	
	/** This is a static final variable for the resigned reasoning for the inactive state */
	public static final String RESIGNED_TERMINATION = "Resigned";
	
	/** This is a static final variable for the fired reasoning for the inactive state */
	public static final String FIRED_TERMINATION = "Fired";
	
	/** This is the final variable for the submitted state inner class */
	private final ApplicationState submittedState = new SubmittedState();
	
	/** This is the final variable for the rejected state inner class */
    private final ApplicationState rejectedState = new RejectedState();
    
    /** This is the final variable for the reviewing state inner class */
    private final ApplicationState reviewingState = new ReviewingState();
    
    /** This is the final variable for the interviewing state inner class */
    private final ApplicationState interviewingState = new InterviewingState();
    
    /** This is the final variable for the processing state inner class */
    private final ApplicationState processingState = new ProcessingState();
    
    /** This is the final variable for the hired state inner class */
    private final ApplicationState hiredState = new HiredState();
    
    /** This is the final variable for the inactive state inner class */
    private final ApplicationState inactiveState = new InactiveState();
	
	/**
	 * This is the first of two Application Constructors. 
	 * This one only uses the applicant's information to construct an Application
	 * @param firstName The applicant's first name
	 * @param surname The applicant's last name
	 * @param unityId The applicant's unity ID
	 * @throws if any parameters are empty or null
	 */
	public Application(String firstName, String surname, String unityId) {
		if(counter == 0) {
			counter = 1;
		}
		
		setFirstName(firstName);
		setSurname(surname);
		setuUnityId(unityId);
		setState(SUBMITTED_NAME);
		setReviewer(null);
		setNote(null);
		setId(Application.counter);
		incrementCounter();
	}
	
	/**
	 * This is the second of the two Application Constructors.
	 * This one uses all of the information, including the reviewer and his/her notes
	 * @param id The unique identification number of the application
	 * @param state The current state of the application
	 * @param firstName The applicant's first name
	 * @param surname The applicant's last name
	 * @param unityId The applicant's unity ID
	 * @param reviewer The application reviewer's id
	 * @param note The application reviewer's notes on the application
	 * @throws if any parameters are empty or null
	 */
	public Application(int id, String state, String firstName, String surname, String unityId, String reviewer, String note) {
		setFirstName(firstName);
		setSurname(surname);
		setuUnityId(unityId);
		setState(state);
		setReviewer(reviewer);
		setNote(note);
		setId(id);
		if(id > Application.counter) {
			setCounter(id + 1);
		}
	}
	
	/**
	 * This method sets the unique identification number for a specific application
	 * @param id the unique identification number of the specific application
	 */
	private void setId(int id) {
		if(id < 1) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		
		this.applicationId = id;
	}

	/**
	 * This method sets the state of a specific application
	 * @param stateValue the state that the application is being set to
	 * @throws IllegalArgumentException if the state parameter is not the correct name
	 */
	private void setState(String stateValue) {
		if(stateValue == null || "".equals(stateValue)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		
		switch(stateValue) {
			case SUBMITTED_NAME: this.state = submittedState; break;
			case REJECTED_NAME: this.state = rejectedState; break;
			case REVIEWING_NAME: this.state = reviewingState; break;
			case INTERVIEWING_NAME: this.state = interviewingState; break;
			case PROCESSING_NAME: this.state = processingState; break;
			case HIRED_NAME: this.state = hiredState; break;
			case INACTIVE_NAME: this.state = inactiveState; break;
			default: throw new IllegalArgumentException("Invalid state.");
		}
	}
	
	/**
	 * This method gets the unique identification number for a specific application
	 * @return the unique identification number of the specific application
	 */
	public int getId() {
		return applicationId;
	}

	/**
	 * This method gets the specific state of an application
	 * @return the state of the application
	 */
	public String getState() {
		return state.getStateName();
	}
	
	/**
	 * This method returns the applicant's first name
	 * @return the applicant's first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * This method sets the applicant's first name
	 * @param firstName the applicant's first name
	 */
	private void setFirstName(String firstName) {
		if(firstName == null || "".equals(firstName)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		
		this.firstName = firstName;
	}
	
	/**
	 * This method gets the applicant's last name
	 * @return the applicant's last name
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * This method sets the applicant's last name
	 * @param surname the applicant's last name
	 */
	private void setSurname(String surname) {
		if(surname == null || "".equals(surname)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		
		this.surname = surname;
	}

	/**
	 * This method returns the applicant's unity ID
	 * @return the applicant's unity ID
	 */
	public String getUnityId() {
		return unityId;
	}
	
	/**
	 * This method sets the applicant's unity ID
	 * @param unityId the applicant's unity ID
	 */
	private void setuUnityId(String unityId) {
		if(unityId == null || "".equals(unityId)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		
		this.unityId = unityId;
	}
	
	/**
	 * This method returns the application's reviewer
	 * @return the application's reviewer's id
	 */
	public String getReviewer() {
		if(reviewer == null) {
			return "";
		}
		return reviewer;
	}
	
	/**
	 * This method sets the application's reviewer
	 * @param reviewer the application's reviewers id
	 */
	private void setReviewer(String reviewer) {
		if("".equals(reviewer)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		if((state == submittedState || state == rejectedState) && reviewer != null) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		if(state != submittedState && state != rejectedState && reviewer == null) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		
		this.reviewer = reviewer;
	}
	
	/**
	 * This method returns the reviewer's note under the application
	 * @return the reviewer's notes
	 */
	public String getNote() {
		if(note == null) {
			return "";
		}
		return note;
	}
	
	/**
	 * This method sets the reviewer's note under the application
	 * @param note the reviewer's notes
	 */
	private void setNote(String note) {
		if("".equals(note)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		if((state == inactiveState || state == rejectedState) && note == null) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		if(state == inactiveState && !COMPLETED_TERMINATION.equals(note) && !RESIGNED_TERMINATION.equals(note) && !FIRED_TERMINATION.equals(note)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		if(state == rejectedState && !QUALIFICATIONS_REJECTION.equals(note) && !INCOMPLETE_REJECTION.equals(note) && !POSITIONS_REJECTION.equals(note) && !DUPLICATE_REJECTION.equals(note)){
			throw new IllegalArgumentException("Application cannot be created.");
		}
		if(note != null && state != inactiveState && state != rejectedState) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		
		this.note = note;
	}

	/**
	 * This method increments the counter, presumably of the number of applications
	 */
	public static void incrementCounter() {
		counter++;
	}
	
	/**
	 * This method sets the counter to a specific number, presumably the identification number starting position
	 * @param newCount the starter number for the counter variable
	 */
	public static void setCounter(int newCount) {
		counter = newCount;
	}
	
	/**
	 * This method returns a string version of an application
	 * @return a string version of the application
	 */
	public String toString() {
		String s = "";
		s += getId() + "," + getState() + "," + getFirstName() + "," + getSurname() + "," + getUnityId() + "," + getReviewer() + "," + getNote();
		return s;
	}
	
	/**
	 * This method updates an application with a specific command
	 * @param c the command that is being used to update the application
	 * @throws UnsupportedOperationException if current state determines that the transition is not appropriate for the code
	 */
	public void update (Command c) {
		state.updateState(c);
	}

	
	
	
	
	/**
	 * Interface for states in the Application State Pattern.  All 
	 * concrete Application states must implement the ApplicationState interface.
	 * The ApplicationState interface should be a private interface of the 
	 * Application class.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu) 
	 */
	private interface ApplicationState {
		
		/**
		 * Update the Application from the given Command.
		 * An UnsupportedOperationException is thrown if the Command
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the Application's
		 * state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 * for the given state.
		 */
		void updateState(Command command);
		
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		String getStateName();

	}


	/**
	 * The SubmittedState Class is a class representing a position in the submitted state.
	 * @author John Eakes
	 */
	private class SubmittedState implements ApplicationState {
		
		/**
		 * This method updates the application's state to submitted
		 * @param command the command that is being updated
		 */
		@Override
		public void updateState(Command command) {
			switch(command.getCommand()) {
			case Command.CommandValue.ASSIGN: setState(REVIEWING_NAME);
												setReviewer(command.getCommandInformation());
											break;
											
			case Command.CommandValue.REJECT: setState(REJECTED_NAME);
											try {
												setNote(command.getCommandInformation());
											} catch(IllegalArgumentException e) {
												setState(SUBMITTED_NAME);
												throw new UnsupportedOperationException("Invalid command.");
											}
											break;
											
			default: throw new UnsupportedOperationException("Invalid command.");
			}
		}

		/**
		 * This method gets the current state name 
		 * @return a string representing the state of the Application
		 */
		@Override
		public String getStateName() {
			return SUBMITTED_NAME;
		}
		
	}


	/**
	 * The RejectedState Class is a class representing a position in the Rejected state
	 * @author John Eakes
	 */
	private class RejectedState implements ApplicationState {

		/**
		 * This method updates the application's state to rejected
		 * @param command the command that is being updated
		 */
		@Override
		public void updateState(Command command) {
			switch(command.getCommand()) {
			case Command.CommandValue.RESUBMIT: setState(SUBMITTED_NAME);
												setNote(command.getCommandInformation());
											break;
			default: throw new UnsupportedOperationException("Invalid command.");
			}
		}

		/**
		 * This method gets the current state name 
		 * @return a string representing the state of the Application
		 */
		@Override
		public String getStateName() {
			return REJECTED_NAME;
		}
	}


	/**
	 * The ReviewingState Class is a class representing a position in the reviewing state
	 * @author John Eakes
	 */
	private class ReviewingState implements ApplicationState {

		/**
		 * This method updates the application's state to reviewing
		 * @param command the command that is being updated
		 */
		@Override
		public void updateState(Command command) {
			switch(command.getCommand()) {
			case Command.CommandValue.REJECT: setState(REJECTED_NAME);
											try {
												setNote(command.getCommandInformation());
											} catch (IllegalArgumentException e) {
												setState(REVIEWING_NAME);
												throw new UnsupportedOperationException("Invalid command.");
											}
											setReviewer(null);
											break;
			
			case Command.CommandValue.SCHEDULE: setState(INTERVIEWING_NAME);
												break;
				
			case Command.CommandValue.RETURN: setState(SUBMITTED_NAME);
											  setReviewer(null);
											  break;
											  
			default: throw new UnsupportedOperationException("Invalid command.");
			}
		}

		/**
		 * This method gets the current state name 
		 * @return a string representing the state of the Application
		 */
		@Override
		public String getStateName() {
			return REVIEWING_NAME;
		}
	}

	
	/**
	 * The InterviewingState Class is a class representing a position in the interviewing state
	 * @author John Eakes
	 */
	private class InterviewingState implements ApplicationState {

		/**
		 * This method updates the application's state to interviewing
		 * @param command the command that is being updated
		 */
		@Override
		public void updateState(Command command) {
			switch(command.getCommand()) {
			case Command.CommandValue.ASSIGN: setState(REVIEWING_NAME);
											  setReviewer(command.getCommandInformation());
											  setNote(null);
											  break;
			
			case Command.CommandValue.REJECT: setState(REJECTED_NAME);
											  try {
												  setNote(command.getCommandInformation());
											  } catch(IllegalArgumentException e) {
												  setState(INTERVIEWING_NAME);
												  throw new UnsupportedOperationException("Invalid command.");
											  }
											  setReviewer(null);
											  break;
				
			case Command.CommandValue.SCHEDULE: setState(INTERVIEWING_NAME);
												setNote(null);
												break;
				
			case Command.CommandValue.PROCESS: setState(PROCESSING_NAME);
											   setNote(null);
											   break;
											   
			default: throw new UnsupportedOperationException("Invalid command.");
			}
		}

		/**
		 * This method gets the current state name 
		 * @return a string representing the state of the Application
		 */
		@Override
		public String getStateName() {
			return INTERVIEWING_NAME;
		}
	}

	
	/**
	 * The ProcessingState Class is a class representing a position in the processing state
	 * @author John Eakes
	 */
	private class ProcessingState implements ApplicationState {

		/**
		 * This method updates the application's state to processing
		 * @param command the command that is being updated
		 */
		@Override
		public void updateState(Command command) {
			switch(command.getCommand()) {
			case Command.CommandValue.REJECT: setState(REJECTED_NAME);
											  try {
												  setNote(command.getCommandInformation());
											  } catch(IllegalArgumentException e) {
												  setState(PROCESSING_NAME);
												  throw new UnsupportedOperationException("Invalid command.");
											  }
											  setReviewer(null);
											  break;
				
			case Command.CommandValue.HIRE: setState(HIRED_NAME);
												break;
											   
			default: throw new UnsupportedOperationException("Invalid command.");
			}
		}

		/**
		 * This method gets the current state name 
		 * @return a string representing the state of the Application
		 */
		@Override
		public String getStateName() {
			return PROCESSING_NAME;
		}
	}

	
	/**
	 * The HiredState Class is a class representing a position in the hired state
	 * @author John Eakes
	 */
	private class HiredState implements ApplicationState {

		/**
		 * This method updates the application's state to hired
		 * @param command the command that is being updated
		 */
		@Override
		public void updateState(Command command) {
			switch(command.getCommand()) {
			case Command.CommandValue.TERMINATE: setState(INACTIVE_NAME);
											  try {
												  setNote(command.getCommandInformation());
											  } catch(IllegalArgumentException e) {
												  setState(HIRED_NAME);
												  throw new UnsupportedOperationException("Invalid command.");
											  }
											  break;
											   
			default: throw new UnsupportedOperationException("Invalid command.");
			}
		}

		/**
		 * This method gets the current state name 
		 * @return a string representing the state of the Application
		 */
		@Override
		public String getStateName() {
			return HIRED_NAME;
		}
	}

	
	/**
	 * The InactiveState Class is a class representing a position in the inactive state
	 * @author John Eakes
	 */
	private class InactiveState implements ApplicationState {

		/**
		 * This method updates the application's state to inactive
		 * @param command the command that is being updated
		 */
		@Override
		public void updateState(Command command) {
			switch(command.getCommand()) {
			default: throw new UnsupportedOperationException("Invalid command.");
			}
			
		}

		/**
		 * This method gets the current state name 
		 * @return a string representing the state of the Application
		 */
		@Override
		public String getStateName() {
			return INACTIVE_NAME;
		}
	}
}