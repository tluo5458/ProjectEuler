package problems1xx;

import commonMethods.Timing;

public class Problem164 {
	// dynamic programming sol
	public static long nums(int length) {
		// currs[i][j] contains number of valid ints of set length that end in ij
		long[][] currs = new long[10][10];
		for (int i = 1; i < 10; i++) {
			currs[0][i] = 1;
		}
		for (int i = 1; i < length; i++) {
			long[][] temp = new long[10][10];
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 10; k++) {
					for (int l = 0; l <= 9 - j - k; l++) {
						temp[k][l] += currs[j][k];
					}
				}
			}
			currs = temp;
		}
		long ret = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				ret += currs[i][j];
			}
		}
		return ret;
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(nums(20));
		t.end();
	}
}
