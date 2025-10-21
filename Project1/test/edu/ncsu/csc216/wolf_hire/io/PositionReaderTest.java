package edu.ncsu.csc216.wolf_hire.io;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_hire.model.application.Application;
import edu.ncsu.csc216.wolf_hire.model.io.PositionReader;
import edu.ncsu.csc216.wolf_hire.model.manager.Position;

class PositionReaderTest {
	/** This is the file path for the positions1 valid test file */
	private final String validTestFile = "test-files/positions1.txt";
	
	@Before
	public void setUp() throws Exception {
		//Reset course_records.txt so that it's fine for other needed tests
				Path sourcePath = FileSystems.getDefault().getPath("test-files", "source.txt");
				Path destinationPath = FileSystems.getDefault().getPath("test-files", "positions1.txt");
				try {
					Files.deleteIfExists(destinationPath);
					Files.copy(sourcePath, destinationPath);
				} catch (IOException e) {
					fail("Unable to reset files");
				}
	}
	
	/**
	 * Test readPosition with a valid file
	 */
	@Test
	void testReadPositionValid() {
		Application.setCounter(1);
		ArrayList<Position> positions = PositionReader.readPositionFile(validTestFile);
		assertEquals(1, positions.size());
		
		Position p1 = new Position("CSC 216 PTF", 12, 15);
		Application a1 = new Application(2, "Submitted", "Carol", "Schmidt", "cschmid", null, null);
		Application a2 = new Application(3, "Rejected", "Kathleen", "Gillespie", "kgilles", null, "Incomplete");
		Application a3 = new Application(4, "Hired", "Fiona", "Rosario", "frosari", "sesmith5", null);
		Application a4 = new Application(7, "Inactive", "Deanna", "Sanders", "dsander", "sesmith5", "Completed");
		Application a5 = new Application(8, "Interviewing", "Benjamin", "Nieves", "bmnieves", "sesmith5", null);
		Application a6 = new Application(11, "Processing", "Quemby", "Mullen", "qmullen", "sesmith5", null);
		
		assertEquals(p1.getPositionName(), positions.get(0).getPositionName());
		assertEquals(p1.getHoursPerWeek(), positions.get(0).getHoursPerWeek());
		assertEquals(p1.getPayRate(), positions.get(0).getPayRate());
		
		assertEquals(a1.getId(), positions.get(0).getApplications().get(0).getId());
		assertEquals(a1.getState(), positions.get(0).getApplications().get(0).getState());
		assertEquals(a1.getFirstName(), positions.get(0).getApplications().get(0).getFirstName());
		assertEquals(a1.getSurname(), positions.get(0).getApplications().get(0).getSurname());
		assertEquals(a1.getUnityId(), positions.get(0).getApplications().get(0).getUnityId());
		assertEquals(a1.getReviewer(), positions.get(0).getApplications().get(0).getReviewer());
		assertEquals(a1.getNote(), positions.get(0).getApplications().get(0).getNote());
		
		assertEquals(a2.getId(), positions.get(0).getApplications().get(1).getId());
		assertEquals(a2.getState(), positions.get(0).getApplications().get(1).getState());
		assertEquals(a2.getFirstName(), positions.get(0).getApplications().get(1).getFirstName());
		assertEquals(a2.getSurname(), positions.get(0).getApplications().get(1).getSurname());
		assertEquals(a2.getUnityId(), positions.get(0).getApplications().get(1).getUnityId());
		assertEquals(a2.getReviewer(), positions.get(0).getApplications().get(1).getReviewer());
		assertEquals(a2.getNote(), positions.get(0).getApplications().get(1).getNote());
		
		assertEquals(a3.getId(), positions.get(0).getApplications().get(2).getId());
		assertEquals(a3.getState(), positions.get(0).getApplications().get(2).getState());
		assertEquals(a3.getFirstName(), positions.get(0).getApplications().get(2).getFirstName());
		assertEquals(a3.getSurname(), positions.get(0).getApplications().get(2).getSurname());
		assertEquals(a3.getUnityId(), positions.get(0).getApplications().get(2).getUnityId());
		assertEquals(a3.getReviewer(), positions.get(0).getApplications().get(2).getReviewer());
		assertEquals(a3.getNote(), positions.get(0).getApplications().get(2).getNote());

		assertEquals(a4.getId(), positions.get(0).getApplications().get(3).getId());
		assertEquals(a4.getState(), positions.get(0).getApplications().get(3).getState());
		assertEquals(a4.getFirstName(), positions.get(0).getApplications().get(3).getFirstName());
		assertEquals(a4.getSurname(), positions.get(0).getApplications().get(3).getSurname());
		assertEquals(a4.getUnityId(), positions.get(0).getApplications().get(3).getUnityId());
		assertEquals(a4.getReviewer(), positions.get(0).getApplications().get(3).getReviewer());
		assertEquals(a4.getNote(), positions.get(0).getApplications().get(3).getNote());
		
		assertEquals(a5.getId(), positions.get(0).getApplications().get(4).getId());
		assertEquals(a5.getState(), positions.get(0).getApplications().get(4).getState());
		assertEquals(a5.getFirstName(), positions.get(0).getApplications().get(4).getFirstName());
		assertEquals(a5.getSurname(), positions.get(0).getApplications().get(4).getSurname());
		assertEquals(a5.getUnityId(), positions.get(0).getApplications().get(4).getUnityId());
		assertEquals(a5.getReviewer(), positions.get(0).getApplications().get(4).getReviewer());
		assertEquals(a5.getNote(), positions.get(0).getApplications().get(4).getNote());
		
		assertEquals(a6.getId(), positions.get(0).getApplications().get(5).getId());
		assertEquals(a6.getState(), positions.get(0).getApplications().get(5).getState());
		assertEquals(a6.getFirstName(), positions.get(0).getApplications().get(5).getFirstName());
		assertEquals(a6.getSurname(), positions.get(0).getApplications().get(5).getSurname());
		assertEquals(a6.getUnityId(), positions.get(0).getApplications().get(5).getUnityId());
		assertEquals(a6.getReviewer(), positions.get(0).getApplications().get(5).getReviewer());
		assertEquals(a6.getNote(), positions.get(0).getApplications().get(5).getNote());
	}
	
	/**
	 * Test read file not found
	 */
	@Test
	void testInvalidFile() {
		Application.setCounter(1);
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> PositionReader.readPositionFile("test-files/skibidi.txt"));
		assertEquals("Unable to load file test-files/skibidi.txt", e1.getMessage());
	}
	
	/**
	 * Tests the default constructor
	 */
	@Test
	void testConstructor() {
		PositionReader reader = new PositionReader();
		assertEquals(0, reader.getClass().getDeclaredFields().length);
	}
}