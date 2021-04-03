package problems1xx;

import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;

import commonMethods.CMath;

public class Problem103 {
	public static boolean satisfied(int[] list) {
		TreeMap<Integer, TreeSet<Integer>> cond2 = new TreeMap<Integer, TreeSet<Integer>>();
		HashSet<Integer> cond1 = new HashSet<Integer>();
		for (int i = 1; i < Math.pow(2, list.length); i++) {
			String bin = CMath.decToBin(i, list.length);
			int numOnes = 0;
			int currSum = 0;
			for (int j = 0; j < list.length; j++) {
				if (bin.charAt(j) == '1') {
					numOnes++;
					currSum += list[j];
				}
			}
			if (cond2.containsKey(numOnes)) {
				if (cond2.get(numOnes).contains(currSum)) {
					return false;
				} else {
					cond2.get(numOnes).add(currSum);
				}
			} else {
				TreeSet<Integer> next = new TreeSet<Integer>();
				next.add(currSum);
				cond2.put(numOnes, next);
			}
			if (cond1.contains(currSum)) {
				return false;
			} else {
				cond1.add(currSum);
			}
		}
		int prev = -1;
		for (int i : cond2.keySet()) {
			if (prev == -1) {
				prev = i;
				continue;
			} 
			if (cond2.get(i).first() <= cond2.get(prev).last()) {
				return false;
			}
			prev = i;
		}
		return true;
	}
	
	public static String optimum() {
		int[] attained = {20, 31, 38, 39, 40, 42, 45};
		int currMin = 255;
		for (int i1 = 17; i1 < 24; i1++) {
			for (int i2 = 28; i2 < 35; i2++) {
				for (int i3 = 35; i3 < 42; i3++) {
					for (int i4 = 36; i4 < 43; i4++) {
						for (int i5 = 37; i5 < 44; i5++) {
							for (int i6 = 39; i6 < 46; i6++) {
								for (int i7 = 42; i7 < 49; i7++) {
									int sum = i1 + i2 + i3 + i4 + i5 + i6 + i7;
									if (sum >= currMin) {
										break;
									}
									int[] curr = {i1, i2, i3, i4, i5, i6, i7};
									if (satisfied(curr)) {
										System.out.println("yay");
										currMin = sum;
										attained = curr;
										break;
									}
								}
							}
						}
					}
				}
			}
		}
		String ret = "";
		for (int i = 0; i < 7; i++) {
			ret += Integer.toString(attained[i]);
		}
		return ret;
	}
	
	public static void main(String[] args) {
		System.out.println(optimum());
	}
}
