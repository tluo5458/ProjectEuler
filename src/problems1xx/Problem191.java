package problems1xx;

import commonMethods.Timing;

public class Problem191 {
	// dynamic programming solution
	public static long numPrizes(int days) {
		// 0 late, 0 absent at end
		// 0 late, 1 absent at end
		// 0 late, 2 absent at end
		// 1 late, 0 absent at end
		// 1 late, 1 absent at end
		// 1 late, 2 absent at end
		long[][] states = new long[6][days];
		states[0][0] = 1;
		states[1][0] = 1;
		states[3][0] = 1;
		for (int i = 1; i < days; i++) {
			states[0][i] = states[0][i - 1] + states[1][i - 1] + states[2][i - 1];
			states[1][i] = states[0][i - 1];
			states[2][i] = states[1][i - 1];
			states[3][i] = states[0][i] + states[3][i - 1] + states[4][i - 1] + states[5][i - 1];
			states[4][i] = states[3][i - 1];
			states[5][i] = states[4][i - 1];
		}
		long ret = 0;
		for (int i = 0; i < 6; i++) {
			ret += states[i][days - 1];
		}
		return ret;
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(numPrizes(30));
		t.end();
	}
}
