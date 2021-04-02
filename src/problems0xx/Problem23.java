package problems0xx;
import java.util.HashMap;
import java.util.HashSet;

import commonMethods.CMath;

public class Problem23 {
	private static int sumOfProperFactors(int num) {
		int total = 1;
		HashMap<Integer, Integer> primeCounts = CMath.primeCount(num);
		for (Integer i : primeCounts.keySet()) {
			total *= (Math.pow(i, primeCounts.get(i) + 1) - 1) / (i - 1);
		}
		return total - num;
	}
	
	private static boolean isAbundant(int num) {
		return num < sumOfProperFactors(num);
	}
	
	private static HashSet<Integer> abundants(int limit) {
		HashSet<Integer> ret = new HashSet<Integer>();
		for (int i = 1; i <= limit; i++) {
			if (isAbundant(i)) {
				ret.add(i);
			}
		}
		return ret;
	}
	
	public static int sumOfNonAbundantSums(int limit) {
		HashSet<Integer> abundants = abundants(limit);
		HashSet<Integer> sums = new HashSet<Integer>();
		for (Integer i : abundants) {
			for (Integer j : abundants) {
				if (i + j <= limit) {
					sums.add(i + j);
				}
			}
		}
		int total = 0;
		for (Integer i : sums) {
			total += i;
		}
		return limit * (limit + 1) / 2 - total;
	}
	
	public static void main(String[] args) {
		System.out.println(sumOfNonAbundantSums(28123));
	}
}
