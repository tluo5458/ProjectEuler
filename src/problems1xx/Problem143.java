package problems1xx;

import java.util.HashSet;

import commonMethods.CMath;
import commonMethods.Timing;

public class Problem143 {
	public static long sumPQRs(int sumLim) {
		HashSet<Integer> sums = new HashSet<Integer>();
		for (long i = 1; i < sumLim / 3; i++) {
			for (long j = i + 1; j < (sumLim - i + 1) / 2; j++) {
				if (!CMath.isSquare(i * i + i * j + j * j)) {
					continue;
				}
				for (long k = j + 1; k <= sumLim - i - j; k++) {
					if (!CMath.isSquare(i * i + i * k + k * k)) {
						continue;
					}
					if (!CMath.isSquare(j * j + j * k + k * k)) {
						continue;
					}
					sums.add((int) (i + j + k));
				}
			}
		}
		long ret = 0;
		for (int i : sums) {
			ret += i;
		}
		return ret;
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(sumPQRs(120000));
		t.end();
	}
}
