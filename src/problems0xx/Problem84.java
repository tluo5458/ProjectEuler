package problems0xx;

import java.util.Random;
import java.util.TreeMap;

public class Problem84 {
	static Random r = new Random();
	static int[] railroads = {5, 15, 25, 35};
	static int[] utilities = {12, 28};
	
	public static int nextSqType(int sq, int[] sqs) {
		for (int i = 0; i < sqs.length; i++) {
			if (sq < sqs[i]) {
				return sqs[i];
			}
		}
		return sqs[0];
	}
	
	public static int nextSquareCH(int curr) {
		int i = r.nextInt(16);
		switch (i) {
		case 0:
			return 0;
		case 1:
			return 10;
		case 2:
			return 11;
		case 3:
			return 24;
		case 4: 
			return 39;
		case 5:
			return 5;
		case 6:
			return nextSqType(curr, railroads);
		case 7:
			return nextSqType(curr, railroads);
		case 8:
			return nextSqType(curr, utilities);
		case 9:
			return curr - 3;
		default:
			return curr;	
		}
	}
	
	public static int nextSquareCC(int curr) {
		int i = r.nextInt(16);
		switch (i) {
		case 0:
			return 0;
		case 1:
			return 10;
		default:
			return curr;
		}
	}
	
	public static TreeMap<Double, Integer> probabilities(int numRolls) {
		int consecDoubles = 0;
		int curr = 0;
		int[] landed = new int[40];
		for (int i = 0; i < numRolls; i++) {
			int roll1 = r.nextInt(4);
			int roll2 = r.nextInt(4);
			if (roll1 == roll2) {
				consecDoubles++;
			} else {
				consecDoubles = 0;
			}
			curr += (roll1 + roll2 + 2);
			curr = curr % 40;
			if (consecDoubles == 3) {
				consecDoubles = 0;
				curr = 10;
			} else {
				if (curr == 30) {
					curr = 10;
				} else if (curr == 7 || curr == 22 || curr == 36) {
					curr = nextSquareCH(curr);
				} else if (curr == 2 || curr == 17 || curr == 33) {
					curr = nextSquareCC(curr);
				}
			}
			landed[curr]++;
		}
		TreeMap<Double, Integer> ret = new TreeMap<Double, Integer>();
		for (int i = 0; i < 40; i++) {
			ret.put(((double) landed[i]) / numRolls, i);
		}
		return ret;
	}
	
	public static String modalString(int numRolls) {
		TreeMap<Double, Integer> probs = probabilities(numRolls);
		String ret = "";
		for (int i = probs.size() - 1; i > probs.size() - 4; i--) {
			int square = (int) probs.values().toArray()[i];
			if (square < 10) {
				ret += "0";
			}
			ret += Integer.toString(square);
		}
		return ret;
	}
	
	public static void main(String[] args) {
		System.out.println(modalString(10000000));
	}
}
