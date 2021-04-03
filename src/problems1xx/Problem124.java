package problems1xx;

import java.util.ArrayList;
import java.util.Collections;

import graphs.CompPair;

public class Problem124 {
	public static ArrayList<CompPair<Integer, Integer>> sortedRad(int limit) {
		ArrayList<CompPair<Integer, Integer>> rads = new ArrayList<CompPair<Integer, Integer>>();
		for (int i = 1; i <= limit; i++) {
			rads.add(new CompPair<Integer, Integer>(2 - (i % 2), i));
		}
		for (int i = 2; i < limit; i += 2) {
			if (rads.get(i).getLeft() == 1) {
				for (int j = i; j < limit; j += (i + 1)) {
					rads.set(j, new CompPair<Integer, Integer>(rads.get(j).getLeft() * (i + 1), rads.get(j).getRight()));
				}
			}
		}
		Collections.sort(rads);
		return rads;
	}
	
	public static int e(int n, int k) {
		return sortedRad(n).get(k - 1).getRight();
	}
	
	public static void main(String[] args) {
		System.out.println(e(100000, 10000));
	}
}
