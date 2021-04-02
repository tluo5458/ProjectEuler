package problems0xx;

public class Problem92 {
	private static int digSquareSum(int num) {
		int tot = 0;
		while (num > 0) {
			tot += (num % 10) * (num % 10);
			num /= 10;
		}
		return tot;
	}
	
	private static boolean arrive89(int num) {
		while (true) {
			if (num == 89) {
				return true;
			}
			if (num == 1) {
				return false;
			}
			num = digSquareSum(num);
		}
	}
	
	public static int count89(int limit) {
		int counter = 0;
		for (int i = 1; i <= limit; i++) {
			if (arrive89(i)) {
				counter++;
			}
		}
		return counter;
	}
	
	public static void main(String[] args) {
		System.out.println(count89(10000000));
	}
}
