package problems0xx;
import java.util.ArrayList;

import commonMethods.CMath;

public class Problem35 {
	private static ArrayList<Integer> rotations(int num) {
		String given = Integer.toString(num);
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for (int i = 0; i < given.length(); i++) {
			ret.add(Integer.parseInt(given.substring(i) + given.substring(0, i)));
		}
		return ret;
	}
	
	public static int circPrimeCount(int limit) {
		int total = 0;
		for (int i = 2; i <= limit; i++) {
			boolean stillGood = true;
			for (Integer j : rotations(i)) {
				if (!CMath.isPrime(j)) {
					stillGood = false;
				}
			}
			if (stillGood) {
				System.out.println(i);
				total++;
			}
		}
		return total;
	}
	
	public static void main(String[] args) {
		System.out.println(circPrimeCount(1000000));
	}
}
