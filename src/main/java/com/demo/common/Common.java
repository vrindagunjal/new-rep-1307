package com.demo.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Common {

	public static String readJsonFile(String input) {
		File json = null;
		if (input.equalsIgnoreCase("Create")) {
			
			System.out.println("Inuput:"+input);
			json = new File(com.demo.configuration.Configuration.jsonCreatePath);
		} else if (input.equalsIgnoreCase("Edit")) {
			System.out.println("Inuput:"+input);
			json = new File(com.demo.configuration.Configuration.jsonEditPath);
		}
		StringBuilder result = new StringBuilder();

		try {
			FileReader fileReader = new FileReader(json);
			int curChar = fileReader.read();
			//System.out.println("Test");
			while (curChar != -1) {

				result.append((char) curChar);
				curChar = fileReader.read();
			}
			// System.out.println("New: " + result);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Test:"+result.toString());
		return result.toString();
	}

}
