package problems1xx;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem102 {
	// return 2 * area of triangle bounded by coordinates in tri (stored as [x1, y1, x2, y2, x3, y3])
	// shoelace method
	public static int doubleArea(int[] tri) {
		int ret = 0;
		for (int i = 0; i < 6; i += 2) {
			ret += tri[i] * tri[(i + 3) % 6] - tri[i + 1] * tri[(i + 2) % 6];
		}
		return ret;
	}
	
	public static boolean containsOrigin(int[] tri) {
		int area = doubleArea(tri);
		double s = 1 / ((double) area) * (tri[1] * tri[4] - tri[0] * tri[5]);
		if (s < 0) {
			return false;
		}
		double t = 1 / ((double) area) * (tri[0] * tri[3] - tri[1] * tri[2]);
		if (t < 0) {
			return false;
		}
		if (s + t <= 1) {
			return true;
		}
		return false;
	}
	
	public static int numContainOrigin() {
		int count = 0;
		try {
			File txt = new File("MiscFiles/p102_triangles.txt");
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(txt);
			while (scan.hasNextLine()) {
				int[] coords = new int[6];
				String[] line = scan.nextLine().split(",");
				for (int i = 0; i < 6; i++) {
					coords[i] = Integer.parseInt(line[i]);
				}
				if (containsOrigin(coords)) {
					count++;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("oops");
		}
		return count;
	}
	
	public static void main(String[] args) {
		System.out.println(numContainOrigin());
	}
}
