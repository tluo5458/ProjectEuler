package problems0xx;
import bcd.BCD;

public class Problem56 {
	public static int maxDigSum(int aLim, int bLim) {
		int currMax = 0;
		for (int i = 1; i < aLim; i++) {
			for (int j = 1; j < bLim; j++) {
				int curr = BCD.pow(i, j).digSum();
				if (curr > currMax) {
					currMax = curr;
					System.out.println(i + " " + j);
				}
			}
		}
		return currMax;
	}
	
	public static void main(String[] args) {
		System.out.println(maxDigSum(100, 100));
	}
}
