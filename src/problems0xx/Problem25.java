package problems0xx;
import java.util.ArrayList;

import bcd.BCD;

public class Problem25 {
	private static ArrayList<BCD> fibonaccis = new ArrayList<BCD>();

	private static void startArray() {
		if (fibonaccis.size() == 0) {
			fibonaccis.add(new BCD(0));
			fibonaccis.add(new BCD(1));
		}
	}

	private static BCD fibonacci(int n) {
		for (int i = fibonaccis.size(); i < n + 1; i++) {
			fibonaccis.add(BCD.addBCDs(fibonaccis.get(i - 1), fibonaccis.get(i - 2)));
		}
		return fibonaccis.get(n);
	}

	public static int firstFibWDigs(int numDigs) {
		startArray();
		int curr = 2;
		while (true) {
			if (fibonacci(curr).numDigs() >= numDigs) {
				return curr;
			}
			curr++;
		}
	}

	public static void main(String[] args) {
		System.out.println(firstFibWDigs(1000));
	}
}
