package commonMethods;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class CMath {
	public static boolean isSquare(int num) {
		int sqrt = (int) Math.sqrt(num);
		return sqrt * sqrt == num;
	}
	
	public static boolean isSquare(long num) {
		long sqrt = (long) Math.sqrt(num);
		return sqrt * sqrt == num;
	}
	
	public static boolean isPrime(int num) {
		if (num < 2) {
			return false;
		}
		if (num == 2) {
			return true;
		}
		if (num % 2 == 0) {
			return false;
		}
		for (int i = 3; i <= Math.sqrt(num); i += 2) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static ArrayList<Integer> primesUnder(int n) {
		// small n
		ArrayList<Integer> ret = new ArrayList<Integer>();
		if (n < 2) {
			return ret;
		}
		ret.add(2);
		if (n == 2) {
			return ret;
		}
		
		// sieve of eratosthenes
		// isComposite[i] checks if 2 * i + 3 is composite
		boolean[] isComposite = new boolean[(n - 1) / 2];
		int sqrt = (((int) Math.sqrt(n)) - 1) / 2;
		for (int i = 0; i <= sqrt; i++) {
			if (!isComposite[i]) {
				// for loop runs from p^2 and adds 2p each time
				// we have 2j + 3 = (2i + 3)^2 so j = 2i^2 + 6i + 3, and j += (2 * (2i + 3)) / 2 = 2i + 3
				for (int j = 2 * i * i + 6 * i + 3; j < isComposite.length; j += (2 * i + 3)) {
					isComposite[j] = true;
				}
			}
		}
		
		// iterate through to find primes
		for (int i = 0; i < isComposite.length; i++) {
			if (!isComposite[i]) {
				ret.add(2 * i + 3);
			}
		}
		return ret;
	}
	
	// n! for some integer n, only works for 0 <= n <= 11
	public static int factorial(int n) {
		int ret = 1;
		for (int i = 1; i <= n; i++) {
			ret *= i;
		}
		return ret;
	}
	
	// n! for some integer n, works for larger n but returns BigInteger
	public static BigInteger factorialBI(int n) {
		BigInteger ret = BigInteger.ONE;
		for (int i = 2; i <= n; i++) {
			ret = ret.multiply(new BigInteger(Integer.toString(i)));
		}
		return ret;
	}
	
	// nPr = n!/r! for n, r
	public static BigInteger permBI(int n, int r) {
		if (r < 0 || r > n) {
			return BigInteger.ZERO;
		}
		BigInteger ret = new BigInteger(Integer.toString(n));
		for (int i = n - 1; i > n - r; i--) {
			ret = ret.multiply(new BigInteger(Integer.toString(i)));
		}
		return ret;
	}
	
	// nCr = n!/(r!(n-r)!) for n, r
	public static BigInteger comboBI(int n, int r) {
		if (r < 0 || r > n) {
			return BigInteger.ZERO;
		}
		return permBI(n, r).divide(factorialBI(r));
	}
	
	// base factorial representation of num, max is number of digits in final representation (with padded zeros as necessary)
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
	
	// generates the num-th permutation lexicographically of the digits between 0 and max, inclusive
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
	
	// generates the permutations of the digits of num
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
	
	// generates all combinations of r items in [1, 2,..., n]
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
	
	// generates the prime factors of n, with repeats
	public static ArrayList<Integer> primeFactors(int n) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		if (n < 2) {
			return ret;
		}
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
	
	// returns hashmap of p -> e for all primes p that divide n, where p^e divides n but p^(e + 1) does not
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
	
	// returns treeset of all factors of a number n
	public static TreeSet<Integer> factors(int n) {
		TreeSet<Integer> ret = new TreeSet<Integer>();
		if (n < 1) {
			return ret;
		}
		ret.add(1);
		HashMap<Integer, Integer> primeCounts = primeCount(n);
		for (Integer p : primeCounts.keySet()) {
			TreeSet<Integer> temp = new TreeSet<Integer>();
			int k = p;
			for (int i = 1; i <= primeCounts.get(p); i++) {
				for (Integer j : ret) {
					temp.add(j * k);
				}
				k *= p;
			}
			ret.addAll(temp);
		}
		return ret;
	}
	
	public static TreeSet<BigInteger> factors(HashMap<Integer, Integer> primeCounts) {
		TreeSet<BigInteger> ret = new TreeSet<BigInteger>();
		ret.add(BigInteger.ONE);
		for (Integer p : primeCounts.keySet()) {
			TreeSet<BigInteger> temp = new TreeSet<BigInteger>();
			BigInteger prime = intToBI(p);
			BigInteger k = prime;
			for (int i = 1; i <= primeCounts.get(p); i++) {
				for (BigInteger j : ret) {
					temp.add(j.multiply(k));
				}
				k = k.multiply(prime);
			}
			ret.addAll(temp);
		}
		return ret;
	}
	
	// computes the euler phi function of n
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
	
	// checks if a contains each digit from 1 to 9 exactly once
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
	
	// checks if a contains each digit from 0 to 9 exactly once
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
	
	// computes gcd of num1 and num2 using euclidean algorithm
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
	
	// computes gcd of num1 and num2 (but they're longs now) using euclidean algorithm
	public static long gcd(long num1, long num2) {
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

	// checks if the string s is a palindrome
	public static boolean isPalindrome(String s) {
		for (int i = 0; i < s.length() / 2; i++) {
			if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
				return false;
			}
		}
		return true;
	}
	
	// finds the roman numeral representation of num
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
	
	// finds the integer value of a roman numeral string
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
	
	// generates the n-th sides-sided number
	public static int polygonalNumber(int n, int sides) {
		int start = n * (n + 1) / 2;
		int inc = n * (n - 1) / 2;
		return start + (sides - 3) * inc;
	}
	
	// finds partitions(i) for all i <= n
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
	
	// finds the sum of proper factors of num
	public static int properFactorSum(int num) {
		int total = 1;
		HashMap<Integer, Integer> primeCounts = primeCount(num);
		for (Integer i : primeCounts.keySet()) {
			total *= (Math.pow(i, primeCounts.get(i) + 1) - 1) / (i - 1);
		}
		return total - num;
	}
	
	// finds the binary representation of k
	public static String decToBin(int k) {
		String end = "";
		while (k > 0) {
			end = Integer.toString(k % 2) + end;
			k /= 2;
		}
		return end;
	}
	
	// finds the binary representation of k, digs is number of digits in final result (with padded zeros as necessary)
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
	
	public static BigInteger intToBI(int n) {
		return BigInteger.valueOf((long) n);
	}
	
	public static BigInteger modInvP(BigInteger a, BigInteger p) {
		if (a.mod(p).compareTo(BigInteger.ZERO) == 0) {
			return BigInteger.ZERO;
		}
		return a.modPow(p.subtract(new BigInteger("2")), p);
	}
	
	public static int digSumBI(BigInteger a) {
		char[] s = a.toString().toCharArray();
		int tot = 0;
		for (int i = 0; i < s.length; i++) {
			tot += s[i] - 48;
		}
		return tot;
	}
}
