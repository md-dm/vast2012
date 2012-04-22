/**
 * 
 */
package com.md.dm.vi.vast.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author diego
 * 
 */
public class ReadData {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		StringBuilder text = new StringBuilder();
		String NL = System.getProperty("line.separator");
		Scanner scanner = new Scanner(new FileInputStream("/Users/diego/Documents/Maestria/VI/vast2012/metaDB-csv-3-7/meta-3-7.csv"), "UTF-8");
		try {
			while (scanner.hasNextLine()) {
				//text.append(scanner.nextLine() + NL);
				System.out.println(scanner.nextLine());
			}
		} finally {
			scanner.close();
		}

	}

}
