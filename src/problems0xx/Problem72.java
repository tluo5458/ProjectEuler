package problems0xx;

import bcd.BCD;

public class Problem72 {
	public static BCD numFracs(int denomLimit) {
		BCD ret = new BCD(0);
		int[] phi = new int[denomLimit - 1];
		for (int i = 0; i < phi.length; i++) {
			phi[i] = i + 2;
		}
		for (int i = 0; i < phi.length; i++) {
			if (phi[i] == i + 2) {
				for (int j = i; j < phi.length; j += (i + 2)) {
					phi[j] = phi[j] / (i + 2) * (i + 1);
				}
			}
		}
		for (int i = 0; i < phi.length; i++) {
			ret = BCD.addBCDs(ret, new BCD(phi[i]));
		}
		return ret;
	}
	
	public static void main(String[] args) {
		System.out.println(numFracs(1000000));
	}
}
