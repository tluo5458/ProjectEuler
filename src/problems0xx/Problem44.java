package problems0xx;

public class Problem44 {
	private static boolean isPentagonal(int num) {
		int disc = 1 + 24 * num;
		if (Math.sqrt(disc) == (int) Math.sqrt(disc)) {
			if ((int) Math.sqrt(disc) % 6 == 5) {
				return true;
			}
		}
		return false;
	}
	
	private static int pentagonal(int n) {
		return n * (3*n - 1) / 2;
	}
	
	public static int minDiff(int limit) {
		int min = Integer.MAX_VALUE;
		for (int i = 1; i < limit; i++) {
			for (int j = i + 1; j < limit; j++) {
				if (isPentagonal(pentagonal(i) + pentagonal(j))) {
					if (isPentagonal(pentagonal(j) - pentagonal(i))) {
						if (pentagonal(j) - pentagonal(i) < min) {
							System.out.println(i + " " + j + " " + pentagonal(i) + " " + pentagonal(j));
							min = pentagonal(j) - pentagonal(i);
						}
					}
				}
			}
		}
		return min;
	}
	
	public static void main(String[] args) {
		System.out.println(minDiff(100000));
	}
}
