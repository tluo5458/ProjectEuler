package problems1xx;

import java.math.BigInteger;
import java.util.TreeSet;

import commonMethods.Timing;

public class Problem169 {
	// n is the binary representation of the number
	public static int f(String n) {
		TreeSet<String> done = new TreeSet<String>();
		TreeSet<String> bins = new TreeSet<String>();
		bins.add(n);
		while (!bins.isEmpty()) {
			char[] bin = bins.first().toCharArray();
			done.add(bins.first());
			bins.remove(bins.first());
			for (int i = 0; i < bin.length - 1; i++) {
				if (bin[i] > '0' && bin[i + 1] == '0') {
					char[] dupe = bin.clone();
					dupe[i] -= 1;
					dupe[i + 1] += 2;
					String s = String.valueOf(dupe);
					if (s.charAt(0) == '0') {
						s = s.substring(1);
					}
					if (!done.contains(s)) {
						bins.add(s);
					}
				}
			}
		}
		return done.size();
	}
	
	public static int fPow10(int exp) {
		return f(BigInteger.TEN.pow(exp).toString(2));
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(fPow10(12));
		// true answer would be fPow10(75), but 12 is already taking 6 seconds lol
		t.end();
	}
}
