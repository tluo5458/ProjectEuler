package problems1xx;

import java.math.BigInteger;
import java.util.ArrayList;

import commonMethods.CMath;
import commonMethods.Timing;

public class Problem132 {
	// is prime p factor of R(10^k)
	public static boolean isFactor(int p, int k) {
		BigInteger prime = CMath.intToBI(p);
		BigInteger curr = BigInteger.ONE;
		BigInteger nine = CMath.intToBI(9);
		BigInteger ten = CMath.intToBI(10);
		BigInteger inverse9 = CMath.modInvP(nine, prime);
		for (int i = 0; i < k; i++) {
			curr = curr.multiply(nine).add(BigInteger.ONE).modPow(ten, prime).subtract(BigInteger.ONE).multiply(inverse9).mod(prime);
		}
		if (curr.compareTo(BigInteger.ZERO) == 0) {
			return true;
		}
		return false;
	}
	
	// finds all primes p that are a factor of R(10^k) that are below limit
	public static ArrayList<Integer> factors(int limit, int k) {
		ArrayList<Integer> primes = CMath.primesUnder(limit);
		ArrayList<Integer> ret = new ArrayList<Integer>();
		// get rid of 2, 3, 5 for obvious reason
		primes.remove(0);
		primes.remove(0);
		primes.remove(0);
		for (Integer i : primes) {
			if (isFactor(i, k)) {
				ret.add(i);
			}
		}
		return ret;
	}
	
	// for the sake of generality, yes this is like 1.5x slower than if i programmed this purely to solve the problem
	public static int sumFactors(int k, int numPrimes) {
		int lim = 1000;
		while (true) {
			ArrayList<Integer> factors = factors(lim, k);
			if (factors.size() >= numPrimes) {
				int tot = 0;
				for (int i = 0; i < numPrimes; i++) {
					tot += factors.get(i);
				}
				return tot;
			}
			lim *= 7;
		}
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(sumFactors(9, 40));
		t.end();
	}
}
