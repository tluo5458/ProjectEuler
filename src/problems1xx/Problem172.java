package problems1xx;

import java.math.BigInteger;
import java.util.ArrayList;

import commonMethods.CMath;
import commonMethods.Timing;

public class Problem172 {
	// numNums is an arraylist of length 4 that sums to 10
	// basically numNums.get(i) is the number of distinct digits which occur i times in the number
	public static BigInteger arrangements(ArrayList<Integer> numNums) {
		BigInteger ret = BigInteger.ZERO;
		int tot = numNums.get(1) + 2 * numNums.get(2) + 3 * numNums.get(3);
		for (int i = 0; i < 4; i++) {
			if (numNums.get(i) > 0) {
				// suppose 0 occurs i times in the number
				BigInteger add = CMath.factorialBI(9);
				add = add.multiply(CMath.factorialBI(tot));
				for (int j = 0; j < 4; j++) {
					if (j == i) {
						add = add.divide(CMath.factorialBI(numNums.get(j) - 1));
					} else {
						add = add.divide(CMath.factorialBI(numNums.get(j)));
					}
					add = add.divide(CMath.factorialBI(j).pow(numNums.get(j)));
				}
				add = add.multiply(CMath.intToBI(tot - i));
				add = add.divide(CMath.intToBI(tot));
				ret = ret.add(add);
				// this formula is first: 9!/(i! for each i) to choose how many of each digit there are (with the one assigned as 0 excluded for obvious reasons)
				// then multiplied by tot! / ((i!)^numNums.get(i)) to actually arrange the digits properly in the main number
				// then multiplied by (tot - i) / tot to avoid leading zeros
			}
		}
		return ret;
	}
	
	// calculates the number of nums with digs digits and no digit occurring >3 times
	public static BigInteger totNums(int digs) {
		if (digs > 30 || digs < 0) {
			return BigInteger.ZERO;
		}
		// generates all arraylists [a, b, c, d] such that b + 2*c + 3*d = digs, and a + b + c + d = 10
		// each element represents how many different digits occur i times in the number
		// for example, [2, 1, 4, 3] would mean that we have an 18 digit number, where 3 digits occur 3 times, 4 digits occur twice, 1 digit occurs exactly once, and 2 digits never appear 
		ArrayList<ArrayList<Integer>> good = new ArrayList<ArrayList<Integer>>();
		for (int three = digs / 3; three >= 0; three--) {
			int tot = digs - (3 * three);
			int sum = three + (tot / 2) + (tot % 2);
			if (sum > 10) {
				continue;
			}
			for (int i = 0; i <= Math.min(10 - sum, tot / 2); i++) {
				ArrayList<Integer> numNums = new ArrayList<Integer>();
				numNums.add(10 - sum - i);
				numNums.add(tot % 2 + (2 * i));
				numNums.add(tot / 2 - i);
				numNums.add(three);
				good.add(numNums);
			}
		}
		// iterates through these arraylists, calling arrangements on it
		BigInteger tot = BigInteger.ZERO;
		for (ArrayList<Integer> numNums : good) {
			BigInteger arr = arrangements(numNums);
			tot = tot.add(arr);
		}
		return tot;
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(totNums(18));
		t.end();
	}
}
