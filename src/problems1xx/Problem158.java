package problems1xx;

import java.math.BigInteger;

import commonMethods.CMath;
import commonMethods.Timing;

public class Problem158 {
	// epic closed form of p(n)
	// (26 choose n) * (2^n - n - 1)
	public static BigInteger p(int n) {
		return CMath.intToBI(2).pow(n).subtract(CMath.intToBI(n)).subtract(BigInteger.ONE).multiply(CMath.comboBI(26, n));
	}
	
	public static BigInteger maxP() {
		BigInteger max = BigInteger.ZERO;
		for (int i = 1; i <= 26; i++) {
			BigInteger p = p(i);
			if (p.compareTo(max) > 0) {
				max = p;
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(maxP());
		t.end();
	}
}
