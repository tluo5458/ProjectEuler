package problems1xx;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;

import commonMethods.CMath;
import commonMethods.Timing;

public class Problem133 {
	// is p a factor of any R(10^k)
	public static boolean isFactor(int p) {
		BigInteger prime = CMath.intToBI(p);
		BigInteger curr = BigInteger.ONE;
		BigInteger nine = CMath.intToBI(9);
		BigInteger ten = CMath.intToBI(10);
		BigInteger inverse9 = CMath.modInvP(nine, prime);
		HashSet<BigInteger> mods = new HashSet<BigInteger>();
		mods.add(curr);
		while (true) {
			curr = curr.multiply(nine).add(BigInteger.ONE).modPow(ten, prime).subtract(BigInteger.ONE).multiply(inverse9).mod(prime);
			if (mods.contains(curr)) {
				break;
			}
			mods.add(curr);
		}
		if (mods.contains(BigInteger.ZERO)) {
			return true;
		}
		return false;
	}
	
	public static ArrayList<Integer> nonFactors(int limit) {
		ArrayList<Integer> primes = CMath.primesUnder(limit);
		ArrayList<Integer> ret = new ArrayList<Integer>();
		primes.remove(0);
		primes.remove(0);
		primes.remove(0);
		ret.add(2);
		ret.add(3);
		ret.add(5);
		for (int i : primes) {
			if (!isFactor(i)) {
				ret.add(i);
			}
		}
		return ret;
	}
	
	public static long sumFactors(int limit) {
		long tot = 0;
		ArrayList<Integer> factors = nonFactors(limit);
		for (int i : factors) {
			tot += (long) i;
		}
		return tot;
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(sumFactors(100000));
		t.end();
	}
}