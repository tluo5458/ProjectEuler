package problems0xx;

import java.math.BigInteger;

public class Problem94 {
	// all triples are (m^2 - n^2, 2mn, m^2 + n^2)
	
	// m^2 - n^2 is height
	public static BigInteger totPerim23(BigInteger limit) {
		final BigInteger four = new BigInteger("4");
		final BigInteger two = new BigInteger("2");
		BigInteger tot = BigInteger.ZERO;
		BigInteger m = new BigInteger("4");
		BigInteger n = BigInteger.ONE;
		while (m.add(n).pow(2).multiply(two).compareTo(limit) <= 0) {
			System.out.println((m.multiply(n).multiply(four)) + ", " + (m.multiply(m).add(n.multiply(n))) + ", " + (m.multiply(m).add(n.multiply(n))));
			tot =  tot.add(m.add(n).pow(2).multiply(two));
			BigInteger nextM = m.multiply(four).subtract(n);
			n = m;
			m = nextM;
		}
		return tot;
	}
	
	// 2mn is height
	public static BigInteger totPerim13(BigInteger limit) {
		final BigInteger four = new BigInteger("4");
		final BigInteger two = new BigInteger("2");
		BigInteger tot = BigInteger.ZERO;
		BigInteger m = new BigInteger("2");
		BigInteger n = BigInteger.ONE;
		while (m.pow(2).multiply(four).compareTo(limit) <= 0) {
			tot = tot.add(m.pow(2).multiply(four));
			System.out.println((m.pow(2).subtract(n.pow(2))).multiply(two) + ", " + (m.pow(2).add(n.pow(2))) + ", " + (m.pow(2).add(n.pow(2))));
			BigInteger nextM = m.multiply(two).add(n.multiply(new BigInteger("3")));
			n = m.add(n.multiply(two));
			m = nextM;
		}
		return tot;
	}
	
	public static BigInteger totPerim(BigInteger limit) {
		return totPerim13(limit).add(totPerim23(limit));
	}
	
	public static void main(String[] args) {
		System.out.println(totPerim(new BigInteger("1000000000")));
	}
}
