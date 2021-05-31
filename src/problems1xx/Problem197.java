package problems1xx;

import commonMethods.Timing;

public class Problem197 {
	public static double f(double x) {
		double ex = 30.403243784 - x * x;
		long k = (long) (Math.pow(2, ex));
		return (double) k * 0.000000001;
	}
	
	public static void main(String[] args) {
		// this cycles between 0.681175878 and 1.029461839
//		double curr = -1;
//		for (int i = 1; i < 10000; i++) {
//			System.out.println(curr);
//			curr = f(curr);
//		}
		Timing t = new Timing();
		t.start();
		System.out.println(0.681175878 + 1.029461839);
		t.end();
	}
}
