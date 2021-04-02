package problems0xx;
import java.util.ArrayList;

import commonMethods.CMath;

public class Problem37 {
	private static ArrayList<Integer> truncations(int num) {
		String given = Integer.toString(num);
		ArrayList<Integer> ret = new ArrayList<Integer>();
		ret.add(num);
		for (int i = 1; i < given.length(); i++) {
			ret.add(Integer.parseInt(given.substring(i)));
			ret.add(Integer.parseInt(given.substring(0, i)));
		}
		return ret;
	}
	
	public static int find() {
		int curr = 10;
		int found = 0;
		int total = 0;
		while (true) {
			boolean good = true;
			for (Integer i : truncations(curr)) {
				if (!CMath.isPrime(i)) {
					good = false;
				}
			}
			if (good) {
				System.out.println(curr);
				found += 1;
				total += curr;
				if (found == 11) {
					return total;
				}
			}
			curr += 1;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(find());
	}
}
