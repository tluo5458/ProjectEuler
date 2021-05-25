package problems1xx;

import commonMethods.Timing;

public class Problem126 {
	public static int cover(int x, int y, int z, int layers) {
		return 2 * (x * y + x * z + y * z) + 4 * (layers - 1) * (x + y + z + layers - 2);
	}
	
	public static int[] allA(int limit) {
		int[] as = new int[limit];
		for (int x = 1; cover(x, x, x, 1) <= limit; x++) {
			for (int y = x; cover(x, x, y, 1) <= limit; y++) {
				for (int z = y; cover(x, y, z, 1) <= limit; z++) {
					for (int n = 1; cover(x, y, z, n) <= limit; n++) {
						as[cover(x, y, z, n) - 1]++;
					}
				}
			}
		}
		return as;
	}
	
	public static int firstA(int a) {
		int[] as = allA(a * 50);
		for (int i = 0; i < as.length; i++) {
			if (as[i] == a) {
				return i + 1;
			}
		}
		return 0;
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(firstA(1000));
		t.end();
	}
}
