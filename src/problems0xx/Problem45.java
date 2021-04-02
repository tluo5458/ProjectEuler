package problems0xx;

public class Problem45 {
	private static boolean isTriangular(long num) {
		long disc = 1 + 8 * num;
		if (Math.sqrt(disc) == (int) Math.sqrt(disc)) {
			if ((int) Math.sqrt(disc) % 2 == 1) {
				System.out.println(((int) Math.sqrt(disc) - 1) / 2);
				return true;
			}
		}
		return false;
	}
	
	private static boolean isPentagonal(long num) {
		long disc = 1 + 24 * num;
		if (Math.sqrt(disc) == (int) Math.sqrt(disc)) {
			if ((int) Math.sqrt(disc) % 6 == 5) {
				System.out.println(((int) Math.sqrt(disc) + 1) / 6);
				return true;
			}
		}
		return false;
	}
	
	private static long hexagonal(int num) {
		return num * (2 * num - 1);
	}
	
	public static long getNextTriPenHex(int minHex) {
		int curr = minHex;
		while (true) {
			long currHex = hexagonal(curr);
			if (isTriangular(currHex) && isPentagonal(currHex)) {
				System.out.println(curr);
				return currHex;
			}
			curr++;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(getNextTriPenHex(144));
		
	}
}
