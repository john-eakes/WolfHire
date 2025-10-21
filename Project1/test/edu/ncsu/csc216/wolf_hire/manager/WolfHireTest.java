package edu.ncsu.csc216.wolf_hire.manager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_hire.model.manager.WolfHire;
import edu.ncsu.csc216.wolf_hire.model.application.Application;
import edu.ncsu.csc216.wolf_hire.model.command.Command;
import edu.ncsu.csc216.wolf_hire.model.command.Command.CommandValue;
import edu.ncsu.csc216.wolf_hire.model.manager.Position;
/**
 * The WolfHireTest Class tests WolfHire.java, going for a minimum of 80% code coverage with my tests
 * @author John Eakes
 */
class WolfHireTest {

	
	
	/**
	 * Sets up wolfHire class
	 */
	WolfHire manager = WolfHire.getInstance();
	
	
	
	/**
	 * Tests addNewPosition method
	 */
	@Test
	void testAddNewPosition() {
		Application.setCounter(1);
		manager.setActivePosition(null);
		
		manager.addNewPosition("TA", 10, 10);
		assertEquals("TA", manager.getActivePositionName());
		assertEquals(1, manager.getPositionList().length);
		
		manager.addNewPosition("Grader", 20, 20);
		assertEquals("Grader", manager.getActivePositionName());
		assertEquals(2, manager.getPositionList().length);
		
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> manager.addNewPosition("TA", 20, 15));
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> manager.addNewPosition(null, 10, 10));
		Exception e3 = assertThrows(IllegalArgumentException.class, () -> manager.addNewPosition("", 10, 10));

		
		assertEquals("Position cannot be created.", e1.getMessage());
		assertEquals("Position cannot be created.", e2.getMessage());
		assertEquals("Position cannot be created.", e3.getMessage());
	}
	
	/**
	 * Tests setActivePosition
	 */
	@Test
	void testSetActivePosition() {
		Position p1 = new Position("Teaching Assistant", 15, 15);
		manager.setActivePosition(p1);
		
		assertEquals(p1, manager.getActivePosition());
	}
	
	/**
	 * Tests addApplicationToPosition
	 */
	@Test
	void testAddApplicationToPosition() {
		Application.setCounter(1);
		
		Position p2 = new Position("Teaching Assistant", 15, 15);
		manager.setActivePosition(p2);
		
		manager.addApplicationToPosition("John", "Eakes", "jpeakes");
		manager.addApplicationToPosition("Zach", "Newman", "zmnewman");
		manager.addApplicationToPosition("Josh", "Medlin", "jpmedlin");
		
		Object[][] application = manager.getApplicationsAsArray("All");
		
		assertEquals("1", application[0][0]);
		assertEquals("Submitted", application[0][1]);
		assertEquals("jpeakes", application[0][2]);
		assertEquals("", application[0][3]);
		
		assertEquals("2", application[1][0]);
		assertEquals("Submitted", application[1][1]);
		assertEquals("zmnewman", application [1][2]);
		assertEquals("", application[1][3]);
		
		assertEquals("3", application[2][0]);
		assertEquals("Submitted", application[2][1]);
		assertEquals("jpmedlin", application[2][2]);
		assertEquals("", application[2][3]);
	}
	
	/**
	 * Test getPositionName
	 */
	@Test
	void testGetPositionName() {
		manager.setActivePosition(null);
		assertEquals(null, manager.getActivePositionName());
		
		Position p1 = new Position("TA", 10, 10);
		manager.setActivePosition(p1);
		assertEquals("TA", manager.getActivePositionName());
	}

	/**
	 * Test executeCommand
	 */
	@Test
	void testExecuteCommand() {
		Application.setCounter(1);
		
		manager.setActivePosition(null);
		Command c = new Command(CommandValue.ASSIGN, "zmnewman");
		
		Position p1 = new Position("CSC 216 Grader", 10, 13);
		manager.setActivePosition(p1);
		
		manager.addApplicationToPosition("John", "Doe", "jodoe");
		manager.addApplicationToPosition("Jane", "Doe", "jndoe");
		manager.addApplicationToPosition("Bates", "Smith", "bpsmith");

		
		manager.executeCommand(1, c);
		
		assertEquals("Reviewing", manager.getApplicationById(1).getState());
	}
	
	/**
	 * Test deleteApplicationById
	 */
	@Test
	void testDeleteApplicationById() {
		Application.setCounter(1);
		
		Position p1 = new Position("CSC 216 Grader", 10, 13);
		manager.setActivePosition(p1);
		
		manager.addApplicationToPosition("John", "Doe", "jodoe");
		manager.addApplicationToPosition("Jane", "Doe", "jndoe");
		manager.addApplicationToPosition("Bates", "Smith", "bpsmith");
		
		manager.deleteApplicationById(2);
		assertEquals(null, manager.getApplicationById(2));
	}
	
	/**
	 * Test getApplicationById
	 */
	@Test
	void testGetApplicationById() {
		Application.setCounter(1);
		manager.setActivePosition(null);
		assertEquals(null, manager.getApplicationById(0));
		
		Position p1 = new Position("Software Engineer", 20, 20);
		manager.setActivePosition(p1);
		
		manager.addApplicationToPosition("Brad", "Yokum", "bgyokum");
		manager.addApplicationToPosition("Colby", "Bowers", "cpbowers");
		
		
		assertEquals(1, manager.getApplicationById(1).getId());
		assertEquals("Submitted", manager.getApplicationById(1).getState());
		assertEquals("Brad", manager.getApplicationById(1).getFirstName());
		assertEquals("Yokum", manager.getApplicationById(1).getSurname());
		assertEquals("bgyokum", manager.getApplicationById(1).getUnityId());
		assertEquals("", manager.getApplicationById(1).getReviewer());
		assertEquals("", manager.getApplicationById(1).getNote());
	}	
	
	/**
	 * Tests loadPositionFromFile
	 */
	@Test
	void testLoadPositionFromFile() {
		Application.setCounter(1);
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> manager.loadPositionsFromFile("test-files/skibidi.txt"));		
		assertEquals("Unable to load file test-files/skibidi.txt", e1.getMessage());
	}
	
	/**
	 * Tests SavePositionsToFile
	 */
	@Test
	void testSavePositionToFile() {
		Application.setCounter(1);
		manager.setActivePosition(null);
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> manager.savePositionsToFile("TA_position.txt"));
		assertEquals("Unable to save file.", e1.getMessage());
	}
}
