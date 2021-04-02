package problems0xx;

public class Problem14 {
	private static int numSteps(int n) {
		int count = 1;
		long curr = n;
		while (curr != 1) {
			if (curr % 2 == 0) {
				curr /= 2;
			} else {
				curr *= 3;
				curr += 1;
			}
			count++;
		}
		return count;
	}
	
	public static int findMax(int max) {
		int currMax = 0;
		int attained = 0;
		for (int i = 1; i <= max; i++) {
			int steps = numSteps(i);
			if (steps > currMax) {
				currMax = steps;
				attained = i;
				System.out.println(currMax + " " + attained);
			}
		}
		System.out.println(currMax);
		return attained;
	}
	
	public static void main(String[] args) {
		System.out.println(findMax(1000000));
	}
}
