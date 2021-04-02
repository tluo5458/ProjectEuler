package problems0xx;

import java.math.BigInteger;

import commonMethods.CMath;

public class Problem80 {
	public static int sqrtDigSum(int sqrt, int numDigs) {
		BigInteger n = new BigInteger(Integer.toString(sqrt));
		n = n.multiply(BigInteger.TEN.pow(numDigs * 2)).sqrt();
		String s = n.toString();
		int ret = 0;
		for (int i = 0; i < numDigs; i++) {
			ret += s.charAt(i) - 48;
		}
		return ret;
	}
	
	public static int sumDigs(int limit, int numDigs) {
		int tot = 0;
		for (int i = 1; i <= limit; i++) {
			if (!CMath.isSquare(i)) {
				tot += sqrtDigSum(i, numDigs);
			}
		}
		return tot;
	}
	
	public static void main(String[] args) {
		System.out.println(sumDigs(100, 100));
	}
}
