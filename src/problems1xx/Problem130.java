package problems1xx;

import commonMethods.CMath;
import commonMethods.Timing;

public class Problem130 {
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
	
	public static int compositePropSum(int numComps) {
		int found = 0;
		int tot = 0;
		int curr = 91;
		while (found < numComps) {
			if (CMath.isPrime(curr)) {
				curr += 2;
				continue;
			}
			int div = A(curr);
			if (div == 0) {
				curr += 2;
				continue;
			}
			if ((curr - 1) % div == 0) {
				found++;
				tot += curr;
			}
			curr += 2;
		}
		return tot;
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(compositePropSum(25));
		t.end();
	}
}
