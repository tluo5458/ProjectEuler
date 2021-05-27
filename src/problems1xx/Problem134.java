package problems1xx;

import java.math.BigInteger;
import java.util.ArrayList;

import commonMethods.CMath;
import commonMethods.Timing;

public class Problem134 {
	// n is the number of digits in p
	// k is -p * 10^(-n) mod q, and 10^(-n) = 10^(q - 1 - n) mod q by Fermat's
	// return k * 10^n + p
	public static BigInteger S(int p, int q) {
		BigInteger primeP = CMath.intToBI(p);
		BigInteger primeQ = CMath.intToBI(q);
		BigInteger n = CMath.intToBI(Integer.toString(p).length());
		BigInteger k = BigInteger.ZERO.subtract(primeP).multiply(BigInteger.TEN.modPow(primeQ.subtract(BigInteger.ONE).subtract(n), primeQ)).mod(primeQ);
		return k.multiply(BigInteger.TEN.pow(Integer.toString(p).length())).add(primeP);
	}
	
	// generate all primes below limit along with the next one (hence +100)
	// remove 2, 3
	// run S on all consec pairs
	public static BigInteger connecSum(int limit) {
		ArrayList<Integer> primes = CMath.primesUnder(limit + 100);
		primes.remove(0);
		primes.remove(0);
		BigInteger tot = BigInteger.ZERO;
		for (int i = 0; primes.get(i) <= limit; i++) {
			tot = tot.add(S(primes.get(i), primes.get(i + 1)));
		}
		return tot;
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(connecSum(1000000));
		t.end();
	}
}
