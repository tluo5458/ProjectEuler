package problems0xx;
import java.util.ArrayList;
import java.util.HashMap;

import commonMethods.CMath;

public class Problem21 {
	private static int sumOfProperFactors(int num) {
		int total = 1;
		HashMap<Integer, Integer> primeCounts = CMath.primeCount(num);
		for (Integer i : primeCounts.keySet()) {
			total *= (Math.pow(i, primeCounts.get(i) + 1) - 1) / (i - 1);
		}
		return total - num;
	}
	
	public static ArrayList<Integer> amicableNums(int limit) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for (int i = 2; i <= limit; i++) {
			int sum = sumOfProperFactors(i);
			if (i != sum) {
				if (i == sumOfProperFactors(sum)) {
					ret.add(i);
				}
			}
		}
		return ret;
	}
	
	public static int sumAmicable(int limit) {
		int total = 0;
		for (Integer i : amicableNums(limit)) {
			total += i;
		}
		return total;
	}
	
	public static void main(String[] args) {
		System.out.println(amicableNums(9999));
		System.out.println(sumAmicable(9999));
	}
}
