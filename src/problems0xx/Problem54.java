package problems0xx;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import cards.Card;
import cards.Hand;

public class Problem54 {
	public static int oneWin() {
		int win1 = 0;
		int win2 = 0;
		Card[] hand1 = new Card[5];
		Card[] hand2 = new Card[5];
		try {
			File txt = new File("MiscFiles/p054_poker.txt");
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(txt);
			while (scan.hasNextLine()) {
				String[] line = scan.nextLine().split(" ");
				for (int i = 0; i < 5; i++) {
					hand1[i] = new Card(line[i]);
					hand2[i] = new Card(line[i + 5]);
				}
				Hand h1 = new Hand(hand1);
				Hand h2 = new Hand(hand2);
				if (h1.compareTo(h2) > 0) {
					win1++;
				} else if (h1.compareTo(h2) < 0){
					win2++;
				} else {
					System.out.println("fuck " + Arrays.deepToString(line));
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("oops");
		}
		System.out.println(win1 + " " + win2);
		return win1;
	}
	
	public static void main(String[] args) {
		System.out.println(oneWin());
	}
}
