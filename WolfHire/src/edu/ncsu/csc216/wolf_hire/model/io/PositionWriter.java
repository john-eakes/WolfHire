package edu.ncsu.csc216.wolf_hire.model.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import edu.ncsu.csc216.wolf_hire.model.manager.Position;

/**
 * The PositionWriter Class writes positions and all of their respective applications to the provided file name.
 * @author John Eakes
 */
public class PositionWriter {

	/**
	 * This method writes a list of positions to a given file name
	 * @param fileName the name of the file that being written
	 * @param positions the list of positions being written
	 */
	public static void writePositionsToFile(String fileName, List<Position> positions) {
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(fileName));
			for(int i = 0; i < positions.size(); i++) {
				if(positions.get(i).getApplications().size() != 0) {
					writer.println("# " + positions.get(i).toString());
					for(int j = 0; j < positions.get(i).getApplications().size(); j++) {
						writer.println("* " + positions.get(i).getApplications().get(j).toString());
					}
				}
			}
			writer.close();
		} catch(IOException e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
	}
}
