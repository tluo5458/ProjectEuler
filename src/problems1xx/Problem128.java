package problems1xx;

import commonMethods.CMath;
import commonMethods.Timing;

public class Problem128 {
	public static long primeDiffTile(int num) {
		if (num == 1) {
			return 1;
		}
		if (num == 2) {
			return 2;
		}
		int found = 2;
		int layer = 2;
		while (true) {
//			long curr = 3 * layer * layer - 3 * layer + 2;
//			long curr2 = curr + 6 * layer - 1;
			if (!CMath.isPrime(6 * layer - 1)) {
				layer++;
				continue;
			}
			if (CMath.isPrime(6 * layer + 1) && CMath.isPrime(12 * layer + 5)) {
				found++;
				if (found == num) {
					return 3L * layer * layer - 3L * layer + 2;
				}
			}
			if (CMath.isPrime(6 * layer + 5) && CMath.isPrime(12 * layer - 7)) {
				found++;
				if (found == num) {
					return 3L * layer * layer + 3L * layer + 1;
				}
			}
			layer++;
		}
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(primeDiffTile(2000));
		t.end();
	}
}
