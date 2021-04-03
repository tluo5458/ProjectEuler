package problems1xx;

import java.math.BigInteger;

import commonMethods.CMath;

public class Problem106 {
	public static BigInteger answer(int size) {
		BigInteger ret = BigInteger.ZERO;
		for (int i = 4; i <= size; i += 2) {
			BigInteger curr = CMath.BICombo(size, i);
			curr = curr.multiply(CMath.BICombo(i, i / 2));
			curr = curr.multiply(new BigInteger(Integer.toString(i - 2))).divide(new BigInteger(Integer.toString(2 * i + 4)));
			ret = ret.add(curr);
		}
		return ret;
	}
	
	
	public static void main(String[] args) {
		System.out.println(answer(12));
	}
}
