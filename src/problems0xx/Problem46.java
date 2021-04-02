package problems0xx;
import commonMethods.CMath;

public class Problem46 {
	private static boolean worksGoldbach(int num) {
		for (int i = 1; i < Math.sqrt(num); i++) {
			if (CMath.isPrime(num - 2 * i * i)) {
				return true;
			}
		}
		return false;
	}
	
	public static int firstNonGoldbach() {
		int curr = 3;
		while (true) {
			if (!CMath.isPrime(curr)) {
				if (!worksGoldbach(curr)) {
					return curr;
				}
			}
			curr += 2;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(firstNonGoldbach());
	}
}
