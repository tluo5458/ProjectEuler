package problems1xx;

import commonMethods.Timing;

public class Problem136 {
	public static int[] sols(int limit) {
		int[] ret = new int[limit - 1];
		for (int x = 1; x < limit; x++) {
			long p = x/3 + 1;
			for (long y = p + ((8 - (x % 4) - (p % 4)) % 4); x * y < limit; y += 4) {
				if (x * y >= Integer.MAX_VALUE) {
					break;
				}
				ret[(int) (x * y - 1)]++;
			}
		}
		return ret;
	}
	
	public static int numEqual(int limit, int numSols) {
		int[] sols = sols(limit);
		int count = 0;
		for (int i : sols) {
			if (i == numSols) {
				count++;
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(numEqual(50000000, 1));
		t.end();
	}
}
