package fractions;

import commonMethods.CMath;

public class Fraction implements Comparable<Fraction> {
	private long num;
	private long den;
	
	public Fraction(long numerator, long denominator) {
		if (denominator == 0) {
			throw new IllegalArgumentException("Can't divide by zero!");
		}
		num = numerator;
		den = denominator;
		simplify();
	}
	
	public Fraction(Fraction f) {
		num = f.num;
		den = f.den;
	}
	
	private void simplify() {
		long gcd = CMath.gcd(Math.abs(num), Math.abs(den));
		num /= gcd;
		den /= gcd;
		boolean neg = false;
		if (num < 0) {
			neg = !neg;
			num *= -1;
		}
		if (den < 0) {
			neg = !neg;
			den *= -1;
		}
		if (neg) {
			num *= -1;
		}
	}
	
	public long num() {
		return num;
	}
	
	public long den() {
		return den;
	}
	
	public Fraction neg() {
		return new Fraction(-1 * num, den);
	}
	
	public Fraction add(Fraction a) {
		return new Fraction(a.num * den + a.den * num, a.den * den);
	}
	
	public Fraction subtract(Fraction a) {
		return add(a.neg());
	}
	
	public Fraction multiply(Fraction a) {
		return new Fraction(a.num * num, a.den * den);
	}
	
	public Fraction divide(Fraction a) {
		return multiply(a.reciprocal());
	}
	
	public Fraction reciprocal() {
		return new Fraction(den, num);
	}
	
	@Override
	public int compareTo(Fraction other) {
		if (this.equals(other)) {
			return 0;
		}
		return (num * other.den - den * other.num) > 0 ? 1 : -1;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Fraction)) {
			return false;
		}
		Fraction oFrac = (Fraction) other;
		return (num == oFrac.num && den == oFrac.den);
	}
	
	@Override 
	public int hashCode() {
		return (int) (10000 * num + den);
	}
	
	@Override
	public String toString() {
		return num + "/" + den;
	}
}
