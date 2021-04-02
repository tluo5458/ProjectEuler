package problems0xx;

import java.util.ArrayList;
import java.util.HashSet;

import commonMethods.CMath;

public class Problem90 {
	public static ArrayList<ArrayList<Integer>> allCubes() {
		HashSet<ArrayList<Integer>> combos = CMath.combinations(10, 6);
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		for (ArrayList<Integer> cube : combos) {
			ArrayList<Integer> add = new ArrayList<Integer>();
			for (int i = 0; i < 9; i++) {
				add.add(0);
			}
			for (int i = 0; i < 6; i++) {
				int dig = cube.get(i) - 1;
				if (dig == 9) {
					add.set(6, add.get(6) + 1);
				} else {
					add.set(dig, add.get(dig) + 1);
				}
			}
			ret.add(add);
		}
		return ret;
	}
	
	// targets = strings of length 2, each char is a digit, all 9's are replaced by 6
	// cubes are arraylists of length 9, index i represents # of faces i is on
	// 6 and 9 are equal
	public static boolean isGood(ArrayList<Integer> cube1, ArrayList<Integer> cube2, String[] targets) {
		for (String i : targets) {
			int a = i.charAt(0) - 48;
			int b = i.charAt(1) - 48;
			if ((cube1.get(a) == 0 || cube2.get(b) == 0) && (cube1.get(b) == 0 || cube2.get(a) == 0)) {
				return false;
			}
		}
		return true;
	}
	
	public static String cubeToString(ArrayList<Integer> cube) {
		char[] chars = new char[6];
		int ind = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < cube.get(i); j++) {
				chars[ind] = (char) (i + 48);
				ind++;
			}
		}
		return String.valueOf(chars);
	}
	
	public static int numGoodCubePairs(String[] targets) {
		ArrayList<ArrayList<Integer>> cubes = allCubes();
		int tot = 0;
		for (int i = 0; i < cubes.size(); i++) {
			for (int j = i; j < cubes.size(); j++) {
				if (isGood(cubes.get(i), cubes.get(j), targets)) {
//					System.out.println(cubeToString(cubes.get(i)) + " " + cubeToString(cubes.get(j)));
					tot += 1;
				}
			}
		}
		return tot;
	}
	
	public static void main(String[] args) {
		String[] squares = new String[] {"01", "04", "06", "16", "25", "36", "46", "18"};
		System.out.println(numGoodCubePairs(squares));
	}
}
