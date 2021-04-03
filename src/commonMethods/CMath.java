package commonMethods;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class CMath {
	public static boolean isSquare(int num) {
		int sqrt = (int) Math.sqrt(num);
		return sqrt * sqrt == num;
	}
	
	public static boolean isPrime(int num) {
		if (num < 2) {
			return false;
		}
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static ArrayList<Integer> primesUnder(int n) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		if (n < 2) {
			return ret;
		}
		ret.add(2);
		if (n == 2) {
			return ret;
		}
		for (int i = 3; i <= n; i += 2) {
			ret.add(i);
		}
		int curr = 1;
		while (ret.get(curr) * ret.get(curr) < n) {
			ArrayList<Integer> toRemove = new ArrayList<Integer>();
			for (int i = curr + 1; i < ret.size(); i++) {
				if (ret.get(i) % ret.get(curr) == 0) {
					toRemove.add(i);
				}
			}
			for (int i = toRemove.size() - 1; i >= 0; i--) {
				ret.remove((int) toRemove.get(i));
			}
			curr++;
		}
		return ret;
		
	}
	
	public static int factorial(int n) {
		int ret = 1;
		for (int i = 1; i <= n; i++) {
			ret *= i;
		}
		return ret;
	}
	
	public static BigInteger BIFactorial(int n) {
		BigInteger ret = BigInteger.ONE;
		for (int i = 2; i <= n; i++) {
			ret = ret.multiply(new BigInteger(Integer.toString(i)));
		}
		return ret;
	}
	
	public static BigInteger BIPerm(int n, int r) {
		if (r < 0 || r > n) {
			return BigInteger.ZERO;
		}
		BigInteger ret = new BigInteger(Integer.toString(n));
		for (int i = n - 1; i > n - r; i--) {
			ret = ret.multiply(new BigInteger(Integer.toString(i)));
		}
		return ret;
	}
	
	public static BigInteger BICombo(int n, int r) {
		if (r < 0 || r > n) {
			return BigInteger.ZERO;
		}
		return BIPerm(n, r).divide(BIFactorial(r));
	}
	
	public static String decBaseFac(int num, int max) {
		String fin = "";
		int temp = num;
		for (int i = max; i > 0; i--) {
			int fact = factorial(i);
			int quo = temp / fact;
			temp -= quo * fact;
			fin += Integer.toString(quo);
		}
		return fin;
	}
	
	public static String numToPerm(int num, int max) {
		ArrayList<Integer> digs = new ArrayList<Integer>();
		for (int i = 0; i <= max; i++) {
			digs.add(i);
		}
		String curr = "";
		String numS = decBaseFac(num, max);
		while (numS.length() <= max - 1) {
			numS = "0" + numS;
		}
		for (int i = 0; i <= max - 1; i++) {
			int currDig = Integer.parseInt(Character.toString(numS.charAt(i)));
			curr += Integer.toString(digs.get(currDig));
			digs.remove(currDig);
		}
		curr += digs.get(0);
		return curr;
	}
	
	public static HashSet<Integer> permutations(int num) {
		char[] chars = Integer.toString(num).toCharArray();
		HashSet<Integer> ret = new HashSet<Integer>();
		for (int i = 0; i < factorial(chars.length); i++) {
			String curr = numToPerm(i, chars.length - 1);
			String fin = "";
			for (int j = 0; j < curr.length(); j++) {
				fin += chars[Integer.parseInt(curr.substring(j, j + 1))];
			}
			int after = Integer.parseInt(fin);
			if (Integer.toString(after).length() == chars.length) {
				ret.add(after);
			}
		}
		return ret;
	}
	
	public static HashSet<ArrayList<Integer>> combinations(int n, int r) {
		HashSet<ArrayList<Integer>> in = new HashSet<ArrayList<Integer>>();
		in.add(new ArrayList<Integer>());
		return combHelper(in, n, r, 0);
	}
	
	private static HashSet<ArrayList<Integer>> combHelper(HashSet<ArrayList<Integer>> alr, int n, int rem, int foundInt) {
		HashSet<ArrayList<Integer>> ret = new HashSet<ArrayList<Integer>>();
		if (rem == 0) {
			return alr;
		}
		for (int i = foundInt + 1; i <= n - rem + 1; i++) {
			HashSet<ArrayList<Integer>> alrI = new HashSet<ArrayList<Integer>>();
			for (ArrayList<Integer> s : alr) {
				ArrayList<Integer> k = new ArrayList<Integer>();
				for (int j : s) {
					k.add(j);
				}
				k.add(i);
				alrI.add(k);
			}
			ret.addAll(combHelper(alrI, n, rem - 1, i));
		}
		return ret;
	}
	
	public static ArrayList<Integer> primeFactors(int n) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		while (n % 2 == 0) {
			ret.add(2);
			n /= 2;
		}
		int curr = 3;
		while (n != 1) {
			if (n % curr == 0) {
				ret.add(curr);
				n /= curr;
			} else {
				curr += 2;
			}
		}
		return ret;
	}
	
	public static HashMap<Integer, Integer> primeCount(int n) {
		ArrayList<Integer> factors = primeFactors(n);
		HashMap<Integer, Integer> primeCount = new HashMap<Integer, Integer>();
		int curr = 2;
		int count = 0;
		for (int i : factors) {
			if (i == curr) {
				count++;
			} else {
				if (count != 0) {
					primeCount.put(curr, count);
				}
				count = 1;
				curr = i;
			}
		}
		primeCount.put(curr, count);
		return primeCount;
	}
	
	public static int phi(int n) {
		int ret = n;
		ArrayList<Integer> factors = primeFactors(n);
		HashSet<Integer> unique = new HashSet<Integer>();
		for (Integer i : factors) {
			unique.add(i);
		}
		for (Integer i : unique) {
			ret *= (i - 1);
			ret /= i;
		}
		return ret;
	}
	
	public static boolean isPandigitalNoZero(String a) {
		for (int i = 1; i < 10; i++) {
			if (!a.contains(Integer.toString(i))) {
				return false;
			}
		}
		if (a.length() == 9) {
			return true;
		}
		return false;
	}
	
	public static boolean isPandigitalWZero(String a) {
		for (int i = 0; i < 10; i++) {
			if (!a.contains(Integer.toString(i))) {
				return false;
			}
		}
		if (a.length() == 10) {
			return true;
		}
		return false;
	}
	
	public static int gcd(int num1, int num2) {
		num1 = Math.abs(num1);
		num2 = Math.abs(num2);
		if (num1 == 0) {
			return num2;
		}
		if (num2 == 0) {
			return num1;
		}
		while (num1 != num2) {
			if (num1 > num2)
				num1 = num1 - num2;
			else
				num2 = num2 - num1;
		}
		return num2;
	}

	public static boolean isPalindrome(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
				return false;
			}
		}
		return true;
	}
	
	public static ArrayList<Integer> primes(int max) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		if (max >= 2) {
			ret.add(2);
		}
		for (int i = 3; i <= max; i += 2) {
			if (isPrime(i)) {
				ret.add(i);
			}
		}
		return ret;
	}
	
	public static String toRoman(int num) {
		int[] vals = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
		String[] symbs = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
		StringBuilder fin = new StringBuilder();
		int curr = num;
		for (int i = 0; i < 13; i++) {
			int count = curr / vals[i];
			curr -= count * vals[i];
			for (int j = 0; j < count; j++) {
				fin.append(symbs[i]);
			}
		}
		return fin.toString();
	}
	
	public static int romanToInt(String roman) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('M', 1000);
		map.put('D', 500);
		map.put('C', 100);
		map.put('L', 50);
		map.put('X', 10);
		map.put('V', 5);
		map.put('I', 1);
		
		int ret = 0;
		char[] chars = roman.toCharArray();
		for (int i = 0; i < chars.length - 1; i++) {
			if (map.get(chars[i]) >= map.get(chars[i + 1])) {
				ret += map.get(chars[i]);
			} else {
				ret -= map.get(chars[i]);
			}
		}
		ret += map.get(chars[chars.length - 1]);
		return ret;
	}
	
	public static int polygonalNumber(int n, int sides) {
		int start = n * (n + 1) / 2;
		int inc = n * (n - 1) / 2;
		return start + (sides - 3) * inc;
	}
	
	public static ArrayList<BigInteger> partitionsBelow(int n) {
		ArrayList<BigInteger> ret = new ArrayList<BigInteger>();
		ret.add(BigInteger.ONE);
		for (int i = 1; i < n; i++) {
			BigInteger add = BigInteger.ZERO;
			int curr = 1;
			while (polygonalNumber(curr, 5) <= i) {
				if (curr % 2 == 0) {
					add = add.subtract(ret.get(i - polygonalNumber(curr, 5)));
				} else {
					add = add.add(ret.get(i - polygonalNumber(curr, 5)));
				}
				if (curr < 0) {
					curr--;
				}
				curr *= -1;
			}
			ret.add(add);
		}
		return ret;
	}
	
	public static int properFactorSum(int num) {
		int total = 1;
		HashMap<Integer, Integer> primeCounts = primeCount(num);
		for (Integer i : primeCounts.keySet()) {
			total *= (Math.pow(i, primeCounts.get(i) + 1) - 1) / (i - 1);
		}
		return total - num;
	}
	
	public static String decToBin(int k) {
		String end = "";
		while (k > 0) {
			end = Integer.toString(k % 2) + end;
			k /= 2;
		}
		return end;
	}
	
	public static String decToBin(int k, int digs) {
		char[] chars = new char[digs];
		int curr = digs - 1;
		while (k > 0) {
			chars[curr] = (char) ((k % 2) + 48);
			k /= 2;
			curr--;
		}
		while (curr >= 0) {
			chars[curr] = '0';
			curr--;
		}
		return String.valueOf(chars);
	}
}
