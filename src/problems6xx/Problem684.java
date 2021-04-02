package problems6xx;

import bcd.BCD;

public class Problem684 {
	private static BCD invDigSum(int n) {
		int[] digs = new int[(n + 8) / 9];
		digs[digs.length - 1] = n % 9;
		for (int i = 0; i < digs.length - 1; i++) {
			digs[i] = 9;
		}
		return new BCD(digs);
	}
	
	public static void main(String[] args) {
		invDigSum(10);
	}
}
