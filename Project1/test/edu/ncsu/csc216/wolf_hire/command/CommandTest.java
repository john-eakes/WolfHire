package edu.ncsu.csc216.wolf_hire.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_hire.model.command.Command;
import edu.ncsu.csc216.wolf_hire.model.command.Command.CommandValue;
/**
 * The CommandTest Class tests Command.java, going for a minimum of 80% code coverage with my tests
 * @author John Eakes
 */
class CommandTest {

	/**
	 * Tests the command constructor with valid parameters
	 */
	@Test
	void testCommandValid() {
		Command c1 = new Command(CommandValue.ASSIGN, "zmnewman");
		Command c2 = new Command(CommandValue.REJECT, "Qualifications");
		Command c3 = new Command(CommandValue.TERMINATE, "Completed");
		Command c4 = new Command(CommandValue.RESUBMIT, null);
		Command c5 = new Command(CommandValue.RETURN, null);
		Command c6 = new Command(CommandValue.SCHEDULE, null);
		Command c7 = new Command(CommandValue.PROCESS, null);
		Command c8 = new Command(CommandValue.HIRE, null);
		
		assertEquals(CommandValue.ASSIGN, c1.getCommand());
		assertEquals("zmnewman", c1.getCommandInformation());
		
		assertEquals(CommandValue.REJECT, c2.getCommand());
		assertEquals("Qualifications", c2.getCommandInformation());
		
		assertEquals(CommandValue.TERMINATE, c3.getCommand());
		assertEquals("Completed", c3.getCommandInformation());
		
		assertEquals(CommandValue.RESUBMIT, c4.getCommand());
		
		assertEquals(CommandValue.RETURN, c5.getCommand());
		
		assertEquals(CommandValue.SCHEDULE, c6.getCommand());
		
		assertEquals(CommandValue.PROCESS, c7.getCommand());
		
		assertEquals(CommandValue.HIRE, c8.getCommand());
	}
	
	/**
	 * Tests the command constructor with invalid parameters
	 */
	@Test
	void testCommandInvalid() {		
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new Command(null, null));
		assertEquals("Invalid information.", e1.getMessage());
		
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> new Command(CommandValue.ASSIGN, null));
		assertEquals("Invalid information.", e2.getMessage());
		
		Exception e3 = assertThrows(IllegalArgumentException.class, () -> new Command(CommandValue.ASSIGN, ""));
		assertEquals("Invalid information.", e3.getMessage());
		
		Exception e4 = assertThrows(IllegalArgumentException.class, () -> new Command(CommandValue.REJECT, null));
		assertEquals("Invalid information.", e4.getMessage());
		
		Exception e5 = assertThrows(IllegalArgumentException.class, () -> new Command(CommandValue.REJECT, ""));
		assertEquals("Invalid information.", e5.getMessage());
		
		Exception e6 = assertThrows(IllegalArgumentException.class, () -> new Command(CommandValue.TERMINATE, null));
		assertEquals("Invalid information.", e6.getMessage());
		
		Exception e7 = assertThrows(IllegalArgumentException.class, () -> new Command(CommandValue.TERMINATE, ""));
		assertEquals("Invalid information.", e7.getMessage());
		
		Exception e8 = assertThrows(IllegalArgumentException.class, () -> new Command(CommandValue.RESUBMIT, "Reason"));
		assertEquals("Invalid information.", e8.getMessage());
		
		Exception e9 = assertThrows(IllegalArgumentException.class, () -> new Command(CommandValue.RETURN, "Reason"));
		assertEquals("Invalid information.", e9.getMessage());
		
		Exception e10 = assertThrows(IllegalArgumentException.class, () -> new Command(CommandValue.SCHEDULE, "Reason"));
		assertEquals("Invalid information.", e10.getMessage());
		
		Exception e11 = assertThrows(IllegalArgumentException.class, () -> new Command(CommandValue.PROCESS, "Reason"));
		assertEquals("Invalid information.", e11.getMessage());
		
		Exception e12 = assertThrows(IllegalArgumentException.class, () -> new Command(CommandValue.HIRE, "Reason"));
		assertEquals("Invalid information.", e12.getMessage());
	}

}
