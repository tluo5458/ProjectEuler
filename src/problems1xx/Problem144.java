package problems1xx;

import commonMethods.Timing;

public class Problem144 {
	public static int numBounces() {
		int bounces = 0;
		// slope and y-intercept of initial line
		double m = -19.7 / 1.4;
		double b = 10.1;
		// first intersection of the initial line with the ellipse
		double x = 0.0071073169499659030637;
		double y = 9.9999898972040512212;
		while (true) {
			double nextX;
			double nextY;
			if (m == Double.POSITIVE_INFINITY) {
				// if previous line is vertical, next coords will be (x, -y)
				nextX = x;
				nextY = -y;
			} else {
				// otherwise x will be reflected across -bm/(m^2 + 4)
				// easy to see by substitution + vieta's
				nextX = -2 * b * m / (m * m + 4) - x;
				nextY = m * nextX + b;
			}
			// debugging code
//			System.out.println(bounces + ": (" + nextX + ", " + nextY + "), Plugged in: " + (4 * nextX * nextX + nextY * nextY));
			
			// check if we're done
			if (-0.01 <= nextX && nextX <= 0.01 && nextY > 0) {
				return bounces;
			}
			// bounce again so we increment
			bounces++;
			if (nextY == 0) {
				// if we bounce on minor axis endpoints
				m *= -1;
				b *= -1;
			} else if (nextX == 0) {
				// if we bounce on bottom end
				m *= -1;
			} else {
				// (tempX, tempY) is the reflection of (x, y) across the perpendicular to the tangent line at (nextX, nextY)
				double tempX = 2 * (4 * nextX / nextY * x + y - 3 * nextY / 4) / (nextY / (4 * nextX) + 4 * nextX / nextY) - x;
				double tempY = (tempX - x) * (-4 * nextX) / nextY + y;
				if (tempX == nextX) {
					// if tempX is nextX then the next line is vertical
					m = Double.POSITIVE_INFINITY;
				} else {
					// otherwise the next line coincides with the one through (nextX, nextY) and (tempX, tempY)
					m = (tempY - nextY) / (tempX - nextX);
					// calculate y intercept using (nextX, nextY) to reduce possible rounding errors
					b = nextY - m * nextX;
				}
			}
			// set x and y to nextX, nextY
			x = nextX;
			y = nextY;
		}
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(numBounces());
		t.end();
	}
}
