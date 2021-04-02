package problems0xx;
import java.util.ArrayList;

import commonMethods.CMath;

public class Problem34 {
	private static int sumFactsDigs(int num) {
		String asString = Integer.toString(num);
		int sum = 0;
		for (int i = 0; i < asString.length(); i++) {
			sum += CMath.factorial(Integer.parseInt(Character.toString(asString.charAt(i))));
		}
		return sum;
	}
	
	public static int sumAllEqualFacts() {
		ArrayList<Integer> allInts = new ArrayList<Integer>();
		for (int i = 3; i < 2540160; i++) {
			if (i == sumFactsDigs(i)) {
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
		System.out.println(sumAllEqualFacts());
	}
}
