package problems0xx;

import java.math.BigInteger;
import java.util.ArrayList;

public class Problem66 {
	public static ArrayList<Integer> period(int n) {
		double sqrt = Math.sqrt(n);
		int sqrtF = (int) sqrt;
		if (sqrtF * sqrtF == n) {
			return new ArrayList<Integer>();
		}
		ArrayList<Integer> ret = new ArrayList<Integer>();
		int d = 1;
		int m = 0;
		int a = sqrtF;
		ret.add(a);
		do {
			m = d * a - m;
			d = (n - m * m) / d;
			a = (int) ((sqrt + m) / d);
			ret.add(a);
		} while (a != 2 * sqrtF);
		return ret;
	}
	
	public static BigInteger firstSol(int n) {
		ArrayList<Integer> period = period(n);
		if (period.size() == 0) {
			return BigInteger.ZERO;
		}
		BigInteger num = new BigInteger(Integer.toString(period.get(period.size() - 2)));
		BigInteger den = BigInteger.ONE;
		for (int i = period.size() - 3; i >= 0; i--) {
			BigInteger temp = num;
			num = den;
			den = temp;
			num = num.add(den.multiply(new BigInteger(Integer.toString(period.get(i)))));
		}
		if (num.multiply(num).subtract(den.multiply(den).multiply(new BigInteger(Integer.toString(n)))).equals(new BigInteger("-1"))) {
			num = num.add((new BigInteger(Integer.toString(n))).sqrt());
			for (int i = period.size() - 2; i >= 0; i--) {
				BigInteger temp = num;
				num = den;
				den = temp;
				num = num.add(den.multiply(new BigInteger(Integer.toString(period.get(i)))));
			}
		}
		System.out.println(num + "/" + den);
		return num;
	}
	
	public static int maxFirstSol(int limit) {
		BigInteger max = BigInteger.ZERO;
		int attained = 0;
		for (int i = 2; i <= limit; i++) {
			BigInteger sol = firstSol(i);
			if (sol.compareTo(max) > 0) {
				max = sol;
				attained = i;
			}
		}
		System.out.println(max);
		return attained;
	}
	
	public static void main(String[] args) {
		System.out.println(maxFirstSol(1000));
		System.out.println(firstSol(661));
	}
}
