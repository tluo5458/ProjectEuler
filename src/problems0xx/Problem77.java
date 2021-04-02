package problems0xx;

import java.util.ArrayList;

import commonMethods.CMath;

public class Problem77 {
	public static int numSums(int target, ArrayList<Integer> nums) {
		int[] ways = new int[target + 1];
		ways[0] = 1;
		for (int i = 0; i < nums.size(); i++) {
			for (int j = nums.get(i); j <= target; j++) {
				ways[j] += ways[j - nums.get(i)];
			}
		}
		return ways[target];
	}
	
	public static int atLeastPrimeSums(int limit) {
		ArrayList<Integer> primes = CMath.primesUnder((int) (10 * Math.log(limit) / Math.log(2)));
		int target = 2;
		System.out.println(primes.get(primes.size() - 1));
		while (true) {
			if (numSums(target, primes) > limit) {
				return target;
			}
			target++;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(atLeastPrimeSums(5000));
	}
}
