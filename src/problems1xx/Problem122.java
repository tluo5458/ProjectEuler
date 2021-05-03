package problems1xx;

import java.util.Arrays;

public class Problem122 {
	public static int[] minExps(int lim) {
		class Solver {
			private int lim;
			private int[] cost;
			private int[] path;
			
			Solver(int limit) {
				lim = limit;
			}
			
			public int[] solve() {
				cost = new int[lim];
				path = new int[lim + 1];
				
				for (int i = 0; i < lim; i++) {
					cost[i] = Integer.MAX_VALUE;
				}
				backtrack(1, 0);
				return cost;
			}
			
			private void backtrack(int power, int depth) {
				if (power > lim || depth > cost[power - 1]) {
					return;
				}
				cost[power - 1] = depth;
				path[depth] = power;
				for (int i = depth; i >= 0; i--) {
					backtrack(power + path[i], depth + 1);
				}
			}		
		}
		Solver s = new Solver(lim);
		return s.solve();
	}
	
	public static int totExps(int lim) {
		int[] costs = minExps(lim);
		int tot = 0;
		for (int i = 0; i < lim; i++) {
			tot += costs[i];
		}
		return tot;
	}
	
	public static void main(String[] args) {
		System.out.println(totExps(200));
	}
}
