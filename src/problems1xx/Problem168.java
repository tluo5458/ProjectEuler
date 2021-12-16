package problems1xx;

import java.math.BigInteger;

import commonMethods.CMath;

public class Problem168 {
	public static int ord(int mod) {
		if (mod == 1) {
			return 1;
		}
		int ord = 1;
		int start = 10 % mod;
		while (start != 1) {
			start = (start * 10) % mod;
			ord++;
		}
		return ord;
	}
	
	public static BigInteger sum(int k, int d, int maxexp) {
		int div = 10 * d - 1;
		int gcd = CMath.gcd(div, k);
		int exp = ord(div / gcd);
		int numexp = (maxexp / exp) * exp;
		BigInteger num = BigInteger.TEN.pow(numexp).subtract(BigInteger.ONE);
		BigInteger den = BigInteger.TEN.pow(exp).subtract(BigInteger.ONE);
		BigInteger ret = num.divide(den).multiply(BigInteger.TEN.pow(exp)).subtract(CMath.intToBI(maxexp / exp));
		if (exp == 1) {
			ret = ret.subtract(CMath.intToBI(9));
		}
		return ret.multiply(CMath.intToBI(k)).divide(CMath.intToBI(div));
	}
	
	public static BigInteger ans() {
		BigInteger ret = BigInteger.ZERO;
		for (int d = 1; d < 10; d++) {
			for (int k = d; k < 10; k++) {
//				System.out.println(k + " " + d + " " + sum(k, d, 100));
				ret = ret.add(sum(k, d, 100));
			}
		}
		return ret.mod(BigInteger.TEN.pow(5));
	}
	
	public static void main(String[] args) {
		System.out.println(ans());
	}
}
