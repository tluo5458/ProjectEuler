package problems1xx;

import java.util.Arrays;

public class Problem114 {

	// we assume lim >= 2
	public static long[] allFunc(int lim) {
		long[] ret = new long[lim + 1];
		ret[0] = 1;
		ret[1] = 1;
		ret[2] = 1;
		for (int i = 3; i <= lim; i++) {
			ret[i] += ret[i - 1];
			ret[i] += 1;
			for (int j = 3; j <= i - 1; j++) {
				ret[i] += ret[i - j - 1];
			}
		}
		return ret;
	}
	
	public static long arrangements(int length) {
		System.out.println(Arrays.toString(allFunc(length)));
		return allFunc(length)[length];
	}
	
	public static void main(String[] args) {
		System.out.println(arrangements(50));
	}

}
