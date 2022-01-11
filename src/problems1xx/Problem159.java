package problems1xx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import commonMethods.Timing;

public class Problem159 {
	private static int dr(int i) {
		return (i - 1) % 9 + 1;
	}
	
	// given an arraylist of prime factors (with multiplicity), gives
	// a sorted arraylist of factors
	private static ArrayList<Integer> factors(ArrayList<Integer> primes) {
		HashMap<Integer, Integer> p = new HashMap<Integer, Integer>();
		for (int i : primes) {
			if (p.containsKey(i)) {
				p.put(i, p.get(i) + 1);
			} else {
				p.put(i, 1);
			}
		}
		ArrayList<Integer> ret = new ArrayList<Integer>();
		ret.add(1);
		for (int i : p.keySet()) {
			int[] pow = new int[p.get(i)];
			pow[0] = i;
			for (int j = 1; j < p.get(i); j++) {
				pow[j] = i * pow[j - 1];
			}
			ArrayList<Integer> toAdd = new ArrayList<Integer>();
			for (int f : ret) {
				for (int j : pow) {
					toAdd.add(f * j);
				}
			}
			for (int f : toAdd) {
				ret.add(f);
			}
		}
		Collections.sort(ret);
		return ret;
	}
	
	// dp solution
	// the key insight is that if n is composite, then mdrs(n) is going
	// to be equal to mdrs(a) + mdrs(b) for some a, b where ab = n
	// (either that or dr(n)
	// so we iterate through, doing a sieve of eratosthenes-esque
	// algorithm where we keep track of all prime factors of each number 
	// found so far
	public static int tot(int lim) {
		HashMap<Integer, ArrayList<Integer>> factors = new HashMap<Integer, ArrayList<Integer>>();
		for (int i = 2; i < lim; i++) {
			factors.put(i, new ArrayList<Integer>());
		}
		int[] mdrs = new int[lim - 2];
		for (int i = 2; i < lim; i++) {
			if (factors.get(i).size() == 0) {
				// prime
				mdrs[i - 2] = dr(i);
				// add the correct number of factors of i to all multiples
				// of i and powers of i
				int curr = 1;
				do {
					curr *= i;
					for (int j = curr; j < lim; j += curr) {
						factors.get(j).add(i);
					}
				} while (curr <= lim / i);
			} else {
				// composite
				int max = 0;
				ArrayList<Integer> facts = factors(factors.get(i));
				// go through the factors and find the max possible sum
				// of mdrs
				for (int j = 1; j < (facts.size() + 1) / 2; j++) {
					max = Math.max(max, mdrs[facts.get(j) - 2] + mdrs[facts.get(facts.size() - 1 - j) - 2]);
				}
				// or it's just dr(i) (e.g. if i = 8, it's this)
				max = Math.max(max, dr(i));
				mdrs[i - 2] = max;
			}
		}
		int sum = 0;
		for (int i : mdrs) {
			sum += i;
		}
		return sum;
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(tot(1000000));
		t.end();
	}
}
