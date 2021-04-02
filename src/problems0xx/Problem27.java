package problems0xx;
import commonMethods.CMath;

public class Problem27 {
	private static int numPrimes(int a, int b) {
		int curr = 0;
		while (true) {
			if (!CMath.isPrime(curr * curr + a * curr + b)) {
				return curr;
			}
			curr++;
		}
	}
	//4x^2 - 2x + 1
	public static int maxPrimeProd(int aLimit, int bLimit) {
		int aMax = 0;
		int bMax = 0;
		int maxPrimes = 0;
		for (int i = -1 * aLimit + 1; i < aLimit; i++) {
			for (int j = -1 * bLimit + 1; j < bLimit; j++) {
				int curr = numPrimes(i, j);
				if (curr > maxPrimes) {
					aMax = i;
					bMax = j;
					maxPrimes = curr;
				}
			}
		}
		System.out.println(aMax);
		System.out.println(bMax);
		System.out.println(maxPrimes);
		return aMax * bMax;
	}
	
	public static void main(String[] args) {
		System.out.println(maxPrimeProd(1000, 1001));
	}
}
