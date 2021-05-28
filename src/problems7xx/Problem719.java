package problems7xx;

import java.util.ArrayList;
import java.util.TreeSet;

import commonMethods.Timing;

public class Problem719 {
	// is n^2 an S-number
	public static boolean isS(int n) {
		String sq = Long.toString((long) n * n);
		int len = Integer.toString(n).length();
		ArrayList<TreeSet<Integer>> found = new ArrayList<TreeSet<Integer>>();
		for (int i = 0; i < sq.length(); i++) {
			TreeSet<Integer> curr = new TreeSet<Integer>();
			if (i < len) {
				int consider = Integer.valueOf(sq.substring(0, i + 1));
				if (consider <= n) {
					curr.add(Integer.valueOf(sq.substring(0, i + 1)));
				}
			}
			for (int j = 1; j <= Math.min(i, len); j++) {
				int add = Integer.valueOf(sq.substring(i - j + 1, i + 1));
				if (add > n) {
					break;
				}
				for (int k : found.get(i - j)) {
					if (k + add <= n) {
						curr.add(k + add);
					} else {
						break;
					}
				}
			}
			found.add(curr);
		}
		return found.get(sq.length() - 1).contains(n);
	}
	
	public static long T(long limit) {
		long tot = 0;
		// ignore n = n^2 case (i = 1)
		for (int i = 2; i <= Math.sqrt(limit); i++) {
			if (isS(i)) {
				tot += ((long) i * i);
			}
		}
		return tot;
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(T(1000000000000L));
		t.end();
	}
}
