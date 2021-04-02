package problems0xx;

import java.util.ArrayList;
import java.util.TreeMap;


import commonMethods.CMath;

public class Problem75 {
	public static ArrayList<Integer> primitivePerimeters(int limit) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for (int i = 2; i <= Math.sqrt(limit); i += 2) {
			for (int j = 1; j <= Math.sqrt(limit); j += 2) {
				if (CMath.gcd(i, j) > 1) {
					continue;
				}
				int max = Math.max(i, j);
				int min = Math.min(i, j);
				if (j > i && 2 * j * (j + i) > limit) {
					break;
				}
				if (2 * max * (max + min) <= limit) {
					ret.add(2 * max * (max + min));
				}
			}
		}
		return ret;
	}
	
	public static TreeMap<Integer, Integer> possiblePerimeters(int limit) {
		TreeMap<Integer, Integer> ret = new TreeMap<Integer, Integer>();
		ArrayList<Integer> primitives = primitivePerimeters(limit);
		for (int i : primitives) {
			for (int j = 1; j <= limit / i; j++) {
				if (ret.containsKey(i * j)) {
					ret.put(i * j, ret.get(i * j) + 1);
				} else {
					ret.put(i * j, 1);
				}
			}
		}
		return ret;
	}
	
	public static int uniquePerimeters(int limit) {
		TreeMap<Integer, Integer> poss = possiblePerimeters(limit);
		int tot = 0;
		for (int i : poss.keySet()) {
			if (poss.get(i) == 1) {
				tot++;
			}
		}
		return tot;
	}
	
	public static void main(String[] args) {
		System.out.println(uniquePerimeters(1500000));
	}
}
