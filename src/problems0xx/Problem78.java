package problems0xx;

import java.math.BigInteger;
import java.util.ArrayList;

import commonMethods.CMath;

public class Problem78 {
	
	public static int firstPartitionDivBy(BigInteger mod) {
		ArrayList<BigInteger> ret = new ArrayList<BigInteger>();
		ret.add(BigInteger.ONE);
		int i = 1;
		while (true) {
			BigInteger add = BigInteger.ZERO;
			int curr = 1;
			while (CMath.polygonalNumber(curr, 5) <= i) {
				if (curr % 2 == 0) {
					add = add.subtract(ret.get(i - CMath.polygonalNumber(curr, 5)));
				} else {
					add = add.add(ret.get(i - CMath.polygonalNumber(curr, 5)));
				}
				if (curr < 0) {
					curr--;
				}
				curr *= -1;
			}
			if (add.mod(mod).equals(BigInteger.ZERO)) {
				return i;
			}
			ret.add(add);
			i++;
		}
	}
	public static void main(String[] args) {
		System.out.println(firstPartitionDivBy(new BigInteger("1000000")));
	}
}
