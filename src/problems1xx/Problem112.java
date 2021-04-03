package problems1xx;

public class Problem112 {
	public static boolean isBouncy(int num) {
		boolean inc = false;
		boolean dec = false;
		String s = Integer.toString(num);
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) > s.charAt(i - 1)) {
				if (dec) {
					return true;
				}
				inc = true;
			}
			if (s.charAt(i) < s.charAt(i - 1)) {
				if (inc) {
					return true;
				}
				dec = true;
			}
		}
		return false;
	}
	
	public static int firstToPerc(int perc) {
		int curr = 1;
		int numBouncy = 0;
		while (true) {
			if (isBouncy(curr)) {
				numBouncy++;
			} 
			double threshold = ((double) curr) / 100 * perc;
			if (threshold == (int) threshold) {
				if (numBouncy == (int) threshold) {
					return curr;
				}
				if (numBouncy > threshold) {
					System.out.println("a");
				}
			}
			curr++;
		}
	}
	
	public static int firstTo99() {
		int curr = 1000000;
		int numBouncy = 987038;
		while (true) {
			if (isBouncy(curr)) {
				numBouncy++;
			} 
			if (curr % 100 == 0) {
				if (numBouncy == curr / 100 * 99) {
					return curr;
				}
				if (numBouncy > curr / 100 * 99) {
					System.out.println("a");
				}
			}
			curr++;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(firstToPerc(99));
	}
}
