package problems0xx;

import java.util.ArrayList;
import java.util.HashSet;

public class Problem88 {
	public static int[] productSums(int limit) {
		int[] ret = new int[limit - 1];
		ArrayList<Integer> current = new ArrayList<Integer>();
		current.add(2);
		int currProd = 2;
		int currSum = 2;
		while (current.get(0) <= Math.sqrt(2 * limit)) {
			int k = currProd - currSum + current.size();
			if (k <= limit && k > 1) {
				if (ret[k - 2] == 0) {
					ret[k - 2] = currProd;
				} else {
					if (currProd < ret[k - 2]) {
						ret[k - 2] = currProd;
					}
				}
			}
			if (currProd <= 2 * limit) {
				currProd *= 2;
				currSum += 2;
				current.add(2);
			} else {
				int last = current.get(current.size() - 1);
				if (currProd / last * (last + 1) <= 2 * limit) {
					current.set(current.size() - 1, last + 1);
					currProd = currProd / last * (last + 1);
					currSum++;
				} else {
					currProd /= current.get(current.size() - 1);
					currSum -= current.get(current.size() - 1);
					current.remove(current.size() - 1);
					int newLast = current.get(current.size() - 1);
					current.set(current.size() - 1,  newLast + 1);
					currProd = currProd / newLast * (newLast + 1);
					currSum++;
				}
			}
		}
		return ret;
	}
	
	public static int totalSum(int limit) {
		int[] sums = productSums(limit);
		HashSet<Integer> uniques = new HashSet<Integer>();
		int tot = 0;
		for (int i : sums) {
			uniques.add(i);
		}
		for (int i : uniques) {
			tot += i;
		}
		return tot;
	}
	
	public static void main(String[] args) {
		long start = System.nanoTime();
		System.out.println(totalSum(12000));
		long end = System.nanoTime();
		System.out.println((end - start) / 1000000.0);
	}
}
