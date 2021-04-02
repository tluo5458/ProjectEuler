package bcd;

import java.util.ArrayList;
import java.util.Arrays;

public class BCD implements Comparable<BCD> {

	/**
	 * @author Thomas Luo
	 * 
	 * This is basically just my shitty implementation of BigInteger.
	 * 
	 * BCD DESCRIPTION: 
	 * 
	 * BCD (Binary-Coded Decimal) stores integers as an array of their base 10 digits in backwards order. The main advantage is that this allows for storage of 
	 * positive and negative integers up to 10^(2^31 - 1) - 1, as opposed to the integer limit of 2^31 - 1 or the long limit of 2^63 - 1. The main constraint of 
	 * the size of the BCD is the limit on lengths of arrays (which in Java is Integer.MAX_VALUE).
	 * 
	 * BCD provides many of the basic utilities with integers: addition, subtraction, multiplication, (integer/truncated) division, exponentiation, factorial, 
	 * comparisons, absolute value, negations, modulo, and gcd. Additional methods will be added as necessary.
	 * Addition, subtraction, and division (?) are O(n), while multiplication is done naively at O(n^2). Exponentiation is also done naively.
	 * Important to note that multiplication is unstable for very large numbers (exceeding around 25 million digits each); see (*) below.
	 * 
	 * As far as I know, BCD is immutable.
	 * 
	 * BCD is not synchronized.
	 * 
	 * In hindsight, it might've been better to store these in base 2 with booleans or shorts instead, as it would be faster and take less memory, but it would 
	 * "only" go up to 2^(2^31 - 1) - 1. Whatever.
	 * 
	 * Future work: -Rewrite pow to use repeated squaring, rather than the current naive implementation
	 * 				-Rewrite multiplyBCDs to use a more efficient algorithm, in particular those relying on FFTs.
	 * 
	 * (*) It is possible for multiplyBCDs to crash if two BCDs of sufficient size are multiplied, and the intermediate step array contains entries that
	 * would exceed Integer.MAX_VALUE, since the intermediate array contains the sum of the products of digits with each possible place value sum. In particular, 
	 * trying (10^26512144 - 1)^2 would almost certainly not work. Not to mention that it would be slow as hell.
	 */

	private int[] digits;
	private boolean neg;
	public static final BCD ZERO = new BCD(0);
	public static final BCD ONE = new BCD(1);
	public static final BCD TEN = new BCD(10);

	public BCD(int n) {
		neg = false;
		if (n < 0) {
			neg = true;
			n *= -1;
		}
		digits = new int[Integer.toString(n).length()];
		for (int i = 0; i < digits.length; i++) {
			digits[i] = n % 10;
			n /= 10;
		}
	}

	public BCD(BCD other) {
		neg = other.neg;
		digits = new int[other.digits.length];
		for (int i = 0; i < digits.length; i++) {
			digits[i] = other.digits[i];
		}
	}
	
	public BCD(int[] digs) {
		neg = false;
		digits = new int[digs.length];
		for (int i = 0; i < digits.length; i++) {
			digits[i] = digs[i];
		}
	}

	public BCD(int[] digs, boolean isNeg) {
		neg = isNeg;
		digits = new int[digs.length];
		for (int i = 0; i < digits.length; i++) {
			digits[i] = digs[i];
		}
	}

	public BCD(ArrayList<Integer> digs, boolean isNeg) {
		if (digs.size() == 0) {
			digits = new int[1];
			neg = false;
		} else {
			neg = isNeg;
			digits = new int[digs.size()];
			for (int i = 0; i < digits.length; i++) {
				digits[i] = digs.get(i);
			}
		}
	}

	public static BCD negation(BCD bcd) {
		if (bcd.equals(new BCD(0))) {
			return new BCD(bcd);
		}
		BCD ret = new BCD(bcd);
		ret.neg = !ret.neg;
		return ret;
	}

	public static BCD abs(BCD bcd) {
		BCD ret = new BCD(bcd);
		ret.neg = false;
		return ret;
	}

