package problems1xx;

import java.util.ArrayList;

import commonMethods.CMath;

public class Problem123 {
	public static int remAbove(long limit) {
		ArrayList<Integer> primes = CMath.primesUnder((int) Math.pow(limit, 0.6));
		for (int i = 1; i < primes.size(); i += 2) {
			if (((long) 2) * i * primes.get(i - 1) > limit) {
				return i;
			}
		}
		return 0;
	}
	
	public static void main(String[] args) {
		System.out.println(remAbove(10000000000L));
	}
}
