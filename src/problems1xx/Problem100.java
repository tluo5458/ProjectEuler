package problems1xx;

import java.math.BigInteger;

import graphs.Pair;

public class Problem100 {
	public static Pair<BigInteger, BigInteger> nextPair(Pair<BigInteger, BigInteger> p) {
		BigInteger left = p.getLeft().multiply(new BigInteger("3")).add(p.getRight().multiply(new BigInteger("2"))).subtract(new BigInteger("2"));
		BigInteger right = p.getLeft().multiply(new BigInteger("4")).add(p.getRight().multiply(new BigInteger("3"))).subtract(new BigInteger("3"));
		return new Pair<BigInteger, BigInteger>(left, right);
	}
	
	public static Pair<BigInteger, BigInteger> firstAbove(BigInteger n) {
		Pair<BigInteger, BigInteger> curr = new Pair<BigInteger, BigInteger>(new BigInteger("15"), new BigInteger("21"));
		while (curr.getRight().compareTo(n) <= 0) {
			curr = nextPair(curr);
		}
		return curr;
	}
	
	public static void main(String[] args) {
		System.out.println(firstAbove(new BigInteger("22")));
		long before = System.nanoTime();
		System.out.println(firstAbove(new BigInteger("1000000000000")));
		long after = System.nanoTime();
		System.out.println((after - before) / 1000000.0 + " ms");
	}
}
