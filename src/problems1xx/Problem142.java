package problems1xx;

import java.util.TreeSet;

import commonMethods.CMath;
import commonMethods.Timing;

public class Problem142 {
	public static long minSqColSum(int limit) {
		TreeSet<Long> sums = new TreeSet<Long>();
		for (long z = 1; z < limit / 3; z++) {
			long minSquare = ((long) Math.sqrt(2 * z) + 1);
			for (long k = minSquare; k * k - z < limit - k * k; k++) {
				// guarantees that y + z is a perfect square
				// also iterates through all possible y such that there exists an x with x > y > z and x + y + z < limit
				long y = k * k - z;
				if (!CMath.isSquare(y - z)) {
					continue;
				}
				for (long l = k + 1; k * k + l * l - z < limit; l++) {
					// guarantees that x + z is a perfect square
					// also guarantees x > y
					long x = l * l - z;
					if (!CMath.isSquare(x - z)) {
						continue;
					}
					if (!CMath.isSquare(x - y)) {
						continue;
					}
					if (!CMath.isSquare(x + y)) {
						continue;
					}
					System.out.println(x + " " + y + " " + z);
					sums.add(x + y + z);
				}
			}
		}
		if (sums.size() == 0) {
			return 0;
		}
		return sums.first();
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(minSqColSum(1010000));
		t.end();
	}
}
