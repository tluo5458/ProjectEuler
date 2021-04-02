package problems0xx;
import commonMethods.CMath;

public class Problem43 {
	private static int[] primes = {2, 3, 5, 7, 11, 13, 17};
	
	private static boolean goodSub(String s) {
		for (int i = 1; i < 8; i++) {
			int curr;
				curr = Integer.parseInt(s.substring(i, i + 3));
			if (curr % primes[i - 1] != 0) {
				return false;
			}
		}
		return true;
	}
	
	public static long sum() {
		long ret = 0;
		for (int i = 0; i < CMath.factorial(10); i++) {
			String curr = CMath.numToPerm(i, 9);
			if (goodSub(curr)) {
				System.out.println(curr);
				ret += Long.parseLong(curr);
			}
		}
		return ret;
	}
	
	public static void main(String[] args) {
		System.out.println(sum());
	}
}
