package problems0xx;
import java.util.ArrayList;

public class Problem30 {
	private static int sumFifthPowsDigs(int num) {
		String asString = Integer.toString(num);
		int sum = 0;
		for (int i = 0; i < asString.length(); i++) {
			sum += Math.pow(Integer.parseInt(Character.toString(asString.charAt(i))), 5);
		}
		return sum;
	}
	
	public static int sumAllEqualPows() {
		ArrayList<Integer> allInts = new ArrayList<Integer>();
		for (int i = 2; i < 354294; i++) {
			if (i == sumFifthPowsDigs(i)) {
				allInts.add(i);
			}
		}
		int sum = 0;
		for (Integer i : allInts) {
			sum += i;
		}
		System.out.println(allInts);
		return sum;
	}
	
	public static void main(String[] args) {
		System.out.println(sumAllEqualPows());
	}
}
