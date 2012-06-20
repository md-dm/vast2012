package com.md.dm.infovis.vast.process;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class DataProcessor {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		DataProcessor dataProcessor = new DataProcessor();

		long startTime = System.currentTimeMillis();
		dataProcessor.process();
		long finishTime = System.currentTimeMillis();

		String diff = dataProcessor.millisToShortDHMS(finishTime - startTime);
		System.out.println(diff);
	}

	private int process() throws Exception {
		Scanner scanner = new Scanner(new FileInputStream(
				"/Users/diego/Documents/Maestria/VI/vast2012/metaDB-csv-3-7/metaStatus-3-7.csv"),
				"UTF-8");

		try {
			System.out.println(scanner.nextLine());
			int i = 0;
			while (scanner.hasNextLine()) {
				i++;
				String line = scanner.nextLine();
				String[] data = line.split(",");

				if (data.length != 6) {
					throw new RuntimeException("Invalid parameters");
				}
			}

			return i;
		} finally {
			scanner.close();
		}
	}

	/**
	 * converts time (in milliseconds) to human-readable format "<dd:>hh:mm:ss"
	 */
	public String millisToShortDHMS(long duration) {
		String res = "";
		long days = TimeUnit.MILLISECONDS.toDays(duration);
		long hours = TimeUnit.MILLISECONDS.toHours(duration)
				- TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(duration));
		long minutes = TimeUnit.MILLISECONDS.toMinutes(duration)
				- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration));
		long seconds = TimeUnit.MILLISECONDS.toSeconds(duration)
				- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration));
		if (days == 0) {
			res = String.format("%02d:%02d:%02d", hours, minutes, seconds);
		} else {
			res = String.format("%dd%02d:%02d:%02d", days, hours, minutes, seconds);
		}
		return res;
	}
}
