package problems0xx;
import bcd.BCD;

public class Problem97 {
	public static BCD lnmp() {
		BCD curr = new BCD(28433);
		BCD mod = new BCD(new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1});
		for (int i = 0; i < 7830457; i++) {
			curr = BCD.mod(BCD.multiplyBCDs(curr, new BCD(2)), mod);
		}
		return BCD.addBCDs(curr, new BCD(1));
	}
	
	public static void main(String[] args) {
		System.out.println(lnmp());
	}
}
