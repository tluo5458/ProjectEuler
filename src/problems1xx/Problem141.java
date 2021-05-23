package problems1xx;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

import commonMethods.CMath;
import commonMethods.Timing;

public class Problem141 {
	// check if n^2 is progressive by factoring n
	public static boolean sqProgFactoring(int n) {
		HashMap<Integer, Integer> counts = CMath.primeCount(n);
		for (int p : counts.keySet()) {
			counts.put(p, 4 * counts.get(p));
		}
		TreeSet<BigInteger> quadFacts = CMath.factors(counts);
		ArrayList<BigInteger> facts = new ArrayList<BigInteger>();
		facts.addAll(quadFacts);
		BigInteger square = CMath.intToBI(n).pow(2);
		for (BigInteger i : facts) {
			if (i.compareTo(CMath.intToBI(n)) > 0) {
				break;
			}
//			System.out.println(i);
			BigInteger isCube = square.subtract(i).pow(2).divide(i);
			BigInteger cbrtCube = BigInteger.valueOf((long) Math.pow((int) (Math.pow((double) isCube.floatValue(), 1.0/3.0) + 0.001), 3));
//			System.out.println(cbrtCube + " " + isCube);
			if (cbrtCube.compareTo(isCube) == 0) {
				return true;
			}
		}
		return false;
	}
	
	public static BigInteger progSqSumFactoring(int lim) {
		BigInteger tot = BigInteger.ZERO;
		for (int i = 2; i < lim; i++) {
			if (sqProgFactoring(i)) {
				System.out.println(i);
				tot = tot.add(CMath.intToBI(i).pow(2));
			}
		}
		return tot;
	}
	
	// let n = dq + r, r guaranteed to be smallest in sequence
	// let common ratio be a/b, then b^2 divides r, so let r = cb^2
	// then n = a^3bc^2 + b^2c, so we can just iterate through a = 1 to cbrt(lim), b = 1 to a - 1, c >= 1 until n surpasses limit
	public static long progSqSumIter(long lim) {
		HashSet<Long> progSqs = new HashSet<Long>();
		for (long a = 2; a < Math.pow(lim, 1.0 / 3.0); a++) {
			for (long b = 1; b < a; b++) {
				if (CMath.gcd(a, b) > 1) {
					continue;
				}
				long c = 1;
				long n = a * a * a * b + b * b;
				while (n < lim) {
					if (CMath.isSquare(n)) {
						progSqs.add(n);
					}
					c++;
					n = a * a * a * b * c * c + b * b * c;
				}
			}
		}
		long tot = 0;
		for (Long l : progSqs) {
			tot += l;
		}
		return tot;
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
//		System.out.println(progSqSumFactoring(1000000));
		// 395.9318663 seconds
		// this has the overhead of factoring every single fuckin number between 2 and 999999 inclusive
		System.out.println(progSqSumIter(1000000000000L));
		// 3.8818599 seconds
		t.end();
	}
}
