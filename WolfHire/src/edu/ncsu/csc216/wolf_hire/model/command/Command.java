package edu.ncsu.csc216.wolf_hire.model.command;
/**
 * The Command Class holds the information that will change an application's state.
 * @author John Eakes
 */
public class Command {
	/**
	 * This is the enumeration for the eight state options: 
	 * ASSIGN, REJECT, RESUBMIT, RETURN, SCHEDULE, PROCESS, HIRE, TERMINATE
	 */
	public enum CommandValue {
		/** The enumeration value options for the states of an application */
		ASSIGN, REJECT, RESUBMIT, RETURN, SCHEDULE, PROCESS, HIRE, TERMINATE
	}
	/** This is the variable for the enum value of the state of the application */
	private CommandValue command;
	
	/** This is the variable for information related to the state of the application*/ 
	private String commandInformation;
	
	/**
	 * This is the constructor for class Command. The constructor sets the values for the two variables
	 * @param command the state value
	 * @param commandInformation the state information
	 * @throws IllegalArgumentException if command is null, if command is assign, reject, or terminate while commandInformation is null or empty, or
	 * 									if command is resubmit, return, schedule, process, or hire while commandInformation is non-null.
	 */
	public Command(CommandValue command, String commandInformation) {
		if(command == null) {
			throw new IllegalArgumentException("Invalid information.");
		}
		if((command == CommandValue.ASSIGN || command == CommandValue.REJECT || command == CommandValue.TERMINATE) &&
		   (commandInformation == null || "".equals(commandInformation))) {
			throw new IllegalArgumentException("Invalid information.");
		}
		if((command == CommandValue.RESUBMIT || command == CommandValue.RETURN || command == CommandValue.SCHEDULE || command == CommandValue.PROCESS || command == CommandValue.HIRE) &&
		   commandInformation != null) {
			throw new IllegalArgumentException("Invalid information.");
		}
		this.command = command;
		this.commandInformation = commandInformation;
	}
	
	/**
	 * This method returns the state of the application
	 * @return the state value
	 */
	public CommandValue getCommand() {
		return command;
	}
	
	/**
	 * This method returns the state's information
	 * @return the state information
	 */
	public String getCommandInformation() {
		return commandInformation;
	}
}
