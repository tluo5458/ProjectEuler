package problems0xx;
import java.util.ArrayList;

public class Problem31 {
	public static int waysTwoPounds() {
		ArrayList<String> ways = new ArrayList<String>();
		for (int p200 = 0; p200 <= 1; p200++) {
			for (int p100 = 0; p100 <= 2; p100++) {
				for (int p50 = 0; p50 <= 4; p50++) {
					for (int p20 = 0; p20 <= 10; p20++) {
						for (int p10 = 0; p10 <= 20; p10++) {
							for (int p5 = 0; p5 <= 40; p5++) {
								for (int p2 = 0; p2 <= 100; p2++) {
									int total = 200 * p200 + 100 * p100 + 50 * p50 + 20 * p20 + 10 * p10 + 5 * p5 + 2 * p2;
									if (total <= 200) {
										ways.add(p200 + " " + p100 + " " + p50 + " " + p20 + " " + p10 + " " + p5 + " " + p2 + " " + (200 - total));
										System.out.println(p200 + " " + p100 + " " + p50 + " " + p20 + " " + p10 + " " + p5 + " " + p2 + " " + (200 - total));
									}
								}
							}
						}
					}
				}
			}
		}
		return ways.size();
	}
	
	public static void main(String[] args) {
		System.out.println(waysTwoPounds());
	}
}
