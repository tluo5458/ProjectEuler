package problems0xx;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem82 {
	public static int[][] parseFile() {
		int[][] ret = new int[80][80];
		int currRow = 0;
		try {
			File txt = new File("MiscFiles/p081_matrix.txt");
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(txt);
			while (scan.hasNextLine()) {
				String[] line = scan.nextLine().split(",");
				for (int i = 0; i < 80; i++) {
					ret[currRow][i] = Integer.parseInt(line[i]);
				}
				currRow++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("oops");
		}
		return ret;
	}
	
	public static void main(String[] args) {
		
	}
}
