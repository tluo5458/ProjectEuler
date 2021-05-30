package problems1xx;

import java.util.ArrayList;

import commonMethods.CMath;
import commonMethods.Timing;

public class Problem179 {
	public static int numConsec(int lim) {
		ArrayList<Integer> primes = CMath.primesUnder(lim);
		int tot = 0;
		// 2 to lim
		int[] numFacts = new int[lim - 1];
		for (int i = 0; i < lim - 1; i++) {
			numFacts[i] = 1;
		}
		for (int i : primes) {
			for (int k = i - 2; k < lim - 1; k += i) {
				int mult = 2;
				int curr = (k + 2) / i;
				while (curr % i == 0) {
					mult++;
					curr /= i;
				}
				numFacts[k] *= mult;
			}
		}
		for (int i = 0; i < lim - 2; i++) {
			if (numFacts[i] == numFacts[i + 1]) {
				tot++;
			}
		}
		return tot;
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(numConsec(10000000));
		t.end();
	}
}
