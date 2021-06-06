package problems1xx;

import java.util.ArrayList;

import commonMethods.Timing;

public class Problem166 {
	// brute force solution based on sum
	public static ArrayList<ArrayList<ArrayList<Integer>>> quads() {
		ArrayList<ArrayList<ArrayList<Integer>>> ret = new ArrayList<ArrayList<ArrayList<Integer>>>();
		for (int i = 0; i <= 36; i++) {
			ArrayList<ArrayList<Integer>> sumI = new ArrayList<ArrayList<Integer>>();
			ret.add(sumI);
		}
		for (int i = 0; i < 10000; i++) {
			String curr = String.format("%04d", i);
			ArrayList<Integer> digs = new ArrayList<Integer>();
			int sum = 0;
			for (char c : curr.toCharArray()) {
				digs.add(c - 48);
				sum += (c - 48);
			}
			ret.get(sum).add(digs);
		}
		return ret;
	}
	
	// terrible brute force lol
	public static long ans() {
		ArrayList<ArrayList<ArrayList<Integer>>> quads = quads();
		long tot = 0;
		for (int i = 0; i < 19; i++) {
			System.out.println(i + " " + quads.get(i).size() + " " + tot);
			for (ArrayList<Integer> row1 : quads.get(i)) {
				// keep a running sum for each column and diagonal
				int col1 = row1.get(0);
				int col2 = row1.get(1);
				int col3 = row1.get(2);
				int col4 = row1.get(3);
				int tlbr = row1.get(0);
				int trbl = row1.get(3);
				for (ArrayList<Integer> row2 : quads.get(i)) {
					boolean good = true;
					col1 += row2.get(0);
					col2 += row2.get(1);
					col3 += row2.get(2);
					col4 += row2.get(3);
					tlbr += row2.get(1);
					trbl += row2.get(2);
					if (col1 > i || col2 > i || col3 > i || col4 > i || tlbr > i || trbl > i) {
						good = false;
					}
					if (col1 < i - 18 || col2 < i - 18 || col3 < i - 18 || col4 < i - 18 || tlbr < i - 18 || trbl < i - 18) {
						good = false;
					}
					if (!good) {
						col1 -= row2.get(0);
						col2 -= row2.get(1);
						col3 -= row2.get(2);
						col4 -= row2.get(3);
						tlbr -= row2.get(1);
						trbl -= row2.get(2);
						good = true;
						continue;
					}
					for (ArrayList<Integer> row3 : quads.get(i)) {
						col1 += row3.get(0);
						col2 += row3.get(1);
						col3 += row3.get(2);
						col4 += row3.get(3);
						tlbr += row3.get(2);
						trbl += row3.get(1);
						if (col1 != trbl || col4 != tlbr) {
							good = false;
						}
						if (col1 > i || col2 > i || col3 > i || col4 > i || tlbr > i || trbl > i) {
							good = false;
						}
						if (col1 < i - 9 || col2 < i - 9 || col3 < i - 9 || col4 < i - 9 || tlbr < i - 9 || trbl < i - 9) {
							good = false;
						}
						col1 -= row3.get(0);
						col2 -= row3.get(1);
						col3 -= row3.get(2);
						col4 -= row3.get(3);
						tlbr -= row3.get(2);
						trbl -= row3.get(1);
						if (!good) {
							good = true;
							continue;
						}
						if (i == 18) {
							tot++;
						} else {
							tot += 2;
						}
					}
					col1 -= row2.get(0);
					col2 -= row2.get(1);
					col3 -= row2.get(2);
					col4 -= row2.get(3);
					tlbr -= row2.get(1);
					trbl -= row2.get(2);
				}
			}
		}
		return tot;
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(ans());
		t.end();
	}
}
