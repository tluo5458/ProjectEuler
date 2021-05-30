package problems1xx;

import commonMethods.Timing;

public class Problem174 {
	public static int sumN(int maxN) {
		int tot = 0;
		int[] count = new int[1000000];
		// 2k x 2k hole, a even
		for (int k = 1; 4 * k * k <= 1000000; k++) {
			for (int a = 2 * k + 2; 4 * k * (a - k) <= 1000000; a += 2) {
				count[4 * k * (a - k) - 1]++;
			}
		}
		// k x k hole, k odd, a odd
		for (int k = 1; 4 * k <= 1000000 - 4; k += 2) {
			for (int a = k + 2; a * a - k * k <= 1000000; a += 2) {
				count[a * a - k * k - 1]++;
			}
		}
		for (int i = 0; i < 1000000; i++) {
			if (count[i] <= maxN && count[i] > 0) {
				tot++;
			}
		}
		return tot;
	}

	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(sumN(10));
		t.end();
	}
}
