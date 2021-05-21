package problems1xx;

import commonMethods.CMath;
import commonMethods.Timing;

public class Problem118 {
	public static int pandigPrimeSets() {
		int tot = 0;
		for (int i : CMath.permutations(123456789)) {
			tot += primePartitions(Integer.toString(i), 0, 0);
		}
		return tot;
	}
	
	// recursive function: k is string of digits, digs is how many digits the function has already processed, prev is the previous integer in the partition
	// returns how many ways there are to partition the remaining digits such that the resulting set is increasing and all prime
	public static int primePartitions(String k, int digs, int prev) {
		int tot = 0;
		for (int i = Integer.toString(prev).length(); i <= (k.length() - digs) / 2; i++) {
			int next = Integer.parseInt(k.substring(digs, digs + i));
			if (next <= prev) {
				continue;
			}
			if (!CMath.isPrime(next)) {
				continue;
			}
			tot += primePartitions(k, digs + i, next);
		}
		int remaining = Integer.parseInt(k.substring(digs));
		if (remaining > prev && CMath.isPrime(remaining)) {
			tot++;
		}
		return tot;
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(pandigPrimeSets());
		t.end();
	}
}
