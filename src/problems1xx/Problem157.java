package problems1xx;

import java.util.HashMap;

import commonMethods.CMath;

public class Problem157 {
	public static int numSols(int n) {
		int tot = 0;
		for (int i = 0; i < 2 * n; i++) {
			for (int j = 0; j < 2 * n - i; j++) {
				long curr = (long) (Math.pow(2, i) * Math.pow(5, j) + Math.pow(10, n));
				long counter = (long) (Math.pow(2, 2 * n - i) * Math.pow(5, 2 * n - j) + Math.pow(10, n));
				int gcd = (int) CMath.gcd(curr, counter);
				HashMap<Integer, Integer> primeCount = CMath.primeCount(gcd);
				int sols = 1;
				for (int exp : primeCount.values()) {
					sols *= (exp + 1);
				}
				tot += sols;
			}
		}
		for (int i = 2 * n; i >= n; i--) {
			int j = 2 * n - i;
			long curr = (long) (Math.pow(2, i) * Math.pow(5, j) + Math.pow(10, n));
			long counter = (long) (Math.pow(2, 2 * n - i) * Math.pow(5, 2 * n - j) + Math.pow(10, n));
			int gcd = (int) CMath.gcd(curr, counter);
			HashMap<Integer, Integer> primeCount = CMath.primeCount(gcd);
			int sols = 1;
			for (int exp : primeCount.values()) {
				sols *= (exp + 1);
			}
			tot += sols;
		}
		return tot;
	}
	
	public static int totSols(int upTo) {
		int tot = 0;
		for (int i = 1; i <= upTo; i++) {
			tot += numSols(i);
		}
		return tot;
	}
	
	public static void main(String[] args) {
		System.out.println(totSols(9));
	}
}
