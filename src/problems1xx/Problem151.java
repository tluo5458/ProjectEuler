package problems1xx;

import commonMethods.Timing;

public class Problem151 {
	// dynamic programming sol
	public static double answer() {
		double[][][][] memo = new double[9][9][9][9];
		// -1 to exclude the last one, (1, 1, 1, 1) because we exclude the first one (and hence have 1 each of a2, a3, a4, a5)
		// round fuckery to only get 6 digs after decimals
		return Math.round((helper(1, 1, 1, 1, memo) - 1) * 1000000) / 1000000.0;
	}
	
	private static double helper(int a2, int a3, int a4, int a5, double[][][][] memo) {
		if (memo[a2][a3][a4][a5] > 0) {
			return memo[a2][a3][a4][a5];
		}
		double s = (double) a2 + a3 + a4 + a5;
		double curr = 0.0;
		if (a2 + a3 + a4 + a5 == 1) {
			curr = 1.0;
		}
		if (a2 > 0) {
			curr += a2 / s * helper(a2 - 1, a3 + 1, a4 + 1, a5 + 1, memo);
		}
		if (a3 > 0) {
			curr += a3 / s * helper(a2, a3 - 1, a4 + 1, a5 + 1, memo);
		}
		if (a4 > 0) {
			curr += a4 / s * helper(a2, a3, a4 - 1, a5 + 1, memo);
		}
		if (a5 > 0) {
			curr += a5 / s * helper(a2, a3, a4, a5 - 1, memo);
		}
		memo[a2][a3][a4][a5] = curr;
		return curr;
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(answer());
		t.end();
	}
}
