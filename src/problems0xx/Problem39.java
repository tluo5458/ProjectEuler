package problems0xx;
import java.util.HashMap;

public class Problem39 {
	public static int findMaxPerim(int limit) {
		HashMap<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
		for (int i = 12; i <= limit; i++) {
			occurrences.put(i, 0);
		}
		for (int a = 1; a <= limit / 2; a++) {
			for (int b = a; b <= limit / 2; b++) {
				double c = Math.sqrt(a * a + b * b);
				if (c == (int) c) {
					int perim = a + b + (int) c;
					if (perim <= limit) {
						occurrences.put(perim, occurrences.get(perim) + 1);
					}
				}
			}
		}
		int currMax = 0;
		int attained = 0;
		for (Integer i : occurrences.keySet()) {
			if (occurrences.get(i) > currMax) {
				attained = i;
				currMax = occurrences.get(i);
			}
		}
		System.out.println(occurrences.get(attained));
		return attained;
	}
	
	public static void main(String[] args) {
		System.out.println(findMaxPerim(1000));
	}
}
