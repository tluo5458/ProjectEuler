package problems0xx;

import java.util.ArrayList;
import java.util.HashSet;

import commonMethods.CMath;

public class Problem87 {
	public static ArrayList<Integer> primePowers(int limit, int pow) {
		ArrayList<Integer> ret = CMath.primesUnder((int) Math.pow(limit, 1.0 / pow));
		for (int i = 0; i < ret.size(); i++) {
			ret.set(i, (int) Math.pow(ret.get(i), pow));
		}
		return ret;
	}
	
	public static int numSums(int limit) {
		ArrayList<Integer> sq = primePowers(limit, 2);
		ArrayList<Integer> cu = primePowers(limit, 3);
		ArrayList<Integer> fo = primePowers(limit, 4);
		HashSet<Integer> found = new HashSet<Integer>();
		for (Integer i : sq) {
			for (Integer j : cu) {
				for (Integer k : fo) {
					int sum = i + j + k;
					if (sum < limit) {
						found.add(i + j + k);
					}
				}
			}
		}
		return found.size();
	}
	
	public static void main(String[] args) {
		System.out.println(numSums(50000000));
	}
}
