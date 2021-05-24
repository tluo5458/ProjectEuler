package problems1xx;

import commonMethods.CMath;
import commonMethods.Timing;

public class Problem139 {
	// euclid's formula, (m^2 - n^2, 2mn, m^2 + n^2) generates all primitive pythag triples, as long as m and n different parity and relatively prime
	public static long numTiles(long maxPerim) {
		double sqrt = Math.sqrt(maxPerim / 2.0);
		long ret = 0;
		for (long m = 2; m < sqrt; m++) {
			// n different parity as m
			for (long n = m % 2 + 1; n < m; n += 2) {
				if (CMath.gcd(m, n) > 1) {
					continue;
				}
				long perim = 2 * m * (m + n);
				if (perim >= maxPerim) {
					break;
				}
				long inSq = Math.abs(m * m - 2 * m * n - n * n);
				long outSq = m * m + n * n;
				if (outSq % inSq == 0) {
					ret += maxPerim / perim;
				}
			}
		}
		return ret;
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(numTiles(100000000L));
		t.end();
	}
}
