package problems1xx;

import java.math.BigInteger;

import commonMethods.Timing;

public class Problem169 {
	// n is the binary representation of the number; assume n > 0
	// dynamic programming solution, iterates through the digits one by one
	public static long f(String n) {
		// basically we represent each solution as binary, except we also allow digits to be 2
		// so 10 = 4 + 4 + 1 + 1 would be the solution 202
		
		// zero is the number of solutions so far where the rightmost non-one digit is a 0
		long zero = 1;
		// two is the same thing but rightmost non-one digit is a 2
		long two = 0;
		// numOnes is the number of ones at the end of each solution
		int numOnes = 1;
		for (int i : n.substring(1).toCharArray()) {
			if (i == 48) {
				// if next digit is 0
				// ind1 is temporary variable to hold zero; in this case the next zero is zero + two because these are just 0 appended to all of the previous sols
				long ind1 = zero + two;
				// two is (numOnes * zero) + (numOnes + 1) * two because for each 1 at the end, we can replace the 10 with 02 and repeat until we reach 0
				// there's an extra two because once we reach that 2, it'll go 20 -> 11
				// for example, 0110 -> 0110, 0102, 0022 so it contributes 2 to two (and 1 to zero)
				// but 2110 -> 2110, 2102, 2022, 1222 so it contributes 3 to two (and 1 to zero)
				two = two + numOnes * (zero + two);
				zero = ind1;
				numOnes = 0;
			} else if (i == 49) {
				// if next digit is 1
				// all solutions will just be a 1 appended to the end of each of the previous solutions
				numOnes++;
			}
		}
		return zero + two;
	}
	
	public static long fPow10(int exp) {
		// BigInteger.TEN.pow(exp) = 10^exp, .toString(2) gets the binary representation
		return f(BigInteger.TEN.pow(exp).toString(2));
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		// under 1ms yay
		System.out.println(fPow10(25));
		t.end();
	}
}
