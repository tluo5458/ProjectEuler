package problems0xx;
import commonMethods.CMath;

public class Problem41 {
	private static int maxPanPrime(int numDig) {
		for (int i = CMath.factorial(numDig) - 1; i >= 0; i--) {
			int curr = Integer.parseInt(CMath.numToPerm(i, numDig));
			if (CMath.isPrime(curr)) {
				return curr;
			}
		}
		return 0;
	}
	
	public static int maxPanPrime() {
		for (int i = 9; i > 0; i--) {
			int curr = maxPanPrime(i);
			if (curr != 0) {
				return curr;
			}
		}
		return 0;
	}
	
	public static void main(String[] args) {
		System.out.println(maxPanPrime());
	}
}
