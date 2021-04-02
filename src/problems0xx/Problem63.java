package problems0xx;
import bcd.BCD;

public class Problem63 {
	public static int numSameDigPow() {
		int count = 0;
		for (int i = 1; i < 10; i++) {
			for (int j = 1; j < 22; j++) {
				if (BCD.pow(i, j).numDigs() == j) {
					count++;
				}
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		System.out.println(numSameDigPow());
	}
}