	public static BCD addBCDs(BCD first, BCD second) {
		if (second.equals(new BCD(0))) {
			return new BCD(first);
		}
		if (first.neg == second.neg) {
			int[] newDigs = new int[Math.max(first.digits.length, second.digits.length)];
			for (int i = 0; i < first.digits.length; i++) {
				newDigs[i] += first.digits[i];
			}
			for (int i = 0; i < second.digits.length; i++) {
				newDigs[i] += second.digits[i];
			}
			for (int i = 0; i < newDigs.length - 1; i++) {
				newDigs[i + 1] += newDigs[i] / 10;
				newDigs[i] = newDigs[i] % 10;
			}
			int numExtraDigs = Integer.toString(newDigs[newDigs.length - 1]).length() - 1;
			int[] finDigs = new int[newDigs.length + numExtraDigs];
			for (int i = 0; i < newDigs.length; i++) {
				finDigs[i] = newDigs[i];
			}
			for (int i = newDigs.length - 1; i < finDigs.length - 1; i++) {
				finDigs[i + 1] = finDigs[i] / 10;
				finDigs[i] = finDigs[i] % 10;
			}
			return new BCD(finDigs, first.neg);
		}
		return subtractBCDs(first, negation(second));
	}

	public static BCD subtractBCDs(BCD first, BCD second) {
		if (second.equals(new BCD(0))) {
			return new BCD(first);
		}
		if (first.neg == second.neg) {
			boolean isNeg = first.compareTo(second) < 0;
			boolean fPGE = abs(first).compareTo(abs(second)) >= 0;
			BCD gPos = (fPGE ? first : second);
			BCD lPos = (fPGE ? second : first);
			int[] newDigs = new int[gPos.digits.length];
			for (int i = 0; i < gPos.digits.length; i++) {
				newDigs[i] += gPos.digits[i];
			}
			for (int i = 0; i < lPos.digits.length; i++) {
				newDigs[i] -= lPos.digits[i];
			}
			for (int i = 0; i < newDigs.length - 1; i++) {
				int transfers = ((newDigs[i] - 9) / 10) * -1;
				newDigs[i + 1] -= transfers;
				newDigs[i] += transfers * 10;
			}
			int numLeadZeros = 0;
			for (int i = newDigs.length - 1; i >= 0; i--) {
				if (newDigs[i] == 0) {
					numLeadZeros++;
				} else {
					break;
				}
			}
			int[] finDigs = new int[newDigs.length - numLeadZeros];
			for (int i = 0; i < finDigs.length; i++) {
				finDigs[i] = newDigs[i];
			}
			if (finDigs.length == 0) {
				finDigs = new int[1];
				finDigs[0] = 0;
			}
			return new BCD(finDigs, isNeg);
		}
		return addBCDs(first, negation(second));
	}

	public static BCD multiplyBCDs(BCD first, BCD second) {
		boolean isNeg = xor(first.neg, second.neg);
		int[] f = first.digits;
		int[] s = second.digits;
		int[] newDigs = new int[f.length + s.length];
		for (int i = 0; i < f.length; i++) {
			for (int j = 0; j < s.length; j++) {
				newDigs[i + j] += f[i] * s[j];
			}
		}
		for (int i = 0; i < newDigs.length - 1; i++) {
			newDigs[i + 1] += newDigs[i] / 10;
			newDigs[i] = newDigs[i] % 10;
		}
		int numLeadZeros = 0;
		for (int i = newDigs.length - 1; i >= 0; i--) {
			if (newDigs[i] == 0) {
				numLeadZeros++;
			} else {
				break;
			}
		}
		int[] finDigs = new int[newDigs.length - numLeadZeros];
		for (int i = 0; i < finDigs.length; i++) {
			finDigs[i] = newDigs[i];
		}
		if (finDigs.length == 0) {
			finDigs = new int[1];
			finDigs[0] = 0;
		}
		return new BCD(finDigs, isNeg);
	}

	public static BCD divideBCDs(BCD first, BCD second) {
		if (second.equals(new BCD(0))) {
			throw new IllegalArgumentException("Cannot divide by zero!");
		}
		boolean isNeg = xor(first.neg, second.neg);
		BCD fPos = abs(first);
		ArrayList<Integer> subtract = new ArrayList<Integer>();
		ArrayList<Integer> digs = new ArrayList<Integer>();
		for (int i = 0; i < first.digits.length - second.digits.length; i++) {
			subtract.add(0);
		}
		for (Integer i : second.digits) {
			subtract.add(i);
		}
		for (int i = 0; i <= first.digits.length - second.digits.length; i++) {
			BCD curr = new BCD(subtract, false);
			int dig = 0;
			while (fPos.compareTo(curr) >= 0) {
				fPos = subtractBCDs(fPos, curr);
				dig++;
			}
			digs.add(0, dig);
			subtract.remove(0);
		}
		for (int i = digs.size() - 1; i >= 0; i--) {
			if (digs.get(i) == 0) {
				digs.remove(i);
			} else {
				break;
			}
		}
		return new BCD(digs, isNeg);
	}

