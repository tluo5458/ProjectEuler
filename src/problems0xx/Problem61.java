package problems0xx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import commonMethods.CMath;

public class Problem61 {
	public static ArrayList<Integer> generateAll(int numSides, int numDigs) {
		int lower = (int) Math.ceil((Math.sqrt(Math.pow(numSides - 4, 2) + 8 * Math.pow(10, numDigs - 1) * (numSides - 2)) + numSides - 4) / (2 * numSides - 4));
		ArrayList<Integer> ret = new ArrayList<Integer>();
		while (true) {
			int curr = CMath.polygonalNumber(lower, numSides);
			if (Integer.toString(curr).length() > numDigs) {
				break;
			}
			ret.add(curr);
			lower++;
		}
		return ret;
	}
	
	public static ArrayList<ArrayList<HashMap<Integer, ArrayList<Integer>>>> cyclics() {
		ArrayList<ArrayList<Integer>> allPolygonals = new ArrayList<ArrayList<Integer>>();
		for (int i = 3; i < 9; i++) {
			allPolygonals.add(generateAll(i, 4));
		}
		ArrayList<ArrayList<HashMap<Integer, ArrayList<Integer>>>> cyclics = new ArrayList<ArrayList<HashMap<Integer, ArrayList<Integer>>>>();
		for (int i = 0; i < 6; i++) {
			ArrayList<HashMap<Integer, ArrayList<Integer>>> fromISides = new ArrayList<HashMap<Integer, ArrayList<Integer>>>();
			for (int j = 0; j < 6; j++) {
				if (j == i) {
					fromISides.add(new HashMap<Integer, ArrayList<Integer>>());
					continue;
				}
				HashMap<Integer, ArrayList<Integer>> iToJ = new HashMap<Integer, ArrayList<Integer>>();
				for (Integer k : allPolygonals.get(i)) {
					String end = Integer.toString(k).substring(2, 4);
					ArrayList<Integer> found = new ArrayList<Integer>();
					for (Integer l : allPolygonals.get(j)) {
						if (end.equals(Integer.toString(l).substring(0, 2))) {
							found.add(l);
						}
					}
					iToJ.put(k, found);
				}
				fromISides.add(iToJ);
			}
			cyclics.add(fromISides);
		}
		return cyclics;
	}
	
	public static int cycleSum() {
		ArrayList<ArrayList<HashMap<Integer, ArrayList<Integer>>>> cyclics = cyclics();
		ArrayList<ArrayList<Integer>> allPolygonal = new ArrayList<ArrayList<Integer>>();
		HashSet<Integer> perms = CMath.permutations(12345);
		for (int i = 0; i < 6; i++) {
			allPolygonal.add(generateAll(i + 3, 4));
		}
		for (Integer perm : perms) {
			int[] permChars = new int[5];
			for (int i = 0; i < 5; i++) {
				permChars[i] = Integer.toString(perm).charAt(i) - 49;
			}
			for (int i1 : allPolygonal.get(5)) {
				for (int i2 : cyclics.get(5).get(permChars[0]).get(i1)) {
					for (int i3 : cyclics.get(permChars[0]).get(permChars[1]).get(i2)) {
						for (int i4 : cyclics.get(permChars[1]).get(permChars[2]).get(i3)) {
							for (int i5 : cyclics.get(permChars[2]).get(permChars[3]).get(i4)) {
								for (int i6 : cyclics.get(permChars[3]).get(permChars[4]).get(i5)) {
									if (cyclics.get(permChars[4]).get(5).get(i6).contains(i1)) {
										System.out.println(perm + " " + i1 + " " + i2 + " " + i3 + " " + i4 + " " + i5 + " " + i6);
										return i1 + i2 + i3 + i4 + i5 + i6;
									}
								}
							}
						}
					}
				}
			}
		}
		
		
		
		return 0;
	}
	
	public static void main(String[] args) {
		System.out.println(cycleSum());
	}
}
