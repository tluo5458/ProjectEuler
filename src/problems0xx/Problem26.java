package problems0xx;

public class Problem26 {

	private static int stripTwoFive(int num) {
		int temp = num;
		while (temp % 2 == 0) {
			temp /= 2;
		}
		while (temp % 5 == 0) {
			temp /= 5;
		}
		return temp;
	}
	
	private static int cycle(int num) {
		int relPrime = stripTwoFive(num);
		if (relPrime == 1) {
			return 0;
		}
		int mod = 10 % relPrime;
		int curr = mod;
		int count = 1;
		while (true) {
			if (curr == 1) {
				return count;
			}
			curr = (curr * mod) % relPrime;
			count++;
		}
	}
	
	public static int maxCycle(int limit) {
		int max = 0;
		int biggest = 0;
		for (int i = 1; i < limit; i++) {
			int curr = cycle(i);
			if (curr > max) {
				max = curr;
				biggest = i;
			}
		}
		return biggest;
	}
	
	public static void main(String[] args) {
		System.out.println(maxCycle(1000));
	}
}
