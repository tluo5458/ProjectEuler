package problems1xx;

import java.util.HashMap;

import commonMethods.CMath;

public class Problem108 {
	public static int numSols(int n) {
		HashMap<Integer, Integer> primes = CMath.primeCount(n);
		int ret = 1;
		for (int i : primes.values()) {
			ret *= (2 * i + 1);
		}
		return (ret + 1) / 2;
	}
	
	public static int firstAbove(int limit) {
		int curr = 2;
		while (numSols(curr) <= limit) {
			curr++;
		}
		return curr;
	}
	
	public static void main(String[] args) {
		long start = System.nanoTime();
		System.out.println(firstAbove(1000));
		long end = System.nanoTime();
		System.out.println((end - start) / 1000000.0 + "ms");
		// see problem 110 for a solution that's about 5000 times faster.
	}
}
