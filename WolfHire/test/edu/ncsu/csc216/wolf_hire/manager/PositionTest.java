package edu.ncsu.csc216.wolf_hire.manager;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_hire.model.application.Application;
import edu.ncsu.csc216.wolf_hire.model.command.Command;
import edu.ncsu.csc216.wolf_hire.model.command.Command.CommandValue;
import edu.ncsu.csc216.wolf_hire.model.manager.Position;
/**
 * The Position Class tests Position.java, going for a minimum of 80% code coverage with my tests
 * @author John Eakes
 */
class PositionTest {

	/**
	 * Tests the position constructor with all valid values
	 */
	@Test
	void testPositionConstructorValid() {
		Position p1 = new Position("TA", 10, 10);
		
		assertEquals("TA", p1.getPositionName());
		assertEquals(10, p1.getHoursPerWeek());
		assertEquals(10, p1.getPayRate());
	}
	
	/**
	 * Tests the position constructor with invalid values
	 */
	@Test
	void testPositionConstructorInvalid() {		
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new Position(null, 10, 10));
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> new Position("", 10, 10));
		Exception e3 = assertThrows(IllegalArgumentException.class, () -> new Position("TA", 0, 10));
		Exception e4 = assertThrows(IllegalArgumentException.class, () -> new Position("TA", 30, 10));
		Exception e5 = assertThrows(IllegalArgumentException.class, () -> new Position("TA", 10, 5));
		Exception e6 = assertThrows(IllegalArgumentException.class, () -> new Position("TA", 10, 50));
		
		assertEquals("Position cannot be created.", e1.getMessage());
		assertEquals("Position cannot be created.", e2.getMessage());
		assertEquals("Position cannot be created.", e3.getMessage());
		assertEquals("Position cannot be created.", e4.getMessage());
		assertEquals("Position cannot be created.", e5.getMessage());
		assertEquals("Position cannot be created.", e6.getMessage());	
	}
	
	/**
	 * Tests setApplicationId
	 */
	@Test
	void testSetApplicationId() {
		Application.setCounter(1);
		Position p10 = new Position("TA", 10, 10);
		
		p10.addApplication("John", "Eakes", "jpeakes");
		p10.addApplication("Zach", "Newman", "zmnewman");
		p10.addApplication("Josh", "Medlin", "jpmedlin");
		
		p10.setApplicationId();
		p10.addApplication("Josh", "Smith", "jpsmith");

		assertEquals(4, p10.getApplications().get(3).getId());
	}
	
	/**
	 * Tests addApplication with application parameter
	 */
	@Test
	void testAddApplicationWithApplication() {
		Application.setCounter(1);
		Position p1 = new Position("TA", 10, 10);
		Application a1 = new Application(1, "Submitted", "John", "Eakes", "jpeakes", null, null);
		Application a2 = new Application(2, "Reviewing", "Zach", "Newman", "zmnewman", "jpeakes", null);
		Application a3 = new Application(1, "Submitted", "John", "Eakes", "jpeakes", null, null);

		
		p1.addApplication(a1);
		p1.addApplication(a2);

		Exception e1 = assertThrows(IllegalArgumentException.class, () -> p1.addApplication(a3));
		assertEquals("Application cannot be created.", e1.getMessage());
	}
	
	/**
	 * Tests getApplicationById
	 */
	@Test
	void testGetApplicationById() {
		Application.setCounter(1);
		Position p1 = new Position("TA", 10, 10);
		Application a1 = new Application(1, "Submitted", "John", "Eakes", "jpeakes", null, null);
		Application a2 = new Application(2, "Reviewing", "Zach", "Newman", "zmnewman", "jpeakes", null);
		Application a3 = new Application(3, "Submitted", "John", "Eakes", "jpeakes", null, null);
		
		p1.addApplication(a1);
		p1.addApplication(a2);
		p1.addApplication(a3);
		
		assertEquals(null, p1.getApplicationById(4));
		
		Application retrievedApp = p1.getApplicationById(3);
		assertEquals(3, retrievedApp.getId());
		assertEquals("Submitted", retrievedApp.getState());
		assertEquals("John", retrievedApp.getFirstName());
		assertEquals("Eakes", retrievedApp.getSurname());
		assertEquals("jpeakes", retrievedApp.getUnityId());
		assertEquals("", retrievedApp.getReviewer());
		assertEquals("", retrievedApp.getNote());
	}
	
	/**
	 * Tests executeCommand method
	 */
	@Test
	void testExecuteCommand() {
		Application.setCounter(1);
		Position p2 = new Position("TA", 10, 10);
		Application a1 = new Application(1, "Submitted", "John", "Eakes", "jpeakes", null, null);
		Application a2 = new Application(2, "Reviewing", "Zach", "Newman", "zmnewman", "jpeakes", null);
		Application a3 = new Application(3, "Submitted", "John", "Eakes", "jpeakes", null, null);
		
		p2.addApplication(a1);
		p2.addApplication(a2);
		p2.addApplication(a3);
		
		Command c = new Command(CommandValue.ASSIGN, "zmnewman");
		
		p2.executeCommand(1, c);
		assertEquals("Reviewing", a1.getState());
		
		p2.executeCommand(4, c);
	}
	
	/**
	 * Tests removeApplicationById
	 */
	@Test
	void testRemoveApplicationById() {
		Application.setCounter(1);
		Position p1 = new Position("TA", 10, 10);
		Application a1 = new Application(1, "Submitted", "John", "Eakes", "jpeakes", null, null);
		Application a2 = new Application(2, "Reviewing", "Zach", "Newman", "zmnewman", "jpeakes", null);
		Application a3 = new Application(3, "Submitted", "John", "Eakes", "jpeakes", null, null);
		
		p1.addApplication(a1);
		p1.addApplication(a2);
		p1.addApplication(a3);
		
		p1.deleteApplicationById(3);
		assertEquals(null, p1.getApplicationById(3));
	}
	
	/**
	 * Tests Position toString
	 */
	@Test
	void testToString() {
		Application.setCounter(1);
		Position p1 = new Position("TA", 10, 10);
		Application a1 = new Application(1, "Submitted", "John", "Eakes", "jpeakes", null, null);
		
		p1.addApplication(a1);
		
		assertEquals("TA,10,10", p1.toString());
	}

}
