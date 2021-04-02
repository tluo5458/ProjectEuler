package problems0xx;

import java.util.HashSet;

public class Problem74 {
	public static int factDigSum(int n) {
		int[] factorials = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
		char[] nChars = Integer.toString(n).toCharArray();
		int tot = 0;
		for (int i = 0; i < nChars.length; i++) {
			tot += factorials[nChars[i] - 48];
		}
		return tot;
	}
	
	public static int numTerms(int n) {
		HashSet<Integer> terms = new HashSet<Integer>();
		terms.add(n);
		int curr = n;
		while (true) {
			curr = factDigSum(curr);
			if (terms.contains(curr)) {
				break;
			} else {
				terms.add(curr);
			}
		}
		return terms.size();
	}
	
	public static int numSixtyTerms(int bound) {
		int count = 0;
		for (int i = 1; i < bound; i++) {
			if (numTerms(i) == 60) {
				System.out.println(i);
				count++;
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		System.out.println(numSixtyTerms(1000000));
	}
}
