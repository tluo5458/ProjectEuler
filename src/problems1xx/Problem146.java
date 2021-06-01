package problems1xx;

import java.math.BigInteger;

import commonMethods.CMath;
import commonMethods.Timing;

public class Problem146 {
	public static BigInteger sumPattern(int limit) {
		BigInteger ret = BigInteger.ZERO;
		BigInteger[] test = {CMath.intToBI(1), CMath.intToBI(3), CMath.intToBI(7), CMath.intToBI(9), CMath.intToBI(13), CMath.intToBI(27)};
		BigInteger[] antiTest = {CMath.intToBI(19), CMath.intToBI(21)};
		// manually calculated mods lol, 0 mod 2, 1 or 2 mod 3, 0 mod 5, 3 or 4 mod 7, 1 3 4 9 10 or 12 mod 13.
		int[] mods = {10, 220, 290, 430, 550, 620, 640, 920, 1130, 1180, 1270, 1340, 1390, 1460, 1550, 1600, 1810, 2090, 2110, 2180, 2300, 2440, 2510, 2720};
		// 2730 = 2 * 3 * 5 * 7 * 13
		for (int i = 0; i <= limit / 2730; i++) {
			for (int j : mods) {
				int curr = 2730 * i + j;
				if (curr >= limit) {
					break;
				}
				BigInteger n = CMath.intToBI(curr);
				BigInteger nSq = n.pow(2);
				boolean good = true;
				for (BigInteger a : test) {
					if (!nSq.add(a).isProbablePrime(15)) {
						good = false;
						break;
					}
				}
				for (BigInteger a : antiTest) {
					if (nSq.add(a).isProbablePrime(15)) {
						good = false;
						break;
					}
				}
				if (good) {
					ret = ret.add(n);
				}
			}
		}
		return ret;
	}
	
	public static void main(String[] args) {
		// unfortunately slow but i don't see a good way of improving this without just checking through more primes and adding more to that mods list
		Timing t = new Timing();
		t.start();
		System.out.println(sumPattern(150000000));
		t.end();
	}
}