	public static BCD pow(BCD a, int b) {
		BCD curr = new BCD(1);
		for (int i = 0; i < b; i++) {
			curr = multiplyBCDs(curr, a);
		}
		return curr;
	}

	public static BCD pow(int a, int b) {
		return pow(new BCD(a), b);
	}

	public static BCD factorial(int n) {
		BCD curr = new BCD(1);
		for (int i = 2; i <= n; i++) {
			curr = multiplyBCDs(curr, new BCD(i));
		}
		return curr;
	}

	public static BCD mod(BCD a, BCD b) {
		return subtractBCDs(a, multiplyBCDs(divideBCDs(a, b), b));
	}

	public static BCD gcd(BCD a, BCD b) {
		BCD aPos = abs(a);
		BCD bPos = abs(b);
		if (aPos.equals(new BCD(0))) {
			return new BCD(bPos);
		}
		if (bPos.equals(new BCD(0))) {
			return new BCD(aPos);
		}
		return gcdHelp(aPos, bPos);
	}

	private static BCD gcdHelp(BCD a, BCD b) {
		if (a.compareTo(b) >= 0) {
			BCD mod = mod(a, b);
			if (mod.equals(new BCD(0))) {
				return new BCD(b);
			}
			return gcdHelp(mod, b);
		}
		BCD mod = mod(b, a);
		if (mod.equals(new BCD(0))) {
			return new BCD(a);
		}
		return gcdHelp(a, mod);
	}

	private static boolean xor(boolean a, boolean b) {
		return (a || b) && (!a || !b);
	}
	
	public boolean isNegative() {
		return neg;
	}
	
	public int digSum() {
		int ret = 0;
		for (int i = 0; i < digits.length; i++) {
			ret += digits[i];
		}
		return ret;
	}
	
	public int numDigs() {
		return digits.length;
	}
	
	public BCD reverse() {
		int[] reversed = new int[digits.length];
		for (int i = 0; i < digits.length; i++) {
			reversed[digits.length - 1 - i] = digits[i];
		}
		return new BCD(reversed, neg);
	}
	
	public boolean isPrime() {
		if (Arrays.equals(digits, new int[] {2})) {
			return true; 
		}
		if (digits[0] % 2 == 0) {
			return false;
		}
		if (digits.length <= 2) {
			BCD consider = new BCD(3);
			while (consider.compareTo(this) < 0) {
				if (mod(this, consider).equals(ZERO)) {
					return false;
				}
				consider = addBCDs(consider, new BCD(2));
			}
			return true;
		}
		int leading = 0;
		int zeros = (digits.length - 1) / 2;
		leading += digits[2 * zeros];
		if (digits.length % 2 == 0) {
			leading += 10 * digits[digits.length - 1];
		}
		int rt = (int) (Math.sqrt(leading) + 1);
		BCD bound = multiplyBCDs(new BCD(rt), pow(TEN, zeros));
		BCD consider = new BCD(3);
		while (consider.compareTo(bound) <= 0) {
			if (mod(this, consider).equals(ZERO)) {
				return false;
			}
			consider = addBCDs(consider, new BCD(2));
		}
		return true;
	}
	
	public boolean isPalindrome() {
		for (int i = 0; i < digits.length / 2; i++) {
			if (digits[i] != digits[digits.length - 1 - i]) {
				return false;
			}
		}
		return true;
	}
	
	public int[] getDigits() {
		return digits;
	}

	@Override
	public int compareTo(BCD other) {
		if (other.neg != neg) {
			return (neg ? -1 : 1);
		}
		int multiplier = neg ? -1 : 1;
		if (digits.length != other.digits.length) {
			return multiplier * (digits.length - other.digits.length);
		}

		for (int i = digits.length - 1; i >= 0; i--) {
			if (digits[i] != other.digits[i]) {
				return multiplier * (digits[i] - other.digits[i]);
			}
		}
		return 0;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof BCD)) {
			return false;
		}
		BCD oBCD = (BCD) other;
		return compareTo(oBCD) == 0;
	}

	@Override
	public int hashCode() {
		int ret = 0;
		for (int i = 0; i < digits.length; i++) {
			ret *= 10;
			ret += digits[i];
		}
		return ret;
	}

	@Override
	public String toString() {
		String ret = (neg ? "-" : "");
		for (int i = digits.length - 1; i >= 0; i--) {
			ret += Integer.toString(digits[i]);
		}
		return ret;
	}

	public static void main(String[] args) {
		int[] test = {101, 1001, 10000007, 10000019};
		for (int i : test) {
			BCD aa = new BCD(i);
			System.out.println(i + " " + aa.isPrime());	
		}
	}
}
