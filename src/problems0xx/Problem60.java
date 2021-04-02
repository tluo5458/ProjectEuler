package problems0xx;

import java.util.ArrayList;
import commonMethods.CMath;

public class Problem60 {
	private static int concat(int a, int b) {
		return ((int) (Math.pow(10, Integer.toString(b).length()))) * a + b;
	}
	
	private static ArrayList<Integer> paired(int a, ArrayList<Integer> primes) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for (int i = a + 1; i < primes.size(); i++) {
			if (CMath.isPrime(concat(primes.get(a), primes.get(i))) && CMath.isPrime(concat(primes.get(i), primes.get(a)))) {
				ret.add(primes.get(i));
			}
		}
		return ret;
	}
	
	public static ArrayList<ArrayList<Integer>> allPairs(int bound, ArrayList<Integer> primes) {
		ArrayList<ArrayList<Integer>> pairs = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < primes.size(); i++) {
			pairs.add(paired(i, primes));
		}
		return pairs;
	}
	
	public static int minQuintSum(int bound) {
		ArrayList<Integer> primes = CMath.primesUnder(bound);
		int min = Integer.MAX_VALUE;
		ArrayList<ArrayList<Integer>> pairs = allPairs(bound, primes);
		for (int a = 1; a < primes.size(); a++) {
			for (int b = a + 1; b < primes.size(); b++) {
				if (!pairs.get(a).contains(primes.get(b))) {
					continue;
				}
				for (int c = b + 1; c < primes.size(); c++) {
					if (!pairs.get(a).contains(primes.get(c)) || !pairs.get(b).contains(primes.get(c))) {
						continue;
					}
					for (int d = c + 1; d < primes.size(); d++) {
						if (!pairs.get(a).contains(primes.get(d)) || !pairs.get(b).contains(primes.get(d)) || !pairs.get(c).contains(primes.get(d))) {
							continue;
						}
						for (int e = d + 1; e < primes.size(); e++) {
							if (!pairs.get(a).contains(primes.get(e)) || !pairs.get(b).contains(primes.get(e)) || !pairs.get(c).contains(primes.get(e)) || !pairs.get(d).contains(primes.get(e))) {
								continue;
							}
							int sum = primes.get(a) + primes.get(b) + primes.get(c) + primes.get(d) + primes.get(e);
							System.out.println(primes.get(a));
							System.out.println(primes.get(b));
							System.out.println(primes.get(c));
							System.out.println(primes.get(d));
							System.out.println(primes.get(e));
							if (sum < min) {
								min = sum;
							}
						}
					}
				}
			}
		}
		return min;
	}
	
	public static void main(String[] args) {
		System.out.println(minQuintSum(10000));
	}
}
