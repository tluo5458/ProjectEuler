package problems0xx;

import fractions.BCDFraction;

public class Problem65 {
	// calculates [previous, a1, ..., an] where continued = [a1, ..., an]
	public static BCDFraction addToFront(BCDFraction continued, int previous) {
		return BCDFraction.addFractions(new BCDFraction(previous, 1), continued.reciprocal());
	}
	
	public static BCDFraction econvergent(int n) {
		// coeffs is backwards
		int[] coeffs = new int[n];
		for (int i = 0; i < n; i++) {
			coeffs[i] = 1;
		}
		for (int i = n % 3; i < n; i += 3) {
			coeffs[i] = (n / 3) * 2 - (i / 3) * 2;
		}
		coeffs[n - 1] = 2;
		BCDFraction curr = new BCDFraction(coeffs[0], 1);
		for (int i = 1; i < n; i++) {
			curr = addToFront(curr, coeffs[i]);
		}
		return curr;
	}
	
	public static void main(String[] args) {
		BCDFraction hundred = econvergent(100);
		System.out.println(hundred);
		System.out.println(hundred.num().digSum());
	}
}
