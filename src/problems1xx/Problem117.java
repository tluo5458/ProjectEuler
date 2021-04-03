package problems1xx;

import java.math.BigInteger;

public class Problem117 {
	public static BigInteger[] numArrangements(int length) {
		BigInteger[] ret = new BigInteger[length + 1];
		ret[0] = BigInteger.ONE;
		ret[1] = BigInteger.ONE;
		ret[2] = new BigInteger("2");
		ret[3] = new BigInteger("4");
		for (int i = 4; i <= length; i++) {
			ret[i] = ret[i - 1].add(ret[i - 2]).add(ret[i - 3]).add(ret[i - 4]);
		}
		return ret;
	}
	
	public static BigInteger arrangements(int length) {
		return numArrangements(length)[length];
	}
	
	public static void main(String[] args) {
		System.out.println(arrangements(50));
	}
}
