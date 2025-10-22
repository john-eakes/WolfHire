package edu.ncsu.csc216.wolf_hire.application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_hire.model.application.Application;
import edu.ncsu.csc216.wolf_hire.model.command.Command;
import edu.ncsu.csc216.wolf_hire.model.command.Command.CommandValue;
/**
 * The ApplicationTest Class tests Application.java, going for a minimum of 80% code coverage with my tests
 * @author John Eakes
 */
class ApplicationTest { 

	/**
	 * Tests constructing an Application with name and unity id
	 */
	@Test
	void testApplicationWithNames() {
		Application.setCounter(1);
		
		Application a = new Application("John", "Eakes", "jpeakes");
		
		assertEquals("John", a.getFirstName());
		assertEquals("Eakes", a.getSurname());
		assertEquals("jpeakes", a.getUnityId());
	}
	
	/**
	 * Tests constructing an Application with all info
	 */
	@Test
	void testAplicationWithEverything() {
		Application.setCounter(1);
		
		Application a = new Application(1, "Submitted", "John", "Eakes", "jpeakes", null, null);
		Application b = new Application(2, "Rejected", "Josh", "Medlin", "jpmedlin", null, "Positions");
		Application c = new Application(3, "Reviewing", "Josh", "Medlin", "jpmedlin", "jpeakes", null);
		Application d = new Application(4, "Interviewing", "Josh", "Medlin", "jpmedlin", "jpeakes", null);
		Application e = new Application(5, "Processing", "Josh", "Medlin", "jpmedlin", "jpeakes", null);
		Application f = new Application(6, "Hired", "Josh", "Medlin", "jpmedlin", "jpeakes", null);
		Application g = new Application(7, "Inactive", "Josh", "Medlin", "jpmedlin", "jpeakes", "Resigned");


		
		
		
		assertEquals(1, a.getId());
		assertEquals("Submitted", a.getState());
		assertEquals("John", a.getFirstName());
		assertEquals("Eakes", a.getSurname());
		assertEquals("jpeakes", a.getUnityId());
		assertEquals("", a.getReviewer());
		assertEquals("", a.getNote());
		
		assertEquals(2, b.getId());
		assertEquals("Rejected", b.getState());
		assertEquals("Josh", b.getFirstName());
		assertEquals("Medlin", b.getSurname());
		assertEquals("jpmedlin", b.getUnityId());
		assertEquals("", b.getReviewer());
		assertEquals("Positions", b.getNote());
		
		assertEquals(3, c.getId());
		assertEquals("Reviewing", c.getState());
		assertEquals("Josh", c.getFirstName());
		assertEquals("Medlin", c.getSurname());
		assertEquals("jpmedlin", c.getUnityId());
		assertEquals("jpeakes", c.getReviewer());
		assertEquals("", c.getNote());
		
		assertEquals(4, d.getId());
		assertEquals("Interviewing", d.getState());
		assertEquals("Josh", d.getFirstName());
		assertEquals("Medlin", d.getSurname());
		assertEquals("jpmedlin", d.getUnityId());
		assertEquals("jpeakes", d.getReviewer());
		assertEquals("", d.getNote());
		
		assertEquals(5, e.getId());
		assertEquals("Processing", e.getState());
		assertEquals("Josh", e.getFirstName());
		assertEquals("Medlin", e.getSurname());
		assertEquals("jpmedlin", e.getUnityId());
		assertEquals("jpeakes", e.getReviewer());
		assertEquals("", e.getNote());
		
		assertEquals(6, f.getId());
		assertEquals("Hired", f.getState());
		assertEquals("Josh", f.getFirstName());
		assertEquals("Medlin", f.getSurname());
		assertEquals("jpmedlin", f.getUnityId());
		assertEquals("jpeakes", f.getReviewer());
		assertEquals("", f.getNote());
		
		assertEquals(7, g.getId());
		assertEquals("Inactive", g.getState());
		assertEquals("Josh", g.getFirstName());
		assertEquals("Medlin", g.getSurname());
		assertEquals("jpmedlin", g.getUnityId());
		assertEquals("jpeakes", g.getReviewer());
		assertEquals("Resigned", g.getNote());
	}
	
