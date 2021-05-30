package problems1xx;

import commonMethods.Timing;

public class Problem173 {
	public static long numLaminae(int lim) {
		long tot = 0;
		int[] count = new int[lim];
		// 2k x 2k hole, a even
		for (int k = 1; 4 * k * k <= lim; k++) {
			for (int a = 2 * k + 2; 4 * k * (a - k) <= lim; a += 2) {
				count[4 * k * (a - k) - 1]++;
			}
		}
		// k x k hole, k odd, a odd
		for (int k = 1; 4 * k <= lim - 4; k += 2) {
			for (int a = k + 2; a * a - k * k <= lim; a += 2) {
				count[a * a - k * k - 1]++;
			}
		}
		for (int i = 0; i < lim; i++) {
			tot += (long) count[i];
		}
		return tot;
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(numLaminae(1000000));
		t.end();
	}
}
