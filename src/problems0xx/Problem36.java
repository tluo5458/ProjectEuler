package problems0xx;
import commonMethods.CMath;

public class Problem36 {
	public static int sumDoubPalin(int limit) {
		int total = 0;
		for (int i = 1; i <= limit; i += 2) {
			if (CMath.isPalindrome(Integer.toString(i)) && CMath.isPalindrome(Integer.toBinaryString(i))) {
				System.out.println(i);
				total += i;
			}
		}
		return total;
	}
	
	public static void main(String[] args) {
		System.out.println(sumDoubPalin(1000000));
	}
}
