package problems0xx;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem99 {
	public static double getLog(int a, int b) {
		return b * Math.log(a);
	}
	
	public static int getMaxIndex() {
		int currInd = 1;
		double max = 0;
		int attained = 0;
		try {
			File txt = new File("MiscFiles/p099_base_exp.txt");
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(txt);
			while (scan.hasNextLine()) {
				String[] line = scan.nextLine().split(",");
				double log = getLog(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
				if (log > max) {
					attained = currInd;
					max = log;
				}
				currInd++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("oops");
		}
		return attained;
	}
	
	public static void main(String[] args) {
		System.out.println(getMaxIndex());
	}
}
