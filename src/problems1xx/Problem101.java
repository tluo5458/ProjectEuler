package problems1xx;

import java.util.ArrayList;

public class Problem101 {
	public static long interpolate(ArrayList<Long> points, int x) {
		double curr = 0;
		for (int i = 0; i < points.size(); i++) {
			long num = 1;
			long den = 1;
			for (int j = 0; j < points.size(); j++) {
				if (j == i) {
					continue;
				}
				num *= (x - j - 1);
				den *= i - j;
			}
			num *= points.get(i);
			if (num % den != 0) {
				System.out.println("stinky");
			}
			curr += (double) num / (double) den;
		}
		return (long) Math.round(curr);
	}
	
	// f is the function
	public static long f(long x) {
//		return x * x * x;
		return 1 - x + x*x - x*x*x + x*x*x*x - x*x*x*x*x + x*x*x*x*x*x - x*x*x*x*x*x*x + x*x*x*x*x*x*x*x - x*x*x*x*x*x*x*x*x + x*x*x*x*x*x*x*x*x*x;
	}
	
	// degree is the degree of f
	public static long FITsum(int degree) {
		ArrayList<Long> points = new ArrayList<Long>();
		long tot = 0;
		for (int i = 1; i <= degree; i++) {
			points.add(f(i));
			long inter = interpolate(points, i + 1);
			tot += inter;
		}
		return tot;
	}
	
	public static void main(String[] args) {
		System.out.println(FITsum(10));
	}
}
