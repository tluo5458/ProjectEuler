package problems0xx;

import java.util.ArrayList;
import java.util.Arrays;

import commonMethods.CMath;

public class Problem70 {
	public static boolean isPermutation(int a, int b) {
		char[] aChars = Integer.toString(a).toCharArray();
		char[] bChars = Integer.toString(b).toCharArray();
		Arrays.sort(aChars);
		Arrays.sort(bChars);
		if (Arrays.equals(aChars, bChars)) {
			return true;
		}
		return false;
	}
	
	// notice that minimum is almost certainly a semiprime with two large primes
	public static int minPhiRatioPerm(int limit) {
		double minRatio = 2;
		int attained = 0;
		ArrayList<Integer> primes = CMath.primesUnder((int) (2 * Math.sqrt(limit)));
		System.out.println("a");
		int max = (int) Math.sqrt(limit);
		int index = 0;
		for (int i = 0; i < primes.size(); i++) {
			if (primes.get(i) > max) {
				index = i - 1;
				break;
			}
		}
		for (int i = index; i >= 0; i--) {
			for (int j = 0; j < primes.size(); j++) {
				int n = primes.get(i) * primes.get(j);
				if (n >= limit) {
					break;
				}
				int phi = n - primes.get(i) - primes.get(j) + 1;
				if (isPermutation(n, phi)) {
					System.out.println(n + " = " + primes.get(i) + " * " + primes.get(j));
					double ratio = (double) n / (double) phi;
					if (ratio < minRatio) {
						minRatio = ratio;
						attained = n;
					}
				}
			}
		}
		return attained;
	}
	
	public static void main(String[] args) {
		System.out.println(minPhiRatioPerm(10000000));
	}
}
