package problems1xx;

import java.math.BigInteger;
import java.util.TreeSet;

import commonMethods.CMath;
import commonMethods.Timing;

public class Problem119 {
	// treeset of all integers with <= maxDigs digits which is a power of its digit sum
	public static TreeSet<BigInteger> digSumPow(int maxDigs) {
		TreeSet<BigInteger> ret = new TreeSet<BigInteger>();
		BigInteger max = BigInteger.TEN.pow(maxDigs);
		
		for (int i = 7; i <= maxDigs * 9; i++) {
			BigInteger iBI = CMath.intToBI(i);
			BigInteger curr = iBI.pow(2);
			while (curr.compareTo(max) < 0) {
				if (CMath.digSumBI(curr) == i) {
					ret.add(curr);
				}
				curr = curr.multiply(iBI);
			}
		}
		return ret;
	}
	
	public static BigInteger a(int n) {
		TreeSet<BigInteger> curr = new TreeSet<BigInteger>();
		int i = 2;
		while (curr.size() < n) {
			curr = digSumPow(i);
			i++;
		}
		return (BigInteger) curr.toArray()[n - 1];
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(a(30));
		t.end();
	}
}
