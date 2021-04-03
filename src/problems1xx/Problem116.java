package problems1xx;

import java.math.BigInteger;

import commonMethods.CMath;

public class Problem116 {
	// we assume lim >= block - 1
	public static BigInteger numArrangements(int length, int block) {
		BigInteger tot = BigInteger.ZERO;
		int top = length - block + 1;
		int bot = 1;
		while (top >= bot) {
			tot = tot.add(CMath.BICombo(top, bot));
			top -= (block - 1);
			bot++;
		}
		return tot;
	}
	
	public static BigInteger RGBArrangements(int length) {
		return numArrangements(length, 2).add(numArrangements(length, 3)).add(numArrangements(length, 4));
	}
	
	public static void main(String[] args) {
		System.out.println(RGBArrangements(50));
	}
}
