package problems0xx;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import commonMethods.CMath;
import graphs.Pair;

public class Problem98 {
	public static ArrayList<ArrayList<String>> wordsByLength() {
		String[] words;
		try {
			File txt = new File("MiscFiles/p098_words.txt");
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(txt);
			words = scan.nextLine().replace("\"", "").split(",");
		} catch (FileNotFoundException e) {
			System.out.println("oops");
			return null;
		}
		ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
		for (String s : words) {
			int len = s.length();
			while (len > ret.size()) {
				ret.add(new ArrayList<String>());
			}
			ret.get(len - 1).add(s);
		}
		return ret;
	}
	
	public static HashMap<String, ArrayList<String>> anagrams(ArrayList<String> words) {
		HashMap<String, ArrayList<String>> all = new HashMap<String, ArrayList<String>>();
		for (String s : words) {
			char[] chars = s.toCharArray();
			Arrays.sort(chars);
			String sorted = String.valueOf(chars);
			if (all.containsKey(sorted)) {
				all.get(sorted).add(s);
			} else {
				ArrayList<String> sVal = new ArrayList<String>();
				sVal.add(s);
				all.put(sorted, sVal);
			}
		}
		HashMap<String, ArrayList<String>> ret = new HashMap<String, ArrayList<String>>();
		for (String s : all.keySet()) {
			if (all.get(s).size() >= 2) {
				ret.put(s, all.get(s));
			}
		}
		return ret;
	}
	
	public static ArrayList<String> generateSquares(int length) {
		BigInteger low = BigInteger.TEN.pow(length - 1).sqrt();
		BigInteger high = BigInteger.TEN.pow(length).sqrt();
		if (length % 2 == 0) {
			low = low.add(BigInteger.ONE);
		} else {
			high = high.add(BigInteger.ONE);
		}
		ArrayList<String> ret = new ArrayList<String>();
		while (low.compareTo(high) < 0) {
			ret.add(0, low.pow(2).toString());
			low = low.add(BigInteger.ONE);
		}
		return ret;
	}
	
	public static Pair<String, HashMap<Character, Character>> pattern(String str) {
		HashMap<Character, Character> mapping = new HashMap<Character, Character>();
		char currMap = 'A';
		char[] chars = str.toCharArray();
		char[] ret = new char[chars.length];
		for (int i = 0; i < chars.length; i++) {
			if (mapping.containsKey(chars[i])) {
				ret[i] = mapping.get(chars[i]);
			} else {
				mapping.put(chars[i], currMap);
				ret[i] = currMap;
				currMap++;
			}
		}
		return new Pair<String, HashMap<Character, Character>>(String.valueOf(ret), mapping);
	}
	
	// assumes a and b are anagrams
	public static String anagramMap(String a, String b) {
		char[] ret = new char[b.length()];
		Pair<String, HashMap<Character, Character>> aPat = pattern(a);
		for (int i = 0; i < a.length(); i++) {
			ret[i] = aPat.getRight().get(b.charAt(i));
		}
		return String.valueOf(ret);
	}
	
	// both sqs and words are arraylists of strings that are anagrams of each other, the two arraylists have strings of the same size
	// sqs assumed to be an arraylist of strings representing integers in descending order
	public static BigInteger goodAnagram(ArrayList<String> sqs, ArrayList<String> words) {
		if (words.size() < 2) {
			return BigInteger.ZERO;
		}
		HashSet<ArrayList<Integer>> pairs = CMath.combinations(words.size(), 2);
		HashMap<String, Pair<String, String>> anagramMaps = new HashMap<String, Pair<String, String>>();
		for (ArrayList<Integer> pair : pairs) {
			anagramMaps.put(anagramMap(words.get(pair.get(0) - 1), words.get(pair.get(1) - 1)), new Pair<String, String>(words.get(pair.get(0) - 1), words.get(pair.get(1) - 1)));
			anagramMaps.put(anagramMap(words.get(pair.get(1) - 1), words.get(pair.get(0) - 1)), new Pair<String, String>(words.get(pair.get(1) - 1), words.get(pair.get(0) - 1)));
		}
		for (int i = 0; i < sqs.size(); i++) {
			for (int j = i + 1; j < sqs.size(); j++) {
				String map = anagramMap(sqs.get(i), sqs.get(j));
				if (anagramMaps.containsKey(map)) {
					System.out.println(sqs.get(i) + " " + sqs.get(j) + " " + anagramMaps.get(map));
					return new BigInteger(sqs.get(i));
				}
			}
		}
		return BigInteger.ZERO;
	}
	
	public static BigInteger maxAnagram(HashMap<String, ArrayList<String>> squares, HashMap<String, ArrayList<String>> words) {
		BigInteger max = BigInteger.ZERO;
		for (ArrayList<String> sqAna : squares.values()) {
			BigInteger highest = new BigInteger(sqAna.get(0));
			if (highest.compareTo(max) <= 0) {
				continue;
			}
			for (ArrayList<String> wordAna : words.values()) {
				BigInteger curr = goodAnagram(sqAna, wordAna);
				if (!curr.equals(BigInteger.ZERO)) {
					if (curr.compareTo(max) > 0) {
						max = curr;
					}
					break;
				}
			}
		}
		return max;
	}
	
	public static BigInteger fullMax() {
		ArrayList<ArrayList<String>> words = wordsByLength();
		for (int i = words.size() - 1; i >= 0; i--) {
			HashMap<String, ArrayList<String>> anagrams = anagrams(words.get(i));
			if (anagrams.size() == 0) {
				continue;
			}
			HashMap<String, ArrayList<String>> sqAnas = anagrams(generateSquares(i + 1));
			BigInteger check = maxAnagram(sqAnas, anagrams);
			if (!check.equals(BigInteger.ZERO)) {
				return check;
			}
		}
		return BigInteger.ZERO;
	}
	
	public static void main(String[] args) { 
		System.out.println(fullMax());
	}
}
