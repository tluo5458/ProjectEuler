package problems0xx;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Problem83 {
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
	
	public static int minDists() {
		int[][] matrix = parseFile();
		int[][] dists = new int[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			dists[i][0] = matrix[i][0];
		}
		for (int j = 1; j < matrix[0].length; j++) {
			// go straight right
			for (int i = 0; i < matrix.length; i++) {
				dists[i][j] = dists[i][j - 1] + matrix[i][j];
			}
			// traverse down
			for (int i = 1; i < matrix.length; i++) {
				dists[i][j] = Math.min(dists[i - 1][j] + matrix[i][j], dists[i][j]);
			}
			// traverse up
			for (int i = matrix.length - 2; i >= 0; i--) {
				dists[i][j] = Math.min(dists[i + 1][j] + matrix[i][j], dists[i][j]);
			}
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < matrix.length; i++) {
			if (dists[i][matrix[0].length - 1] < min) {
				min = dists[i][matrix[0].length - 1];
			}
		}
		return min;
	}
	
	public static void main(String[] args) {
		System.out.println(minDists());
	}
}
