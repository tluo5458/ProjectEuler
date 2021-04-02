package problems0xx;
import java.util.HashMap;

import commonMethods.CMath;

public class Problem12 {
	private static int triangular(int n) {
		return n * (n + 1) / 2;
	}
	
	private static int numFactors(int n) {
		HashMap<Integer, Integer> primeCount = CMath.primeCount(n);
		int fin = 1;
		for (int i : primeCount.values()) {
			fin *= (i + 1);
		}
		return fin;
	}
	
	public static int findFirstTrianFactors() {
		int i = 1;
		while (true) {
			int curr = numFactors(triangular(i));
			if (curr > 500) {
				return i;
			}
			i++;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(findFirstTrianFactors());
	}
}
