package problems0xx;
import bcd.BCD;

public class Problem55 {
	private static boolean isLychrel(int n, int limit) {
		BCD curr = new BCD(n);
		for (int i = 0; i < limit; i++) {
			curr = BCD.addBCDs(curr, curr.reverse());
			if (curr.isPalindrome()) {
				return false;
			}
		}
		return true;
	}
	
	public static int numLychrel(int limit, int iter) {
		int counter = 0;
		for (int i = 1; i <= limit; i++) {
			if (isLychrel(i, iter)) {
				counter++;
				System.out.println(i);
			}
		}
		return counter;
	}
	
	public static void main(String[] args) {
//		System.out.println(isLychrel(349, 50));
		System.out.println(numLychrel(10000, 50));
	}
}
