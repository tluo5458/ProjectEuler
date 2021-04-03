package problems1xx;

public class Problem115 {

	// we assume lim >= block - 1
	public static long[] allFunc(int lim, int block) {
		long[] ret = new long[lim + 1];
		for (int i = 0; i < block; i++) {
			ret[i] = 1;
		}
		for (int i = block; i <= lim; i++) {
			ret[i] += ret[i - 1];
			ret[i] += 1;
			for (int j = block; j <= i - 1; j++) {
				ret[i] += ret[i - j - 1];
			}
		}
		return ret;
	}
	
	public static long arrangements(int length, int block) {
		return allFunc(length, block)[length];
	}
	
	public static int minBlock(int limit, int block) {
		int ret = block;
		while (true) {
			if (arrangements(ret, block) > limit) {
				return ret;
			}
			ret++;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(minBlock(1000000, 50));
	}

}
