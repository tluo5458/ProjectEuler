package problems1xx;

import java.math.BigInteger;

import commonMethods.CMath;
import commonMethods.Timing;

public class Problem121 {
	public static BigInteger[] probs(int row) {
		BigInteger[] ret = new BigInteger[row + 1];
		for (int i = 0; i <= row; i++) {
			ret[i] = BigInteger.ZERO;
		}
		ret[0] = BigInteger.ONE;
		for (int i = 1; i <= row; i++) {
			BigInteger[] curr = new BigInteger[i + 1];
			for (int j = 0; j <= i; j++) {
				curr[j] = BigInteger.ZERO;
			}
			for (int j = 0; j < i; j++) {
				curr[j] = curr[j].add(ret[j].multiply(CMath.intToBI(i)));
				curr[j + 1] = curr[j + 1].add(ret[j]);
			}
			for (int j = 0; j <= i; j++) {
				ret[j] = curr[j];
			}
		}
		return ret;
	}
	
	public static BigInteger maxPayout(int turns) {
		BigInteger[] probs = probs(turns);
		BigInteger tot = BigInteger.ZERO;
		for (int i = turns / 2 + 1; i <= turns; i++) {
			tot = tot.add(probs[i]);
		}
		return CMath.BIFactorial(turns + 1).divide(tot);
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(maxPayout(15));
		t.end();
		t.time();
	}
}
