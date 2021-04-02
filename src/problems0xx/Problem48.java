package problems0xx;
import bcd.BCD;

public class Problem48 {
	public static BCD sumSelfPows(int limit) {
		BCD curr = new BCD(0);
		for (int i = 1; i <= limit; i++) {
			curr = BCD.addBCDs(curr, BCD.pow(i, i));
		}
		return curr;
	}
	
	public static void main(String[] args) {
		System.out.println(sumSelfPows(1000));
	}
}
