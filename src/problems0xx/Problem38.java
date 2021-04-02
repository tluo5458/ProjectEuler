package problems0xx;
import commonMethods.CMath;

public class Problem38 {
	private static String multString(int n) {
		String curr = "";
		int mult = 1;
		while (curr.length() < 9) {
			curr += Integer.toString(n * mult);
			mult++;
		}
		return curr;
	}
	
	public static int maxPand() {
		int currMax = 0;
		for (int i = 1; i < 10000; i++) {
			String s = multString(i);
			if (CMath.isPandigitalNoZero(s)) {
				System.out.println(i + " " + s);
				if (Integer.parseInt(s) > currMax) {
					currMax = Integer.parseInt(s);
				}
			}
		}
		return currMax;
	}
	
	public static void main(String[] args) {
		System.out.println(maxPand());
	}
}
