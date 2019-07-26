package forGeneralSQLStatements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.time.Duration;
import java.time.Instant;

public class Main {

	public static void main(String[] args) throws IOException {
		generateSqlFile(args);
	}

	public static File generateFile(String fileName) throws IOException {
		// Create File
		String fileSeparator = System.getProperty("file.separator");
		String relativeFilePath = "output";
		File testFile = new File(relativeFilePath + fileSeparator + fileName);
		if (testFile.createNewFile()) {
			System.out.println(relativeFilePath + fileSeparator + fileName + " is created.");
		} else {
			System.out.println(relativeFilePath + fileSeparator + fileName + " already existed.");
		}
		return testFile;
	}

	public static void generateSqlFile(String[] args) throws IOException {
		String fileName = args[0];
		int begin = Integer.parseInt(args[1]);
		int end = Integer.parseInt(args[2]);

		// Create File
		File testFile = generateFile(fileName);

		// Write File
		Instant startTime = Instant.now();
		String encoding = "UTF8";
		try (OutputStream os = new FileOutputStream(testFile);
				OutputStreamWriter osw = new OutputStreamWriter(os, encoding);
				BufferedWriter bf = new BufferedWriter(osw);) {
			bf.write(
					"INSERT INTO `ptqnruser`.`target_user` (`project_id`,`easy_id`,`seg_reservation_id`,`insert_time`) VALUES"); // INSERT
			// STATEMENT
			bf.newLine();
			bf.flush();
			for (int i = begin; i <= end; i++) {
				bf.write(String.format("(5,%d,1,'2019/1/1 0:00:00')" + (i == end ? ";" : ","), i));
				bf.newLine();
				bf.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("File writing is done...");
		}
		Instant endTime = Instant.now();
		Duration timeElapsed = Duration.between(startTime, endTime);
		System.out.println("Time taken: " + timeElapsed.toMillis() + " milliseconds");

	}

}
