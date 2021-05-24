package problems1xx;

import commonMethods.Timing;

public class Problem138 {
	public static long sumIso(int numIso) {
		long sum = 0;
		long x = 0;
		long y = 1;
		for (int i = 0; i < numIso; i++) {
			long temp = -9 * x - 4 * y - 4;
			y = -20 * x - 9 * y - 8;
			x = temp;
			sum += Math.abs(y);
		}
		return sum;
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(sumIso(12));
		t.end();
	}
}
