package fractions;

import bcd.BCD;

public class BCDFraction implements Comparable<BCDFraction> {
	private BCD num;
	private BCD den;
	
	public BCDFraction(int numerator, int denominator) {
		if (denominator == 0) {
			throw new IllegalArgumentException("Can't divide by zero!");
		}
		num = new BCD(numerator);
		den = new BCD(denominator);
		simplify();
	}
	
	public BCDFraction(BCD numerator, BCD denominator) {
		if (denominator.equals(new BCD(0))) {
			throw new IllegalArgumentException("Can't divide by zero!");
		}
		num = numerator;
		den = denominator;
		simplify();
	}
	
	private void simplify() {
		BCD gcd = BCD.gcd(num, den);
		num = BCD.divideBCDs(num, gcd);
		den = BCD.divideBCDs(den, gcd);
	}
	
	public BCD num() {
		return num;
	}
	
	public BCD den() {
		return den;
	}
	
	public static BCDFraction addFractions(BCDFraction a, BCDFraction b) {
		return new BCDFraction(BCD.addBCDs(BCD.multiplyBCDs(a.num, b.den), BCD.multiplyBCDs(a.den, b.num)), BCD.multiplyBCDs(a.den, b.den));
	}
	
	public static BCDFraction multiplyFractions(BCDFraction a, BCDFraction b) {
		return new BCDFraction(BCD.multiplyBCDs(a.num, b.num), BCD.multiplyBCDs(a.den, b.den));
	}
	
	public BCDFraction reciprocal() {
		return new BCDFraction(den, num);
	}
	
	@Override
	public int compareTo(BCDFraction other) {
		BCD diff = BCD.subtractBCDs(BCD.multiplyBCDs(num, other.den), BCD.multiplyBCDs(den, other.num));
		if (diff.compareTo(new BCD(0)) == 0) {
			return 0;
		} 
		return diff.isNegative() ? -1 : 1;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof BCDFraction)) {
			return false;
		}
		BCDFraction oFrac = (BCDFraction) other;
		return (num.equals(oFrac.num) && den.equals(oFrac.den));
	}
	
	@Override 
	public int hashCode() {
		return 10000 * num.hashCode() + den.hashCode();
	}
	
	@Override
	public String toString() {
		return num + "/" + den;
	}
}
