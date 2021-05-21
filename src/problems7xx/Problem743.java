package problems7xx;

import java.math.BigInteger;

import commonMethods.CMath;
import commonMethods.Timing;

public class Problem743 {
	// A(n, an) = sum from k = 0 to n/2 of (n choose k)(n - k choose k)2^(a(n - 2k))	
	public static BigInteger a(int n, int a, BigInteger p) {
		// 2^(an), will get divided by 2^(2a) every step
		BigInteger pow2 = (new BigInteger("2")).modPow(CMath.intToBI(n), p).modPow(CMath.intToBI(a), p);
		
		// (n choose k) * (n - k choose k), k starts at 0 and increments
		BigInteger combo = BigInteger.ONE;
		
		// inverse of 4 mod p
		BigInteger dec = CMath.modInvP(new BigInteger("2"), p).modPow(CMath.intToBI(2 * a), p);
		
		// final total
		BigInteger tot = pow2.multiply(combo).mod(p);
		for (int i = 1; i <= n/2; i++) {
			pow2 = pow2.multiply(dec).mod(p);
			combo = combo.multiply(CMath.intToBI(n - 2 * i + 2)).multiply(CMath.intToBI(n -  2 * i + 1)).multiply(CMath.modInvP(CMath.intToBI(i), p).pow(2)).mod(p);
			tot = tot.add(pow2.multiply(combo)).mod(p);
		}
		return tot;
	}
	
	public static void main(String[] args) {
		BigInteger p = new BigInteger("1000000007");
		Timing t = new Timing();
		t.start();
		System.out.println(a(100000000, 100000000, p));
		t.end();
	}
}
