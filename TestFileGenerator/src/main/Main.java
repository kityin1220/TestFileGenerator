package main;

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
		if (args.length != 3) {
			System.out.println("Incorrect Parameter. Please input Target File Name , No. of Rows and No of Columns");
			return;
		}
		String fileName = args[0];
		int row = Integer.parseInt(args[1]);
		int col = Integer.parseInt(args[2]);

		// Create File
		String fileSeparator = System.getProperty("file.separator");
		String relativeFilePath = "output";

		File testFile = new File(relativeFilePath + fileSeparator + fileName);
		if (testFile.createNewFile()) {
			System.out.println(relativeFilePath + fileSeparator + fileName + " is created.");
		} else {
			System.out.println(relativeFilePath + fileSeparator + fileName + " already existed.");
		}

		// Write File
		Instant start = Instant.now();
		String encoding = "UTF8";
		try (OutputStream os = new FileOutputStream(testFile);
				OutputStreamWriter osw = new OutputStreamWriter(os, encoding);
				BufferedWriter bf = new BufferedWriter(osw);) {
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					bf.write("test" + j + (j == col ? "" : "\t"));
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
