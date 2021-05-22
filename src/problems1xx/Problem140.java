package problems1xx;

import java.math.BigInteger;

import commonMethods.Timing;

public class Problem140 {
	// infinite geometric series gives us A_G(x) = x(1 + 3x)/(1 - x - x^2)
	// setting A_G(x) = n and quadratic formula (discriminant) gives us 5n^2 + 14n + 1 = k^2 for all golden nuggets n, k some integer
	// then use pell to find all positive solutions	
	public static BigInteger goldenNugget(int a) {
		// 1, 2, 5 mod 6
		BigInteger[] x1 = {new BigInteger("2"), new BigInteger("5"), new BigInteger("152")};
		BigInteger[] y1 = {new BigInteger("7"), new BigInteger("14"), new BigInteger("343")};
		// 0, 3, 4 mod 6
		BigInteger[] x2 = {new BigInteger("296"), new BigInteger("21"), new BigInteger("42")};
		BigInteger[] y2 = {new BigInteger("-665"), new BigInteger("-50"), new BigInteger("-97")};
		BigInteger one61 = new BigInteger("161");
		BigInteger seven2 = new BigInteger("72");
		BigInteger two24 = new BigInteger("224");
		BigInteger three60 = new BigInteger("360");
		BigInteger five04 = new BigInteger("504");
		if (a % 3 != 0 && a % 6 != 4) {
			if (a % 6 == 5) {
				a -= 2;
			}
			BigInteger currX = x1[a % 6 - 1];
			BigInteger currY = y1[a % 6 - 1];
			for (int i = 0; i < a / 6; i++) {
				BigInteger temp = (one61.multiply(currX)).add(seven2.multiply(currY)).add(two24);
				currY = (three60.multiply(currX)).add(one61.multiply(currY)).add(five04);
				currX = temp;
			}
			return currX;
		}
		if (a % 6 == 0) {
			a += 2;
		}
		BigInteger currX = x2[a % 6 - 2];
		BigInteger currY = y2[a % 6 - 2];
		for (int i = 0; i < (a - 3) / 6; i++) {
			BigInteger temp = (one61.multiply(currX)).subtract(seven2.multiply(currY)).add(two24);
			currY = BigInteger.ZERO.subtract(three60.multiply(currX)).add(one61.multiply(currY)).subtract(five04);
			currX = temp;
		}
		return currX;
	}

	// copied code from goldenNugget method, but adds each iteration instead of just calling goldenNugget lim times
	public static BigInteger goldenNuggetSum(int lim) {
		BigInteger ret = BigInteger.ZERO;
		// 1, 2, 5 mod 6
		BigInteger[] x1 = {new BigInteger("2"), new BigInteger("5"), new BigInteger("152")};
		BigInteger[] y1 = {new BigInteger("7"), new BigInteger("14"), new BigInteger("343")};
		// 0, 3, 4 mod 6
		BigInteger[] x2 = {new BigInteger("296"), new BigInteger("21"), new BigInteger("42")};
		BigInteger[] y2 = {new BigInteger("-665"), new BigInteger("-50"), new BigInteger("-97")};
		BigInteger one61 = new BigInteger("161");
		BigInteger seven2 = new BigInteger("72");
		BigInteger two24 = new BigInteger("224");
		BigInteger three60 = new BigInteger("360");
		BigInteger five04 = new BigInteger("504");
		for (int i = Math.max(1, lim - 5); i <= lim; i++) {
			int a = i;
			if (a % 3 != 0 && a % 6 != 4) {
				if (a % 6 == 5) {
					a -= 2;
				}
				BigInteger currX = x1[a % 6 - 1];
				BigInteger currY = y1[a % 6 - 1];
				ret = ret.add(currX);
				for (int j = 0; j < a / 6; j++) {
					BigInteger temp = (one61.multiply(currX)).add(seven2.multiply(currY)).add(two24);
					currY = (three60.multiply(currX)).add(one61.multiply(currY)).add(five04);
					currX = temp;
					ret = ret.add(currX);
				}
			} else {
				if (a % 6 == 0) {
					a += 2;
				}
				BigInteger currX = x2[a % 6 - 2];
				BigInteger currY = y2[a % 6 - 2];
				ret = ret.add(currX);
				for (int j = 0; j < (a - 3) / 6; j++) {
					BigInteger temp = (one61.multiply(currX)).subtract(seven2.multiply(currY)).add(two24);
					currY = BigInteger.ZERO.subtract(three60.multiply(currX)).add(one61.multiply(currY)).subtract(five04);
					currX = temp;
					ret = ret.add(currX);
				}
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(goldenNuggetSum(30));
		t.end();
	}
}
