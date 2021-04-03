package problems1xx;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class Problem110 {
	public static int numFacts(ArrayList<Integer> factors) {
		int ret = 1;
		for (int i = 0; i < factors.size(); i++) {
			ret *= (factors.get(i) * 2 + 1);
		}
		return ret;
	}
	
	public static BigInteger evalPrimeFac(ArrayList<Integer> exp, BigInteger[] primes) {
		BigInteger ret = BigInteger.ONE;
		for (int i = 0; i < primes.length; i++) {
			ret = ret.multiply(primes[i].pow(exp.get(i)));
		}
		return ret;
	}
	
	// memoized dynamic programming solution, where we recursively go through each possible prime factorization with decreasing exponents
	public static BigInteger firstAbove(int limit, ArrayList<Integer> alreadyAdded, BigInteger[] primes, HashMap<ArrayList<Integer>, BigInteger> memo) {
		if (memo.containsKey(alreadyAdded)) {
			return memo.get(alreadyAdded);
		}
		if (numFacts(alreadyAdded) > 2 * limit - 1) {
			BigInteger ret = evalPrimeFac(alreadyAdded, primes);
			memo.put(alreadyAdded, ret);
			return ret;
		}
		BigInteger init = BigInteger.TEN.pow(primes.length * 3);
		if (alreadyAdded.get(0) < Math.ceil(Math.sqrt(Math.log(limit)))) {
			ArrayList<Integer> clone1 = new ArrayList<Integer>();
			clone1.addAll(alreadyAdded);
			clone1.set(0, clone1.get(0) + 1);
			init = firstAbove(limit, clone1, primes, memo);
		}
		for (int i = alreadyAdded.size() - 1; i > 0; i--) {
			if (alreadyAdded.get(i) < alreadyAdded.get(i - 1)) {
				ArrayList<Integer> clone2 = new ArrayList<Integer>();
				clone2.addAll(alreadyAdded);
				clone2.set(i, clone2.get(i) + 1);
				if (evalPrimeFac(clone2, primes).compareTo(init) >= 0) {
					continue;
				}
				BigInteger comp = firstAbove(limit, clone2, primes, memo);
				if (comp.compareTo(init) < 0) {
					init = comp;
				}
			}
		}
		memo.put(alreadyAdded, init);
		return init;
	}
	
	public static BigInteger trueMin(int limit) {
 		int minElems = (int) Math.ceil(Math.log(2 * limit + 1) / Math.log(3));
 		ArrayList<Integer> curr = new ArrayList<Integer>();
 		for (int i = 0; i < minElems; i++) {
 			curr.add(0);
 		}
 		int[] primes = new int[minElems];
 		BigInteger[] bIPrimes = new BigInteger[minElems];
 		int found = 1;
 		bIPrimes[0] = new BigInteger("2");
 		primes[0] = 2;
 		int ind = 3;
 		while (found < minElems) {
 			boolean good = true;
 			for (int i = 1; i < found; i++) {
 				if (ind % primes[i] == 0) {
 					good = false;
 				}
 			}
 			if (good) {
 				primes[found] = ind;
 				bIPrimes[found] = new BigInteger(Integer.toString(ind));
 				found++;
 			}
 			ind += 2;
 		}
 		HashMap<ArrayList<Integer>, BigInteger> memo = new HashMap<ArrayList<Integer>, BigInteger>();
 		return firstAbove(limit, curr, bIPrimes, memo);
	}
	
	public static void main(String[] args) {
		long start1 = System.nanoTime();
		System.out.println(trueMin(4000000));
		long end1 = System.nanoTime();
		System.out.println((end1 - start1) / 1000000.0 + "ms");

		long start2 = System.nanoTime();
		System.out.println(trueMin(1000));
		long end2 = System.nanoTime();
		System.out.println((end2 - start2) / 1000000.0 + "ms");
	}
}
