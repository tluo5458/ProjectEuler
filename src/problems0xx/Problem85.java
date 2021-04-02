package problems0xx;

import graphs.Pair;

public class Problem85 {
	public static int choose2(int n) {
		return n * (n - 1) / 2;
	}
	
	public static int[] getChooses(int bound) {
		int num = (int) Math.sqrt(bound) * 2;
		int[] ret = new int[num - 2];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = choose2(i + 2);
		}
		return ret;
	}
	
	public static int numRects(int x, int y) {
		return choose2(x) * choose2(y);
	}
	
	public static Pair<Integer, Integer> nearest(int tot) {
		int[] chooses = getChooses(tot);
		int minDiff = Integer.MAX_VALUE;
		Pair<Integer, Integer> attained = new Pair<Integer, Integer>(0, 0);
		int i = 0;
		while (chooses[i] * chooses[i] <= tot) {
			int j = i;
			while (chooses[i] * chooses[j] <= tot) {
				int diff = Math.abs(chooses[i] * chooses[j] - tot);
				if (diff < minDiff) {
					minDiff = diff;
					attained = new Pair<Integer, Integer>(i + 1, j + 1);
				}
				j++;
			}
			int diff = Math.abs(chooses[i] * chooses[j] - tot);
			if (diff < minDiff) {
				minDiff = diff;
				attained = new Pair<Integer, Integer>(i + 1, j + 1);
			}
			i++;
		}
		int diff = Math.abs(chooses[i] * chooses[i] - tot);
		if (diff < minDiff) {
			minDiff = diff;
			attained = new Pair<Integer, Integer>(i + 1, i + 1);
		}
		return attained;
	}
	
	public static void main(String[] args) {
		System.out.println(nearest(2000000));
	}
}
