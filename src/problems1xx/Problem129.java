package problems1xx;

import commonMethods.Timing;

public class Problem129 {
	public static int A(int n) {
		if (n % 5 == 0 || n % 2 == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		int x = 1;
		int k = 1;
		while (x != 0) {
			x = (x * 10 + 1) % n;
			k++;
		}
		return k;
	}
	
	public static int minAboveA(int lim) {
		int x = lim - (lim % 2) + 1;
		while (true) {
			if (A(x) > lim) {
				return x;
			}
			x += 2;
		}
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(minAboveA(1000000));
		t.end();
	}
}
