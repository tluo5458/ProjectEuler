package problems0xx;

public class Problem64 {
	public static int period(int n) {
		double sqrt = Math.sqrt(n);
		int sqrtF = (int) sqrt;
		if (sqrtF * sqrtF == n) {
			return 0;
		}
		int ret = 0;
		int d = 1;
		int m = 0;
		int a = sqrtF;
		
		do {
			m = d * a - m;
			d = (n - m * m) / d;
			a = (int) ((sqrt + m) / d);
			ret++;
		} while (a != 2 * sqrtF);
		return ret;
	}
	
	public static int countOdds(int bound) {
		int ret = 0;
		for (int i = 2; i <= bound; i++) {
			ret += (period(i) % 2);
		}
		return ret;
	}
	
	public static void main(String[] args) {
		System.out.println(countOdds(10000));
	}
}
