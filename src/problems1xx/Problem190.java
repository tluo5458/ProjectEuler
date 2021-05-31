package problems1xx;

import commonMethods.Timing;

public class Problem190 {
	// lagrange multipliers gives x_i = 2*i/(m + 1)
	public static long P(int m) {
		double curr = 1.0;
		for (int i = 1; i <= m; i++) {
			curr *= Math.pow(2.0 * i / (m + 1), i); 
		}
		return (long) curr;
	}
	
	public static long sumP(int lim) {
		long ret = 0;
		for (int i = 2; i <= lim; i++) {
			ret += P(i);
		}
		return ret;
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(sumP(15));
		t.end();
	}
}
