package problems0xx;

import java.util.HashSet;
import java.util.TreeMap;

import commonMethods.CMath;

public class Problem95 {
	public static TreeMap<Integer, Integer> propFactSum(int limit) {
		TreeMap<Integer, Integer> ret = new TreeMap<Integer, Integer>();
		for (int i = 2; i <= limit; i++) {
			int sum = CMath.properFactorSum(i);
			if (sum > 1 && sum <= limit) {
				ret.put(i, sum);
			}
		}
		return ret;
	}
	
	public static int minChain(int limit) {
		TreeMap<Integer, Integer> props = propFactSum(limit);
		int maxChainLength = 0;
		int minInChain = 0;
		for (int i = 2; i <= limit; i++) {
			if (!props.containsKey(i)) {
				continue;
			}
			HashSet<Integer> found = new HashSet<Integer>();
			int curr = i;
			while (!found.contains(curr)) {
				found.add(curr);
				if (!props.containsKey(curr)) {
					break;
				}
				curr = props.get(curr);
			}
			if (curr == i) {
				if (found.size() > maxChainLength) {
					minInChain = i;
					maxChainLength = found.size();
				} else if (found.size() == maxChainLength) {
					if (i < minInChain) {
						minInChain = i;
					}
				}
			}
		}
		return minInChain;
	}
	
	public static void main(String[] args) {
		System.out.println(minChain(1000000));
//		int i = 14316;
//		do {
//			System.out.println(i);
//			i = CMath.properFactorSum(i);
//		} while (i != 14316);
	}
}
