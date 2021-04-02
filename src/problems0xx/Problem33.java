package problems0xx;
import bcd.BCD;
import fractions.Fraction;

public class Problem33 {
	public static BCD denomFracProd() {
		Fraction denRet = new Fraction(1, 1);
		for (int num = 10; num < 100; num++) {
			int firstDig = num / 10;
			int secondDig = num % 10;
			if (secondDig != 0) {
				for (int sDig = 1; sDig < 10; sDig++) {
					int den = 10 * secondDig + sDig;
					if (num < den) {
						if (new Fraction(num, den).equals(new Fraction(firstDig, sDig))) {
							System.out.println(num + " " + den);
							denRet = Fraction.multiplyFractions(denRet, new Fraction(num, den));
						}
					}
				}
				for (int fDig = 1; fDig < 10; fDig++) {
					int den = fDig * 10 + secondDig;
					if (num < den) {
						if (new Fraction(num, den).equals(new Fraction(firstDig, fDig))) {
							System.out.println(num + " " + den);
							denRet = Fraction.multiplyFractions(denRet, new Fraction(num, den));
						}
					}
				}
			}
			for (int sDig = 1; sDig < 10; sDig++) {
				int den = 10 * firstDig + sDig;
				Fraction curr = new Fraction(num, den);
				Fraction other = new Fraction(secondDig, sDig);
				if (num < den) {
					if (curr.equals(other)) {
						System.out.println(num + " " + den);
						denRet = Fraction.multiplyFractions(denRet, new Fraction(num, den));
					}
				}
			}
			for (int fDig = 1; fDig < 10; fDig++) {
				int den = fDig * 10 + firstDig;
				if (num < den) {
					if (new Fraction(num, den).equals(new Fraction(secondDig, fDig))) {
						System.out.println(num + " " + den);
						denRet = Fraction.multiplyFractions(denRet, new Fraction(num, den));
					}
				}
			}
		}
		return denRet.den();
	}

	public static void main(String[] args) {
		System.out.println(denomFracProd());
	}
}
