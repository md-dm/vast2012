package com.md.dm.vi.vast;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ReadFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		final long startTime = System.nanoTime();
		final long endTime;
		try {
			methodToTime();
		} finally {
			endTime = System.nanoTime();
		}
		final long duration = endTime - startTime;
		long seconds = TimeUnit.MILLISECONDS.toSeconds(duration)
				- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration));

		System.out.println("Total duration: " + seconds);
	}

	private static void methodToTime() throws Exception {
		Scanner scanner = new Scanner(new FileInputStream(
				"/Users/diego/Documents/Maestria/VI/vast2012/metaDB-csv-3-7/metaStatus-3-7.csv"),
				"UTF-8");

		System.out.println(scanner.nextLine());
		int line = 0;
		long startTime = System.nanoTime();
		long endTime;
		while (scanner.hasNextLine()) {
			scanner.nextLine();

			line++;

			if ((line % 10000000) == 0) {
				endTime = System.nanoTime();
				long duration = endTime - startTime;
				long seconds = TimeUnit.MILLISECONDS.toSeconds(duration)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration));

				System.out.println("Leidos !" + line + ". Duration: " + seconds);
				startTime = System.nanoTime();
			}
		}

		endTime = System.nanoTime();
		System.out.println("Leidos !" + line + ". Duration: " + (endTime - startTime));

	}

}