	/**
	 * Tests the first constructor with invalid parameters 
	 */
	@Test
	void testFirstConstructorInvalid() {
		Application.setCounter(1);
		
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new Application(null, "Eakes", "jpeakes"));
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> new Application("John", null, "jpeakes"));
		Exception e3 = assertThrows(IllegalArgumentException.class, () -> new Application("John", "Eakes", null));

		Exception e4 = assertThrows(IllegalArgumentException.class, () -> new Application("", "Eakes", "jpeakes"));
		Exception e5 = assertThrows(IllegalArgumentException.class, () -> new Application("John", "", "jpeakes"));
		Exception e6 = assertThrows(IllegalArgumentException.class, () -> new Application("John", "Eakes", ""));

		
		assertEquals("Application cannot be created.", e1.getMessage());
		assertEquals("Application cannot be created.", e2.getMessage());
		assertEquals("Application cannot be created.", e3.getMessage());
		
		assertEquals("Application cannot be created.", e4.getMessage());
		assertEquals("Application cannot be created.", e5.getMessage());
		assertEquals("Application cannot be created.", e6.getMessage());
	}
	
	/**
	 * Tests the second constructor with invalid parameters
	 */
	@Test
	void testSecondConstructorInvalid() {
		Application.setCounter(1);
		
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new Application(0, "Submitted", "John", "Eakes", "jpeakes", null, null));
		assertEquals("Application cannot be created.", e1.getMessage());

		Exception e2 = assertThrows(IllegalArgumentException.class, () -> new Application(1, null, "John", "Eakes", "jpeakes", null, null));
		assertEquals("Application cannot be created.", e2.getMessage());

		Exception e3 = assertThrows(IllegalArgumentException.class, () -> new Application(1, "Submitted", null, "Eakes", "jpeakes", "zmnewman", "Qualifications"));
		assertEquals("Application cannot be created.", e3.getMessage());

		Exception e4 = assertThrows(IllegalArgumentException.class, () -> new Application(1, "Submitted", "John", null, "jpeakes", "zmnewman", "Qualifications"));
		assertEquals("Application cannot be created.", e4.getMessage());

		Exception e5 = assertThrows(IllegalArgumentException.class, () -> new Application(1, "Submitted", "John", "Eakes", null, "zmnewman", "Qualifications"));
		assertEquals("Application cannot be created.", e5.getMessage());

		Exception e8 = assertThrows(IllegalArgumentException.class, () -> new Application(1, "", "John", "Eakes", "jpeakes", "zmnewman", "Qualifications"));
		assertEquals("Application cannot be created.", e8.getMessage());

		Exception e9 = assertThrows(IllegalArgumentException.class, () -> new Application(1, "Submitted", "", "Eakes", "jpeakes", "zmnewman", "Qualifications"));
		assertEquals("Application cannot be created.", e9.getMessage());

		Exception e10 = assertThrows(IllegalArgumentException.class, () -> new Application(1, "Submitted", "John", "", "jpeakes", "zmnewman", "Qualifications"));
		assertEquals("Application cannot be created.", e10.getMessage());

		Exception e11 = assertThrows(IllegalArgumentException.class, () -> new Application(1, "Submitted", "John", "Eakes", "", "zmnewman", "Qualifications"));
		assertEquals("Application cannot be created.", e11.getMessage());

		Exception e12 = assertThrows(IllegalArgumentException.class, () -> new Application(1, "Submitted", "John", "Eakes", "jpeakes", "", "Qualifications"));
		assertEquals("Application cannot be created.", e12.getMessage());

		Exception e13 = assertThrows(IllegalArgumentException.class, () -> new Application(1, "Submitted", "John", "Eakes", "jpeakes", "zmnewman", ""));
		assertEquals("Application cannot be created.", e13.getMessage());
	}
	
	/**
	 * Tests invalid setState method
	 */
	@Test
	void testInvalidSetState() {
		Application.setCounter(1);
		
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new Application(1, "Moved", "John", "Eakes", "jpeakes", "zmnewman", "Qualifications"));

		assertEquals("Invalid state.", e1.getMessage());
	}
	
	/**
	 * Tests null parameter for getReviewer and getNote
	 */
	@Test
	void testNullReviewerAndNote() {
		Application.setCounter(1);
		
		Application a = new Application(1, "Submitted", "John", "Eakes", "jpeakes", null, null);
		Application b = new Application(2, "Hired", "John", "Eakes", "jpeakes", "zmnewman", null);
		
		assertEquals("", a.getReviewer());
		assertEquals("", b.getNote());
	}
	
	/**
	 * Tests toString
	 */
	@Test
	void testToString() {
		Application.setCounter(1);
		
		Application a = new Application(1, "Submitted", "John", "Eakes", "jpeakes", null, null);

		assertEquals("1,Submitted,John,Eakes,jpeakes,,", a.toString());
	}
	
	/**
	 * Tests update method
	 */
	@Test
	void testUpdate(){
		Application.setCounter(1);
		
		Application a = new Application(1, "Submitted", "John", "Eakes", "jpeakes", null, null);
		Command c = new Command(CommandValue.ASSIGN, "zmnewman");
		
		a.update(c);
		assertEquals("Reviewing", a.getState());
		
	}
	
	
	/**
	 * Tests submittedState Class
	 */
	@Test
	void testSubmittedState() {
		Application.setCounter(1);
		
		Application a1 = new Application(1, "Submitted", "John", "Eakes", "jpeakes", null, null);
		Application a2 = new Application(2, "Submitted", "John", "Eakes", "jpeakes", null, null);
		Application a3 = new Application(3, "Submitted", "John", "Eakes", "jpeakes", null, null);
		Application a4 = new Application(4, "Submitted", "John", "Eakes", "jpeakes", null, null);
		
		
		Command c1 = new Command(CommandValue.REJECT, "Qualifications");
		Command c2 = new Command(CommandValue.ASSIGN, "zmnewman");
		Command c3 = new Command(CommandValue.HIRE, null);
		Command c4 = new Command(CommandValue.REJECT, "Balls");
		
		a1.update(c1);
		assertEquals("Rejected", a1.getState());
		assertEquals("Qualifications", a1.getNote());
		
		a2.update(c2);
		assertEquals("Reviewing", a2.getState());
		assertEquals("zmnewman", a2.getReviewer());
		
		Exception e1 = assertThrows(UnsupportedOperationException.class, () -> a3.update(c3));
		assertEquals("Invalid command.", e1.getMessage());		
		
		Exception e2 = assertThrows(UnsupportedOperationException.class, () -> a4.update(c4));
		assertEquals("Invalid command.", e2.getMessage());
	}
	
	/**
	 * Tests rejectedState Class
	 */
	@Test
	void testRejectedState() {
		Application.setCounter(1);
		
		Application a1 = new Application(1, "Rejected", "John", "Eakes", "jpeakes", null, "Qualifications");
		Application a2 = new Application(2, "Rejected", "John", "Eakes", "jpeakes", null, "Qualifications");
		
		Command c1 = new Command(CommandValue.RESUBMIT, null);
		Command c2 = new Command(CommandValue.ASSIGN, "zmnewman");
		
		a1.update(c1);
		assertEquals("Submitted", a1.getState());
		
		Exception e1 = assertThrows(UnsupportedOperationException.class, () -> a2.update(c2));
		assertEquals("Invalid command.", e1.getMessage());
	}
	
	/**
	 * Tests reviewingState Class
	 */
	@Test
	void testReviewingState() {
		Application.setCounter(1);
		
		Application a1 = new Application(1, "Reviewing", "John", "Eakes", "jpeakes", "zmnewman", null);
		Application a2 = new Application(2, "Reviewing", "John", "Eakes", "jpeakes", "zmnewman", null);
		Application a3 = new Application(3, "Reviewing", "John", "Eakes", "jpeakes", "zmnewman", null);
		Application a4 = new Application(4, "Reviewing", "John", "Eakes", "jpeakes", "zmnewman", null);
		
		Command c1 = new Command(CommandValue.REJECT, "Qualifications");
		Command c2 = new Command(CommandValue.SCHEDULE, null);
		Command c3 = new Command(CommandValue.RETURN, null);
		Command c4 = new Command(CommandValue.HIRE, null);
		
		a1.update(c1);
		assertEquals("Rejected", a1.getState());
		assertEquals("Qualifications", a1.getNote());
		
		a2.update(c2);
		assertEquals("Interviewing", a2.getState());
		
		a3.update(c3);
		assertEquals("Submitted", a3.getState());
		
		Exception e1 = assertThrows(UnsupportedOperationException.class, () -> a4.update(c4));
		assertEquals("Invalid command.", e1.getMessage());
	}
	
	/**
	 * Tests InterviewingState Class
	 */
	@Test
	void testInterviewingState() {
		Application.setCounter(1);
		
		Application a1 = new Application(1, "Interviewing", "John", "Eakes", "jpeakes", "zmnewman", null);
		Application a2 = new Application(2, "Interviewing", "John", "Eakes", "jpeakes", "zmnewman", null);
		Application a3 = new Application(3, "Interviewing", "John", "Eakes", "jpeakes", "zmnewman", null);
		Application a4 = new Application(4, "Interviewing", "John", "Eakes", "jpeakes", "zmnewman", null);
		Application a5 = new Application(5, "Interviewing", "John", "Eakes", "jpeakes", "zmnewman", null);
		Application a6 = new Application(6, "Interviewing", "John", "Eakes", "jpeakes", "zmnewman", null);

		
		Command c1 = new Command(CommandValue.ASSIGN, "zmnewman");
		Command c2 = new Command(CommandValue.REJECT, "Qualifications");
		Command c3 = new Command(CommandValue.SCHEDULE, null);
		Command c4 = new Command(CommandValue.PROCESS, null);
		Command c5 = new Command(CommandValue.HIRE, null);
		Command c6 = new Command(CommandValue.REJECT, "Skibidi");
		
		a1.update(c1);
		assertEquals("Reviewing", a1.getState());
		assertEquals("zmnewman", a1.getReviewer());
		
		a2.update(c2);
		assertEquals("Rejected", a2.getState());
		assertEquals("", a1.getNote());
		
		a3.update(c3);
		assertEquals("Interviewing", a3.getState());
		
		a4.update(c4);
		assertEquals("Processing", a4.getState());
		
		Exception e1 = assertThrows(UnsupportedOperationException.class, () -> a5.update(c5));
		assertEquals("Invalid command.", e1.getMessage());
		
		Exception e2 = assertThrows(UnsupportedOperationException.class, () -> a6.update(c6));
		assertEquals("Invalid command.", e2.getMessage());
	}
	
	/**
	 * Tests processingState Class
	 */
	@Test
	void testProcessingState() {
		Application.setCounter(1);
		
		Application a1 = new Application(1, "Processing", "John", "Eakes", "jpeakes", "zmnewman", null);
		Application a2 = new Application(2, "Processing", "John", "Eakes", "jpeakes", "zmnewman", null);
		Application a3 = new Application(3, "Processing", "John", "Eakes", "jpeakes", "zmnewman", null);
		
		Command c1 = new Command(CommandValue.REJECT, "Qualifications");
		Command c2 = new Command(CommandValue.HIRE, null);
		Command c3 = new Command(CommandValue.SCHEDULE, null);
		
		a1.update(c1);
		assertEquals("Rejected", a1.getState());
		assertEquals("Qualifications", a1.getNote());
		
		a2.update(c2);
		assertEquals("Hired", a2.getState());
		
		Exception e1 = assertThrows(UnsupportedOperationException.class, () -> a3.update(c3));
		assertEquals("Invalid command.", e1.getMessage());
	}
	
	/**
	 * Tests hiredState Class
	 */
	@Test
	void testHiredState() {
		Application.setCounter(1);
		
		Application a1 = new Application(1, "Hired", "John", "Eakes", "jpeakes", "zmnewman", null);
		Application a2 = new Application(2, "Hired", "John", "Eakes", "jpeakes", "zmnewman", null);
		Application a3 = new Application(3, "Hired", "John", "Eakes", "jpeakes", "zmnewman", null);

		
		Command c1 = new Command(CommandValue.TERMINATE, "Completed");
		Command c2 = new Command(CommandValue.ASSIGN, "zmnewman");
		Command c3 = new Command(CommandValue.TERMINATE, "Skibidi");

		
		a1.update(c1);
		assertEquals("Inactive", a1.getState());
		
		Exception e1 = assertThrows(UnsupportedOperationException.class, () -> a2.update(c2));
		assertEquals("Invalid command.", e1.getMessage());
		
		Exception e2 = assertThrows(UnsupportedOperationException.class, () -> a3.update(c3));
		assertEquals("Invalid command.", e2.getMessage());
	}
	
	/**
	 * Tests inactiveState Class
	 */
	@Test
	void testInactiveState() {
		Application.setCounter(1);
		
		Application a1 = new Application(1, "Inactive", "John", "Eakes", "jpeakes", "zmnewman", "Fired");
		Command c1 = new Command(CommandValue.RESUBMIT, null);
		
		Exception e1 = assertThrows(UnsupportedOperationException.class, () -> a1.update(c1));
		assertEquals("Invalid command.", e1.getMessage());
	}
}
