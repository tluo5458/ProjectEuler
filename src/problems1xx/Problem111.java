package problems1xx;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;

import commonMethods.CMath;

public class Problem111 {
	public static BigInteger[] testGenPattern(int len, int numZ) {
		HashSet<ArrayList<Integer>> combs = CMath.combinations(len, numZ);
		HashSet<String> patterns = new HashSet<String>();
		char[] base = new char[len];
		for (int i = 0; i < len; i++) {
			base[i] = 'x';
		}
		for (ArrayList<Integer> i : combs) {
			char[] clone = base.clone();
			for (int j : i) {
				clone[j - 1] = 'z';
			}
			patterns.add(String.valueOf(clone));
		}
		BigInteger[] allS = new BigInteger[10];
		for (int i = 0; i < 10; i++) {
			allS[i] = BigInteger.ZERO;
		}
		for (String pattern : patterns) {
			for (int x = 0; x < 10; x++) {
				String curr = pattern.replace("x", Integer.toString(x));
				if (curr.charAt(0) == '0') {
					continue;
				}
				for (int z = 0; z < Math.pow(10, numZ); z++) {
					String unpadded = Integer.toString(z);
					String replace = "";
					for (int i = 0; i < numZ - unpadded.length(); i++) {
						replace += "0";
					}
					replace += unpadded;
					int foundZs = 0;
					char[] charsNum = new char[curr.length()];
					for (int j = 0; j < curr.length(); j++) {
						if (curr.charAt(j) == 'z') {
							charsNum[j] = replace.charAt(foundZs);
							foundZs++;
						} else {
							charsNum[j] = curr.charAt(j);
						}
					}
					if (charsNum[0] == '0') {
						continue;
					}
					BigInteger num = new BigInteger(String.valueOf(charsNum));
					if (num.isProbablePrime(50)) {
						allS[x] = allS[x].add(num);
					}
				}
			}
		}
		return allS;
	}
	
	// len is assumed to be >2
	public static BigInteger[] trueAllS(int len) {
		BigInteger[] ret = new BigInteger[10];
		for (int i = 0; i < 10; i++) {
			ret[i] = BigInteger.ZERO;
		}
		int found = 0;
		for (int i = 1; i <= len; i++) {
			BigInteger[] patternI = testGenPattern(len, i);
			for (int j = 0; j < 10; j++) {
				if (ret[j].equals(BigInteger.ZERO) && !(patternI[j].equals(BigInteger.ZERO))) {
					ret[j] = patternI[j];
					found++;
				}
			}
			if (found == 10) {
				break;
			}
		}
		return ret;
	}
	
	public static BigInteger totalS(int len) {
		BigInteger ret = BigInteger.ZERO;
		BigInteger[] allS = trueAllS(len);
		for (int i = 0; i < 10; i++) {
			ret = ret.add(allS[i]);
		}
		return ret;
	}

	public static void main(String[] args) {
		long start = System.nanoTime();
		System.out.println(totalS(10));
		long end = System.nanoTime();
		System.out.println((end - start) / 1000000.0 + "ms");
	}
}
