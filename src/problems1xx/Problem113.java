package problems1xx;

import java.math.BigInteger;

import commonMethods.CMath;

public class Problem113 {
	public static BigInteger nonBouncy(int expTenLimit) {
		BigInteger tot = BigInteger.ZERO;
		for (int i = 1; i <= expTenLimit; i++) {
			tot = tot.add(CMath.comboBI(8 + i, i)).add(CMath.comboBI(9 + i, i)).subtract(BigInteger.TEN);
		}
		return tot;
	}
	
	public static void main(String[] args) {
		System.out.println(nonBouncy(100));
	}
}
