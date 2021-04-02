package problems0xx;
import java.util.ArrayList;

import commonMethods.CMath;

public class Problem50 {
	private static ArrayList<Integer> getConsecPrimes(int minInd, int length, int max) {
		ArrayList<Integer> primes = CMath.primes(max);
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for (int i = minInd; i < minInd + length; i++) {
			ret.add(primes.get(i));
		}
		return ret;
	}
	
	public static int maxPrimeSumPrime(int max) {
		ArrayList<Integer> primes = CMath.primes(max);
		int maxLength = 0;
		int primeMaxLength = 0;
		int minPrime = 0;
		int minInd = 0;
		for (int i = 0; i < primes.size() - maxLength; i++) {
			int curr = 0;
			int currInd = i;
			for (int j = 0; j < maxLength; j++) {
				curr += primes.get(currInd);
				currInd++;
			}
			while (curr < max - primes.get(currInd)) {
				curr += primes.get(currInd);
				if (CMath.isPrime(curr)) {
					if (currInd - i + 1 > maxLength) {
						maxLength = currInd - i + 1;
						primeMaxLength = curr;
						minPrime = primes.get(i);
						minInd = i;
					}
				}
				currInd++;
			}
		}
		System.out.println("Length: " + maxLength + "   Starts from: " + minPrime);
		System.out.println("Primes: " + getConsecPrimes(minInd, maxLength, primes.get(minInd + maxLength)));
		return primeMaxLength;
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(maxPrimeSumPrime(1000000));
	}
}
