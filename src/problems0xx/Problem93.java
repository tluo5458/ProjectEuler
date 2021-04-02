package problems0xx;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

import commonMethods.CMath;

public class Problem93 {
	public static double op(double curr, double nextDig, int op) {
		switch (op) {
		case 0:
			return curr + nextDig;
		case 1:
			return curr - nextDig;
		case 2:
			return curr * nextDig;
		case 3:
			if (nextDig == 0) {
				return 0;
			}
			return curr / nextDig;
		default:
			return 0;
		}
	}
	
	// patchwork extra solution for 4 digs being paired off and then op together
	public static TreeSet<Integer> twoPairPossibilities(int digs) {
		HashSet<Integer> perms = CMath.permutations(digs);
		TreeSet<Integer> ret = new TreeSet<Integer>();
		for (Integer perm : perms) {
			char[] digChars = Integer.toString(perm).toCharArray();
			int[] digArray = new int[4];
			for (int i = 0; i < 4; i++) {
				digArray[i] = digChars[i] - 48;
			}
			int[] pair1 = {digArray[0], digArray[1]};
			int[] pair2 = {digArray[2], digArray[3]};
			for (int op1 = 0; op1 < 4; op1++) {
				for (int op2 = 0; op2 < 4; op2++) {
					for (int opt = 0; opt < 4; opt++) {
						double left = op((double) pair1[0], pair1[1], op1);
						double right = op((double) pair2[0], pair2[1], op2);
						double fin = op(left, right, opt);
						if ((int) fin == fin && fin > 0) {
							ret.add((int) fin);
						}
					}
				}
			}
		}
		return ret;
	}
	
	// integer possibilities if operation tree is just 1 leaf on one side at every level
	// completely valid if number of digs is < 4
	public static TreeSet<Integer> orderPossibilities(int digs) {
		HashSet<Integer> perms = CMath.permutations(digs);
		TreeSet<Integer> ret = new TreeSet<Integer>();
		for (Integer i : perms) {
			TreeSet<Double> permRet = new TreeSet<Double>();
			char[] chars = Integer.toString(i).toCharArray();
			int[] digArray = new int[chars.length];
			for (int j = 0; j < chars.length; j++) {
				digArray[j] = ((int) chars[j]) - 48;
			}
			permRet.add((double) digArray[0]);
			for (int j = 1; j < digArray.length; j++) {
				TreeSet<Double> next = new TreeSet<Double>();
				for (Double k : permRet) {
					for (int op = 0; op < 4; op++) {
						next.add(op(k, digArray[j], op));
					}
				}
				permRet = next;
			}
			for (double d : permRet) {
				if ((int) d == d && d > 0) {
//					System.out.print(d + " ");
					ret.add((int) d);
				}
			}
		}
		return ret;
	}
	
	// true general solution would generate all possible binary trees with n leaves
	// this function works for digs with length 4 or less
	public static int numConsec(int digs) {
		int i = 1;
		TreeSet<Integer> possibilities = orderPossibilities(digs);
		if (Integer.toString(digs).length() == 4) {
			possibilities.addAll(twoPairPossibilities(digs));
		}
		while (true) {
			 if (!possibilities.contains(i)) {
				 return i - 1;
			 }
			 i++;
		 }
	}
	
	public static int maxConsec(int numDigs) {
		HashSet<ArrayList<Integer>> combs = CMath.combinations(9, numDigs);
		int max = 0;
		for (ArrayList<Integer> i : combs) {
			String in = "";
			for (int j : i) {
				in += Integer.toString(j);
			}
			int consec = numConsec(Integer.parseInt(in));
			if (consec >= max) {
				System.out.println(i + " " + consec);
				max = consec;
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		System.out.println(maxConsec(4));
	}
}
