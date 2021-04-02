package problems0xx;

public class Problem86 {
	public static int numPairs(int max, int tot) {
		return (max + 1 - Math.abs(tot - max - 1)) / 2;
	}
	
	public static int numIntRoutes(int max) {
		int ret = 0;
		int maxDiag = (int) Math.sqrt(5 * max * max);
		int maxSq = max * max;
		for (int i = maxDiag; i > max; i--) {
			int diff = i * i - maxSq;
			int sqrt = (int) Math.sqrt(diff);
			if (sqrt * sqrt == diff) {
				ret += numPairs(max, sqrt);
			}
		}
		return ret;
	}
	
	public static int minBoundAbove(int totSols) {
		int total = 0;
		int i = 0;
		while (total <= totSols) {
			i++;
			total += numIntRoutes(i);
		}
		return i;
	}
	
	public static int totCuboids(int bound) {
		int total = 0;
		for (int i = 1; i <= bound; i++) {
			total += numIntRoutes(i);
		}
		return total;
	}
	
	public static void main(String[] args) {
		System.out.println(minBoundAbove(1000000));
		System.out.println(totCuboids(1818));
	}
}
