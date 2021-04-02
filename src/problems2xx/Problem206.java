package problems2xx;

import bcd.BCD;

public class Problem206 {
	public static int findSquare() {
		for (int i = 10101010; i < 13890266; i++) {
			int curr1 = i * 100 + 30;
			int curr2 = i * 100 + 70;
			if (BCD.pow(curr1, 2).toString().matches("1.2.3.4.5.6.7.8.9.0")) {
				return curr1;
			}
			if (BCD.pow(curr2, 2).toString().matches("1.2.3.4.5.6.7.8.9.0")) {
				return curr2;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		System.out.println(findSquare());
		System.out.println(BCD.pow(1389019170, 2));
	}
}
