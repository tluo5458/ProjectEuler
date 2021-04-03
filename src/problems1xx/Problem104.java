package problems1xx;

import java.math.BigInteger;

import commonMethods.CMath;

public class Problem104 {
	public static int firstPanStartEndFib() {
		BigInteger two = BigInteger.ONE;
		BigInteger one = BigInteger.ONE;
		BigInteger pow9 = BigInteger.TEN.pow(9);
		int ind = 2;
		while (true) {
			if (CMath.isPandigitalNoZero(one.mod(pow9).toString())) {
				BigInteger head = one.divide(BigInteger.TEN.pow(one.toString().length() - 9));
				if (CMath.isPandigitalNoZero(head.toString())) {
					return ind;
				}
			}
			BigInteger sum = one.add(two);
			two = one;
			one = sum;
			ind++;
		}
	}
	
	public static void main(String[] args) {
		long start = System.nanoTime();
		System.out.println(firstPanStartEndFib());
		long end = System.nanoTime();
		System.out.println((end - start) / (1000000.0) + "ms");
	}
}
