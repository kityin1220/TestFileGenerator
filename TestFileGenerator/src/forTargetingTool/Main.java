package forTargetingTool;

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
		if (args.length == 1) {
			generateEndFile(args);

		} else if (args.length == 3) {
			generateTsvFile(args);

		} else {
			System.out.println("Incorrect Parameter. Please input Target File Name , No. of Rows and No of Columns");
			return;
		}

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

	public static void generateEndFile(String[] args) throws IOException {
		String fileName = args[0];
		// Create File
		generateFile(fileName);
	}

	public static void generateTsvFile(String[] args) throws IOException {
		String fileName = args[0];
		int row = Integer.parseInt(args[1]);
		int col = Integer.parseInt(args[2]);

		// Create File
		File testFile = generateFile(fileName);

		// Write File
		Instant start = Instant.now();
		String encoding = "UTF8";
		try (OutputStream os = new FileOutputStream(testFile);
				OutputStreamWriter osw = new OutputStreamWriter(os, encoding);
				BufferedWriter bf = new BufferedWriter(osw);) {
			for (int i = 0; i < row; i++) {
				bf.write("" + (long) (Math.random() * 1000000000 + 1) + "\t"); // Easy ID
//				bf.write("" + (long) (1000000000 + 900000 + i) + "\t"); // Easy ID
				for (int j = 0; j < col; j++) {
					bf.write("test" + j + (j == col - 1 ? "" : "\t"));
				}
				bf.newLine();
				bf.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("File writing is done...");
		}
		Instant end = Instant.now();
		Duration timeElapsed = Duration.between(start, end);
		System.out.println("Time taken: " + timeElapsed.toMillis() + " milliseconds");

	}

}
