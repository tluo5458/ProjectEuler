package problems1xx;

import java.math.BigInteger;

import commonMethods.Timing;

public class Problem137 {
	// gen func gives us A_F(x) = x/(1 - x - x^2)
	// setting A_F(x) = n and quadratic formula (discriminant) gives us 5n^2 + 2n + 1 = k^2 for all golden nuggets n, k some integer
	// then use pell to find all positive solutions
	public static BigInteger goldenNugget(int a) {
		BigInteger one61 = new BigInteger("161");
		BigInteger seven2 = new BigInteger("72");
		BigInteger three2 = new BigInteger("32");
		BigInteger three60 = new BigInteger("360");
		BigInteger[] n = {new BigInteger("104"), new BigInteger("2"), new BigInteger("15")};
		BigInteger[] q = {new BigInteger("233"), new BigInteger("5"), new BigInteger("34")};
		BigInteger currN = n[a % 3];
		BigInteger currQ = q[a % 3];
		for (int i = 0; i < (a - 1) / 3; i++) {
			BigInteger temp = (one61.multiply(currN)).add(seven2.multiply(currQ)).add(three2);
			currQ = (three60.multiply(currN)).add(one61.multiply(currQ)).add(seven2);
			currN = temp;
		}
		return currN;
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(goldenNugget(15));
		t.end();
	}
}
