package problems1xx;

import commonMethods.Timing;

public class Problem149 {
	public static int maxSubseq(int[] seq) {
		if (seq.length == 1) {
			return Math.max(seq[0], 0);
		}
		int max = 0;
		int[] maxUpTo = new int[seq.length];
		if (seq[0] > 0) {
			maxUpTo[0] = seq[0];
			max = seq[0];
		} else {
			maxUpTo[0] = 0;
		}
		for (int i = 1; i < seq.length; i++) {
			if (seq[i] + maxUpTo[i - 1] > 0) {
				maxUpTo[i] = seq[i] + maxUpTo[i - 1];
				if (maxUpTo[i] > max) {
					max = maxUpTo[i];
				}
			} else {
				maxUpTo[i] = 0;
			}
		}
		return max;
	}

	// grid is assumed to be square because i don't want to deal with literal corner cases
	public static int maxSubseqGrid(int[][] grid) {
		int max = 0;
		// horizontal
		for (int i = 0; i < grid.length; i++) {
			int sub = maxSubseq(grid[i]);
			if (sub > max) {
				max = sub;
			}
		}
		// vertical
		for (int i = 0; i < grid[0].length; i++) {
			int[] curr = new int[grid.length];
			for (int j = 0; j < grid.length; j++) {
				curr[j] = grid[j][i];
			}
			int sub = maxSubseq(curr);
			if (sub > max) {
				max = sub;
			}
		}
		// downright across top row
		for (int i = 0; i < grid.length; i++) {
			int[] curr = new int[grid.length - i];
			for (int j = 0; j < grid.length - i; j++) {
				curr[j] = grid[j][i + j];
			}
			int sub = maxSubseq(curr);
			if (sub > max) {
				max = sub;
			}
		}
		// downright down left edge
		for (int i = 1; i < grid.length; i++) {
			int[] curr = new int[grid.length - i];
			for (int j = 0; j < grid.length - i; j++) {
				curr[j] = grid[i + j][j];
			}
			int sub = maxSubseq(curr);
			if (sub > max) {
				max = sub;
			}
		}
		// downleft across top row
		for (int i = 0; i < grid.length; i++) {
			int[] curr = new int[i + 1];
			for (int j = 0; j <= i; j++) {
				curr[j] = grid[i - j][j];
			}
			int sub = maxSubseq(curr);
			if (sub > max) {
				max = sub;
			}
		}
		// downleft down right edge
		for (int i = 0; i < grid.length - 1; i++) {
			int[] curr = new int[i + 1];
			for (int j = 0; j <= i; j++) {
				curr[j] = grid[grid.length - 1 - i + j][grid.length - 1 - j];
			}
			int sub = maxSubseq(curr);
			if (sub > max) {
				max = sub;
			}
		}
		return max;
	}
	
	// lagged fibonacci generator as stated in the problem
	public static int[][] generateGrid() {
		int[][] ret = new int[2000][2000];
		for (int i = 1; i <= 55; i++) {
			long sk = 100003L - 200003L * i + 300007L * i * i * i;
			sk = sk % 1000000;
			// ensure sk is positive, then mod again
			sk = (sk + 1000000) % 1000000;
			ret[0][i - 1] = ((int) sk) - 500000;
		}
		for (int i = 55; i < 4000000; i++) {
			int s24 = ret[(i - 24) / 2000][(i - 24) % 2000];
			int s55 = ret[(i - 55) / 2000][(i - 55) % 2000];
			int sk = (s24 + s55 + 1000000) % 1000000 - 500000;
			ret[i / 2000][i % 2000] = sk;
		}
		return ret;
	}
	
	public static int ans() {
		return maxSubseqGrid(generateGrid());
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(ans());
		t.end();
	}
}
