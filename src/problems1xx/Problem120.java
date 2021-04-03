package problems1xx;

public class Problem120 {
	public static long sum(int maxA) {
		long a = (long) maxA;
		long h = a / 2;
		long sqSum = a * (a + 1) * (2 * a + 1) / 6;
		long aSum = a * (a + 1) / 2;
		long hSum = h * (h + 1);
		return sqSum - aSum - hSum;
	}
	
	public static void main(String[] args) {
		System.out.println(sum(1000));
	}
}
