package problems0xx;
import java.util.HashSet;

import bcd.BCD;

public class Problem29 {
	public static int numPows(int aLim, int bLim) {
		HashSet<BCD> pows = new HashSet<BCD>();
		for (int a = 2; a <= aLim; a++) {
			for (int b = 2; b <= bLim; b++) {
				pows.add(BCD.pow(a, b));
			}
		}
		return pows.size();
	}

	public static void main(String[] args) {
		System.out.println(numPows(100, 100));
	}
}
