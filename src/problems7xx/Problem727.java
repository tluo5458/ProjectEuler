package problems7xx;

import commonMethods.CMath;
import commonMethods.Timing;

public class Problem727 {
	public static double distance(int r1, int r2, int r3) {
		double rd1 = (double) r1;
		double rd2 = (double) r2;
		double rd3 = (double) r3;
		
		// A = (0, 0), B = (bx, by), C = (r1 + r3, 0), A is center of circle with radius r1, B circle r2, and C circle r3 
		double bx = rd1 + rd2 * (rd1 - rd3) / (rd1 + rd3);
		double by = Math.sqrt(4 * rd1 * rd2 * rd3 * (rd1 + rd2 + rd3) / (rd1 + rd3) / (rd1 + rd3));
		
		// (ix, iy) is the incenter of ABC, equivalent to D in the problem
		double ix = rd1;
		double iy = -bx / by * (rd1 - rd1 / (rd1 + rd2) * bx) + rd1 / (rd1 + rd2) * by;
		
		// r4 is the radius of the inner soddy circle
		double r4 = rd1 * rd2 * rd3 / (rd1 * rd2 + rd2 * rd3 + rd1 * rd3 + 2 * Math.sqrt(rd1 * rd2 * rd3 * (rd1 + rd2 + rd3)));
		// (ox, oy) is E in the problem
		double ox = ((rd1 - rd3) * (rd1 + rd3 + 2 * r4) + (rd1 + rd3) * (rd1 + rd3)) / (2 * (rd1 + rd3));
		double oy = Math.sqrt((rd1 + r4) * (rd1 + r4) - ox * ox);
		
		// dist(D, E)
		return Math.sqrt((ox - ix) * (ox - ix) + (oy - iy) * (oy - iy));
	}
	
	public static double avg(int max) {
		double total = 0;
		int num = 0;
		for (int r1 = 1; r1 <= max; r1++) {
			for (int r2 = r1 + 1; r2 <= max; r2++) {
				for (int r3 = r2 + 1; r3 <= max; r3++) {
					// gcd(r1, r2, r3) = 1
					if (CMath.gcd(CMath.gcd(r1, r2), CMath.gcd(r1, r3)) == 1) {
						num++;
						total += distance(r1, r2, r3);
					}
				}
			}
		}
		return total / num;
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(avg(100));
		t.end();
	}
}
