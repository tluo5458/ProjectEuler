package problems1xx;

import java.util.ArrayList;
import java.util.Collections;

import commonMethods.CMath;
import commonMethods.Timing;
import graphs.CompPair;

public class Problem127 {
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
	
	public static int[] rads(int limit) {
		int[] ret = new int[limit];
		for (int i = 0; i < limit; i++) {
			ret[i] = 2 - ((i + 1) % 2);
		}
		for (int i = 2; i < limit; i += 2) {
			if (ret[i] == 1) {
				for (int j = i; j < limit; j += (i + 1)) {
					ret[j] *= (i + 1);
				}
			}
		}
		return ret;
	}
	
	public static long abcHits(int maxC) {
		// yes i know this radical calculation is repeated, no i don't care i copied this from problem 124
		ArrayList<CompPair<Integer, Integer>> sorted = sortedRad(maxC - 1);
		int[] rads = rads(maxC - 1);
		long total = 0;
		// iterate over c, but only consider a for which rad(a) * rad(c) < c / 2 since rad(b) >= 2
		// particularly note that gcd(a, b) = gcd(a, c) = gcd(b, c)
		// if gcd(a, b) = 1, then rad(abc) = rad(a) * rad(b) * rad(c)
		for (int c = 3; c < maxC; c++) {
			int cRad = rads[c - 1];
			for (CompPair<Integer, Integer> a : sorted) {
				if (a.getLeft() * cRad > c / 2) {
					break;
				}
				int b = c - a.getRight();
				if (b <= a.getRight()) {
					continue;
				}
				if (CMath.gcd(a.getRight(), c) > 1) {
					continue;
				}
				// lol if you don't include the long cast you get int overflows
				if ((long) a.getLeft() * rads[b - 1] * cRad < c) {
					total += c;
				}
			}
		}
		return total;
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(abcHits(120000));
		t.end();
	}
}
