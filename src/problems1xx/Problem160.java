package problems1xx;

import java.math.BigInteger;

import commonMethods.CMath;
import commonMethods.Timing;

public class Problem160 {
	public static int f(long n) {
		// 499993
		int expMod = 499993;
		long curr = n;
		long remaining = n;
		long p5 = 0;
		while (curr > 0) {
			curr /= 5;
			p5 += curr;
		}
		BigInteger mod = BigInteger.TEN.pow(5);
		BigInteger fact = BigInteger.ONE;
		
		int expMax = (int) (p5 / expMod);
		if (expMax > 0) {
			fact = f(500000, expMod).modPow(CMath.intToBI(expMax), mod);
		}
		p5 = p5 % expMod;
		remaining -= 500000 * expMax;
		if (remaining > 500000) {
			fact = fact.multiply(f(500000, p5)).mod(mod);
			remaining -= 500000;
			fact = fact.multiply(f(500000, 0).modPow(CMath.intToBI((int) (remaining / 500000)), mod)).mod(mod);
			remaining %= 500000;
			if (remaining > 0) {
				fact = fact.multiply(f(remaining, 0)).mod(mod);
			}
		} else {
			fact = fact.multiply(f(remaining, p5)).mod(mod);
		}
		
//		if (n <= 500000) {
//			
//		} else {
//			
//		}
		return (int) fact.longValue();
	}
	
	public static int bruteForceF(long n) {
		long curr = n;
		long p5 = 0;
		while (curr > 0) {
			curr /= 5;
			p5 += curr;
		}
		BigInteger mod = BigInteger.TEN.pow(5);
		BigInteger fact = BigInteger.ONE;
		for (int i = 1; i <= n; i++) {
			int mult = i;
			if (p5 > 0) {
				int v2 = 0;
				while (mult % 2 == 0) {
					mult /= 2;
					v2++;
				}
				p5 -= v2;
				if (p5 < 0) {
					for (long j = p5; j < 0; j++) {
						mult *= 2;
					}
				}
			}
			while (mult % 5 == 0) {
				mult /= 5;
			}
			fact = fact.multiply(CMath.intToBI(mult)).mod(mod);
		}
		return (int) fact.longValue();
	}
	
	public static BigInteger f(long n, long p5) {
		long left = p5;
		BigInteger mod = BigInteger.TEN.pow(5);
		BigInteger fact = BigInteger.ONE;
		for (int i = 1; i <= n; i++) {
			int mult = i;
			if (left > 0) {
				int v2 = 0;
				while (mult % 2 == 0) {
					mult /= 2;
					v2++;
				}
				left -= v2;
				if (left < 0) {
					for (long j = left; j < 0; j++) {
						mult *= 2;
					}
				}
			}
			while (mult % 5 == 0) {
				mult /= 5;
			}
			fact = fact.multiply(CMath.intToBI(mult)).mod(mod);
		}
		return fact;
	}
	
	// this DOES NOT WORK!!
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(f(2000000));
		System.out.println(bruteForceF(2000000));
		System.out.println(f(2000000, 499993));
		System.out.println(f(500000, 499993).multiply(f(500000, 0).modPow(CMath.intToBI(3), CMath.intToBI(100000))));
		t.end();
	}
}
