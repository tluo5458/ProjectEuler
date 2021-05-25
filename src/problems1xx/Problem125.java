package problems1xx;

import java.util.TreeSet;

import commonMethods.CMath;
import commonMethods.Timing;

public class Problem125 {
	public static long palinSums(long limit) {
		TreeSet<Long> sums = new TreeSet<Long>();
		for (int i = (int) Math.sqrt(limit) + 1; i > 1; i--) {
			long curr = i * i;
			for (int j = i - 1; j > 0; j--) {
				curr += j * j;
				if (curr >= limit) {
					break;
				}
				if (CMath.isPalindrome(Long.toString(curr))) {
					sums.add(curr);
				}
			}
		}
		long sum = 0;
		for (Long i : sums) {
			sum += i;
		}
		return sum;
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(palinSums(100000000));
		t.end();
	}
}
