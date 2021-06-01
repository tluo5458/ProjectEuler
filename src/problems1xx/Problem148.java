package problems1xx;

import java.math.BigInteger;

import commonMethods.CMath;
import commonMethods.Timing;

public class Problem148 {
	// there's a nice formula for the answer given the base 7 representation of the number of rows
	// for example, let's say we want to see the first 270 rows. then, the base 7 representation is 534.
	// first, for the first digit 5, we take the 5th triangular number (15), multiplied by 28^2, multiplied by 1. this is 11760
	// then, for the next digit 3, we take the 3rd triangular number (6), multiplied by 28^1, multiplied by (5 + 1). this is 1008.
	// for the last digit 4, we take the 4th triangular number (10), multiplied by 28^0, multiplied by (5 + 1) * (3 + 1). this is 240.
	// in general, the last multiplication is going to be the products of one more than all the digits preceding.
	// add these numbers to get our final answer: 11760 + 1008 + 240 = 13008.
	public static BigInteger numNonDiv(BigInteger rows) {
		BigInteger ret = BigInteger.ZERO;
		BigInteger twenty8 = CMath.intToBI(28);
		char[] base7 = rows.toString(7).toCharArray();
		BigInteger currMult = BigInteger.ONE;
		BigInteger currExp = twenty8.pow(base7.length - 1);
		BigInteger[] tri = {BigInteger.ZERO, CMath.intToBI(1), CMath.intToBI(3), CMath.intToBI(6), CMath.intToBI(10), CMath.intToBI(15), CMath.intToBI(21)};
		for (int i = 0; i < base7.length; i++) {
			// note that base7 is a char array, so the actual number it's representing is 48 less because of the ascii table ('0' is 48 ascii)
			ret = ret.add(currMult.multiply(currExp).multiply(tri[base7[i] - 48]));
			currMult = currMult.multiply(CMath.intToBI(base7[i] - 47));
			currExp = currExp.divide(twenty8);
		}
		return ret;
	}
	
	// lol
	public static BigInteger numNonDiv(int rows) {
		return numNonDiv(CMath.intToBI(rows));
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(numNonDiv(1000000000));
		t.end();
	}
}
