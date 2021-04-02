package problems0xx;
import java.util.HashMap;

public class Problem17 {
	private static HashMap<Integer, Integer> ones = new HashMap<Integer, Integer>();
	private static HashMap<Integer, Integer> tens = new HashMap<Integer, Integer>();
	
	public static void setUpHashMap() {
		ones.put(0, 0);
		ones.put(1, 3);
		ones.put(2, 3);
		ones.put(3, 5);
		ones.put(4, 4);
		ones.put(5, 4);
		ones.put(6, 3);
		ones.put(7, 5);
		ones.put(8, 5);
		ones.put(9, 4);
		ones.put(10, 3);
		ones.put(11, 6);
		ones.put(12, 6);
		ones.put(13, 8);
		ones.put(14, 8);
		ones.put(15, 7);
		ones.put(16, 7);
		ones.put(17, 9);
		ones.put(18, 8);
		ones.put(19, 8);
		tens.put(0, 0);
		tens.put(2, 6);
		tens.put(3, 6);
		tens.put(4, 5);
		tens.put(5, 5);
		tens.put(6, 5);
		tens.put(7, 7);
		tens.put(8, 6);
		tens.put(9, 6);
	}
	
	public static int numLetters(int num) {
		int hundred = num / 100;
		int ten = (num / 10) % 10;
		int one = num % 10;
		int fin = 0;
		if (hundred > 0) {
			fin += (ones.get(hundred) + 7);
			if (num != hundred * 100) {
				fin += 3;
			}
		}
		if (ten == 1) {
			fin += ones.get(10 * ten + one);
		} else {
			fin += (tens.get(ten) + ones.get(one));
		}
		System.out.println(num + " " + fin);
		return fin;
	}
	
	public static int total() {
		setUpHashMap();
		int fin = 0;
		for (int i = 1; i < 1000; i++) {
			fin += numLetters(i);
		}
		fin += 11;
		return fin;
	}
	
	public static void main(String[] args) {
		System.out.println(total());
	}
}
