package problems0xx;

import commonMethods.CMath;

public class Problem51 {
	
	//pattern formatted with numbers and then "x" for replace, e.g. 56xx3
	public static int testPattern(String pattern) {
		int count = 0;
		for (int i = 0; i < 10; i++) {
			int check = Integer.parseInt(pattern.replace("x", Integer.toString(i)));
			if (CMath.isPrime(check) && Integer.toString(check).length() == pattern.length()) {
				count++;
			}
		}
		return count;
	}
	
	//pattern formatted with z where numbers should be
	public static int testGenPattern(String genPattern, int numZ) {
		int max = 0;
		int attained = 0;
		int bound = (int) Math.pow(10, numZ);
		for (int i = 0; i < bound; i++) {
			String replace = String.format("%3d", i).replace(" ", "0");
			int foundZs = 0;
			String newPattern = "";
			for (int j = 0; j < genPattern.length(); j++) {
				if (genPattern.charAt(j) == 'z') {
					newPattern += replace.charAt(foundZs);
					foundZs++;
				} else {
					newPattern += genPattern.charAt(j);
				}
			}
			int works = testPattern(newPattern);
			if (works > max) {
				max = works;
				attained = i;
			}
		}
		System.out.println("Max for pattern " + genPattern + " is " + max + " attained at " + String.format("%3d", attained).replace(" ", "0"));
		return max;
	}
	
	public static void main(String[] args) {
		String[] patternsfivedig = {"zxxxz", "xzxxz", "xxzxz", "xxxzz"};
		for (String s : patternsfivedig) {
			testGenPattern(s, 2);
		}
		String[] patternssixdig = {"zzxxxz", "zxzxxz", "zxxzxz", "zxxxzz", "xzzxxz", "xzxzxz", "xzxxzz", "xxzzxz", "xxzxzz", "xxxzzz"};
		for (String s : patternssixdig) {
			testGenPattern(s, 3);
		}
	}
}
