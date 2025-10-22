package edu.ncsu.csc216.wolf_hire.model.io;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;

import edu.ncsu.csc216.wolf_hire.model.application.Application;
import edu.ncsu.csc216.wolf_hire.model.manager.Position;

/**
 * Processes a given file containing specific position and application information 
 * and creates a list of positions and their applications
 * @author John Eakes
 */
public class PositionReader {

	/**
	 * This method reads a list of positions from a file
	 * @param fileName the name of the file being read
	 * @return the ArrayList of positions that're in the file
	 */
	public static ArrayList<Position> readPositionFile(String fileName) {
		ArrayList<Position> positions = new ArrayList<Position>();
		try {
			Scanner scanner = new Scanner(new FileInputStream(fileName));
			String fileContents = "";
			while(scanner.hasNextLine()) {
				fileContents += scanner.nextLine() + "\n";
			}
			scanner.close();
			
			Scanner scanner2 = new Scanner(fileContents);			
			scanner2.useDelimiter("\\r?\\n?[#]");
			
			while(scanner2.hasNext()) {
				Position position = processPosition(scanner2.next().substring(1));
				if(position != null && position.getApplications().size() != 0) {
					positions.add(position);
				}
			}
			scanner2.close();
			
			
		} catch(FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file " + fileName);
		}
		return positions;
	}
		
	
	/**
	 * This method processes a String version of a position text, to be read by the program
	 * @param positionText the text representing the position that is to be read
	 * @return the position that's being represented by the String
	 */
	public static Position processPosition(String positionText) {
		Scanner scanner = new Scanner(positionText);
		String positionLine = scanner.nextLine();
		Position newPosition = processPositionLine(positionLine);
		
		if(newPosition == null) {
			scanner.close();
			return null;
		}
		
		scanner.close();
		Scanner scanner2 = new Scanner(positionText);
		scanner2.useDelimiter("\\r?\\n?[*]");
		scanner2.next();
		
		while(scanner2.hasNext()) {
			Application application = processApplication(scanner2.next().substring(1));
			if(application != null) {
				try {
					newPosition.addApplication(application);
				} catch(IllegalArgumentException e) {
					//Does nothing other than catches
				}
			}
		}
		scanner2.close();
		return newPosition;
	}
	
	/**
	 * This method processes a whole line of position information, to be read by the program
	 * @param positionLine the line of position information
	 * @return the position that's being represented by the String
	 */
	public static Position processPositionLine(String positionLine) {
		Scanner scanner = new Scanner(positionLine);
		scanner.useDelimiter(",");
		
		String positionName;
		int hoursPerWeek;
		int payRate;
		
		positionName = scanner.next();
		try {
			hoursPerWeek = scanner.nextInt();
			payRate = scanner.nextInt();
		} catch(Exception e) {
			scanner.close();
			return null;
		}
		scanner.close();
		
		try {
			return new Position(positionName, hoursPerWeek, payRate);
		} catch(IllegalArgumentException e) {
			return null;
		}
	}
	
	/**
	 * This method processes a String version of an Application, to be read by the program
	 * @param applicationLine the text representing the application that is to be read
	 * @return the application that's being represented by the String
	 */
	public static Application processApplication(String applicationLine) {
		Scanner scanner = new Scanner(applicationLine);
		scanner.useDelimiter(",");
		
		int id;
		String state;
		String firstName;
		String surname;
		String unityId;
		String reviewer;
		String note;
		
		try {
			id = scanner.nextInt();
		} catch(Exception e) {
			scanner.close();
			return null;
		}
		state = scanner.next();
		firstName = scanner.next();
		surname = scanner.next();
		unityId = scanner.next();
		reviewer = scanner.next();
		if(scanner.hasNext()) {
			note = scanner.next();
		}
		else {
			note = null;
		}
		
		if("".equals(reviewer)) {
			reviewer = null;
		}
		if("\n".equals(note)) {
			note = null;
		}
		
		try {
			scanner.close();
			return new Application(id, state, firstName, surname, unityId, reviewer, note);
		} catch(IllegalArgumentException e) {
			scanner.close();
			return null;
		}
	}
}
