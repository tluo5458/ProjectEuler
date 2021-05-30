package problems1xx;

import java.util.ArrayList;

import commonMethods.CMath;
import commonMethods.Timing;

public class Problem187 {
	public static int numSemiP(int lim) {
		ArrayList<Integer> primes = CMath.primesUnder(lim / 2);
		int tot = 0;
		for (int i = 0; i < primes.size(); i++) {
			long p = primes.get(i);
			if (p * p > lim) {
				break;
			}
			// binary search for index of largest prime < lim / p
			int lo = i;
			int hi = primes.size() - 1;
			while (hi - lo > 1) {
				int curr = (hi + lo) / 2;
				if ((long) primes.get(curr) * p < lim) {
					lo = curr;
				} else {
					hi = curr;
				}
			}
			if ((long) primes.get(hi) * p < lim) {
				lo = hi;
			}
			tot += (lo - i + 1);
		}
		return tot;
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(numSemiP(100000000));
		t.end();
	}
}
