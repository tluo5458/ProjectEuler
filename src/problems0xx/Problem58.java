package problems0xx;
import commonMethods.CMath;

public class Problem58 {
	public static int minSideLengthUnder(double percentage) {
		int primes = 0;
		int total = 1;
		int currLayer = 1;
		while (true) {
			int check = 4*currLayer*currLayer - 2*currLayer + 1;
			for (int i = 0; i < 3; i++) {
				if (CMath.isPrime(check)) {
					primes++;
				}
				check += 2 * currLayer;
			}
			total += 4;
			if ((double) primes / total < percentage) {
				return 2 * currLayer + 1;
			}
			currLayer++;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(minSideLengthUnder(0.1));
	}
}
