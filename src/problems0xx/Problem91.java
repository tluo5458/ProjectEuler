package problems0xx;

import commonMethods.CMath;

public class Problem91 {
	public static int coordsRight(int x, int y, int maxX, int maxY) {
		int gcd = CMath.gcd(x, y);
		int a = x / gcd;
		int b = y / gcd;
		return Math.min((maxX - x) / b , y / a);
	}
	
	public static int numTriangles(int maxX, int maxY) {
		int tot = 0;
		for (int i = 1; i <= maxX; i++) {
			for (int j = 1; j <= maxY; j++) {
				tot += coordsRight(i, j, maxX, maxY);
			}
		}
		return 2 * tot + 3 * maxX * maxY;
	}
	
	public static void main(String[] args) {
		System.out.println(numTriangles(2, 2));
		System.out.println(numTriangles(50, 50));
	}
}
