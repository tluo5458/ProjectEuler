package problems0xx;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import commonMethods.CMath;

public class Problem89 {
	public static int numSaved(String s) {
		return s.length() - CMath.toRoman(CMath.romanToInt(s)).length();
	}
	
	public static int totalSaved() {
		int total = 0;
		try {
			File txt = new File("MiscFiles/p089_roman.txt");
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(txt);
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				total += numSaved(line);
			}
		} catch (FileNotFoundException e) {
			System.out.println("oops");
		}
		return total;
	}
	
	public static void main(String[] args) {
		System.out.println(totalSaved());
	}
}
