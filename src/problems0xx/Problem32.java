package problems0xx;
import java.util.HashSet;

import commonMethods.CMath;

public class Problem32 {
	private static boolean isPandigital(int a, int b) {
		int prod = a * b;
		String all = Integer.toString(a) + Integer.toString(b) + Integer.toString(prod);
		return CMath.isPandigitalNoZero(all);
	}
	
	public static int allPandigital() {
		HashSet<Integer> products = new HashSet<Integer>();
		//1 * 4 = 4
		for (int i = 2; i < 10; i++) {
			for (int j = 1000; j < 10000; j++) {
				if (isPandigital(i, j)) {
					System.out.println(i + " " + j + " " + i*j);
					products.add(i * j);
				}
			}
		}
		//2 * 3 = 4
		for (int i = 10; i < 100; i++) {
			for (int j = 100; j < 1000; j++) {
				if (isPandigital(i, j)) {
					System.out.println(i + " " + j + " " + i*j);
					products.add(i * j);
				}
			}
		}
		int sum = 0;
		for (Integer i : products) {
			sum += i;
		}
		System.out.println(products);
		return sum;
	}
	
	public static void main(String[] args) {
		System.out.println(allPandigital());
	}
}
