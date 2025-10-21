package edu.ncsu.csc216.wolf_hire.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_hire.model.application.Application;
import edu.ncsu.csc216.wolf_hire.model.io.PositionWriter;
import edu.ncsu.csc216.wolf_hire.model.manager.Position;
/**
 * The PositionWriter Class tests PositionWriter.java, going for a minimum of 80% code coverage with my tests
 * @author John Eakes
 */
class PositionWriterTest {

	/**
	 * Tests writePositionsToFile
	 */
	@Test
	void testWritePositionsToFile() {
		Application.setCounter(1);
		List<Position> positions = new ArrayList<>();
		
		Position p1 = new Position("TA", 10, 10);
		Application a1 = new Application(1, "Submitted", "John", "Eakes", "jpeakes", null, null);
		Application a2 = new Application(2, "Reviewing", "Zach", "Newman", "zmnewman", "jpeakes", null);
		
		p1.addApplication(a1);
		p1.addApplication(a2);
		positions.add(p1);
		
		File testFile;
        try {
            testFile = File.createTempFile("testPositions", ".txt");
            testFile.deleteOnExit(); // Ensure cleanup

            // Write positions to file
            PositionWriter.writePositionsToFile(testFile.getAbsolutePath(), positions);

            // Read file contents
            List<String> lines = Files.readAllLines(testFile.toPath());

            // Expected output format
            assertEquals("# TA,10,10", lines.get(0));
            assertEquals("* 1,Submitted,John,Eakes,jpeakes,,", lines.get(1));
            assertEquals("* 2,Reviewing,Zach,Newman,zmnewman,jpeakes,", lines.get(2));

        } catch (IOException e) {
            fail("Unexpected IOException: " + e.getMessage());
        }
	}
		
        /**
         * Tests PositionWriter with multiple positions
         */
        @Test
        void testMultiplePositions() {
        	Application.setCounter(1);
    		List<Position> positions = new ArrayList<>();
    		
    		Position p1 = new Position("Position A", 18, 20);
    		Position p2 = new Position("Position B", 10, 12);
    		Position p3 = new Position("Position C", 10, 10);
    		Position p4 = new Position("Position D", 11, 11);
    		
    		Application a1 = new Application(2, "Submitted", "Carol", "Schmidt", "cschmid", null, null);
    		Application a2 = new Application(4, "Hired", "Fiona", "Rosario", "frosari", "sesmith5", null);
    		p1.addApplication(a1);
    		p1.addApplication(a2);
    		
    		Application a3 = new Application(7, "Inactive", "Deanna", "Sanders", "dsander", "tmbarnes", "Completed");
    		Application a4 = new Application(8, "Interviewing", "Benjamin", "Nieves", "bmnieves", "sesmith5", null);
    		Application a5 = new Application(11, "Processing", "Quemby", "Mullen", "qmullen", "sesmith5", null);
    		p2.addApplication(a3);
    		p2.addApplication(a4);
    		p2.addApplication(a5);
    		
    		Application a6 = new Application(3, "Rejected", "Kathleen", "Gillespie", "kgilles", null, "Incomplete");
    		p4.addApplication(a6);
		
    		positions.add(p1);
    		positions.add(p2);
    		positions.add(p3);
    		positions.add(p4);
    		File testFile;
            try {
            	testFile = File.createTempFile("actual_multiple_positionWriter", ".txt");

                // Write positions to file
                PositionWriter.writePositionsToFile(testFile.getAbsolutePath(), positions);

                // Read file contents
                List<String> lines = Files.readAllLines(testFile.toPath());
                
                assertEquals("# Position A,18,20", lines.get(0));
                assertEquals("* 2,Submitted,Carol,Schmidt,cschmid,,", lines.get(1));
                assertEquals("* 4,Hired,Fiona,Rosario,frosari,sesmith5,", lines.get(2));
                assertEquals("# Position B,10,12", lines.get(3));
                assertEquals("* 7,Inactive,Deanna,Sanders,dsander,tmbarnes,Completed", lines.get(4));
                assertEquals("* 8,Interviewing,Benjamin,Nieves,bmnieves,sesmith5,", lines.get(5));
                assertEquals("* 11,Processing,Quemby,Mullen,qmullen,sesmith5,", lines.get(6));
                assertEquals("# Position D,11,11", lines.get(7));
                assertEquals("* 3,Rejected,Kathleen,Gillespie,kgilles,,Incomplete", lines.get(8));
            } catch (IOException e) {
                fail("Unexpected IOException: " + e.getMessage());
            }
	}
        
        /**
    	 * Tests the default constructor
    	 */
    	@Test
    	void testConstructor() {
    		PositionWriter writer = new PositionWriter();
    		assertEquals(0, writer.getClass().getDeclaredFields().length);
    	}

}
