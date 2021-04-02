package problems0xx;

import commonMethods.CMath;

public class Problem73 {
	public static int numFracs(int denomLimit) {
		int tot = 0;
		for (int i = 5; i <= denomLimit; i++) {
			for (int j = (i + 2) / 3; j <= i / 2; j++) {
				if (CMath.gcd(i, j) == 1) {
					tot++;
				}
			}
		}
		return tot;
	}
	
	public static void main(String[] args) {
		System.out.println(numFracs(8));
		System.out.println(numFracs(12000));
	}
}
