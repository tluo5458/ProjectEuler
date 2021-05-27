package problems1xx;

import commonMethods.CMath;
import commonMethods.Timing;

public class Problem131 {
	public static int numPrimeProperty(int limit) {
		int tot = 0;
		int i = 1;
		while (true) {
			int p = 3 * i * i + 3 * i + 1;
			if (p >= limit) {
				break;
			}
			if (CMath.isPrime(p)) {
				tot++;
			}
			i++;
		}
		return tot;
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(numPrimeProperty(1000000));
		t.end();
	}
}
