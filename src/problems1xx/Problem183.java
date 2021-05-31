package problems1xx;

import java.util.TreeSet;

import commonMethods.CMath;
import commonMethods.Timing;

public class Problem183 {
	public static TreeSet<Integer> hamming25(int lim) {
		TreeSet<Integer> ret = new TreeSet<Integer>();
		int curr = 1;
		while (curr <= lim) {
			ret.add(curr);
			curr *= 2;
		}
		TreeSet<Integer> add = new TreeSet<Integer>();
		for (int i : ret) {
			int con = 5 * i;
			if (con > lim) {
				break;
			}
			while (con <= lim) {
				add.add(con);
				con *= 5;
			}
		}
		ret.addAll(add);
		return ret;
	}
	
	// just assume lim > 6 lol
	// this was initially a lot better but then i realized it didn't work
	// so this is just a very quick fix to the solution i already had
	// probably quite inefficient
	public static int sumD(int lim) {
		TreeSet<Integer> good = hamming25((int) (lim/Math.E) + 1);
		// ret is the result if D(5) and D(6) were negative but D(N) positive for all 7 to lim
		int ret = lim * (lim + 1) / 2 - 32;
		// basic idea here is that for N/k to be optimal, you need N/k to be approximately e
		for (int i = 3; i < (int) (lim / Math.E) + 1; i++) {
			double approx = Math.E * i;
			int min = (int) Math.ceil(approx - 2);
			int max = (int) Math.floor(approx + 2);
			for (int n = (int) Math.floor(approx); n >= min; n--) {
				if ((CMath.intToBI(n).multiply(CMath.intToBI(i - 1).pow(i - 1))).compareTo(CMath.intToBI(i).pow(i)) >= 0) {
					if (n <= lim) {
						if (good.contains(i / CMath.gcd(i, n))) {
							ret -= 2 * n;
						}
					}
				} else {
					break;
				}
			}
			for (int n = (int) Math.ceil(approx); n <= max; n++) {
				if ((CMath.intToBI(n).multiply(CMath.intToBI(i).pow(i))).compareTo(CMath.intToBI(i + 1).pow(i + 1)) <= 0) {
					if (n <= lim) {
						if (good.contains(i / CMath.gcd(i, n))) {
							ret -= 2 * n;
						}
					}
				} else {
					break;
				}
			}
		}
		return ret;
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(sumD(10000));
		t.end();
	}
}
