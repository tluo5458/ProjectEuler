package problems1xx;

import java.util.ArrayList;

import commonMethods.Timing;
import graphs.Pair;

public class Problem185 {
	// backtracking
	private static String solve(int numDigs, ArrayList<Pair<String, Integer>> guesses) {
		char[] ret = new char[numDigs];
		for (int i = 0; i < numDigs; i++) {
			ret[i] = '0';
		}
		int currInd = 0;
		while (true) {
			// check constraints
			boolean good = true;
			for (Pair<String, Integer> guess : guesses) {
				int match = 0;
				for (int i = 0; i <= currInd; i++) {
					if (ret[i] == guess.getLeft().charAt(i)) {
						match++;
					}
				}
				if (match > guess.getRight() || match + numDigs - currInd - 1 < guess.getRight()) {
					good = false;
					break;
				}
			}
			if (good) {
				if (currInd == numDigs - 1) {
					break;
				} else {
					currInd++;
				}
			} else {
				while (currInd >= 0 && ret[currInd] == '9') {
					ret[currInd] = '0';
					currInd--;
				}
				if (currInd == -1) {
					return "No solutions";
				} else {
					ret[currInd]++;
				}
			}
		}
		return String.valueOf(ret);
	}
	
	public static String test() {
		ArrayList<Pair<String, Integer>> guesses = new ArrayList<Pair<String, Integer>>();
		guesses.add(new Pair<String, Integer>("90342", 2));
		guesses.add(new Pair<String, Integer>("70794", 0));
		guesses.add(new Pair<String, Integer>("39458", 2));
		guesses.add(new Pair<String, Integer>("34109", 1));
		guesses.add(new Pair<String, Integer>("51545", 2));
		guesses.add(new Pair<String, Integer>("12531", 1));
		return solve(5, guesses);
	}
	
	public static String ans() {
		ArrayList<Pair<String, Integer>> guesses = new ArrayList<Pair<String, Integer>>();
		guesses.add(new Pair<String, Integer>("2321386104303845", 0));
		guesses.add(new Pair<String, Integer>("3847439647293047", 1));
		guesses.add(new Pair<String, Integer>("3174248439465858", 1));
		guesses.add(new Pair<String, Integer>("8157356344118483", 1));
		guesses.add(new Pair<String, Integer>("6375711915077050", 1));
		guesses.add(new Pair<String, Integer>("6913859173121360", 1));
		guesses.add(new Pair<String, Integer>("4895722652190306", 1));
		guesses.add(new Pair<String, Integer>("5616185650518293", 2));
		guesses.add(new Pair<String, Integer>("4513559094146117", 2));
		guesses.add(new Pair<String, Integer>("2615250744386899", 2));
		guesses.add(new Pair<String, Integer>("6442889055042768", 2));
		guesses.add(new Pair<String, Integer>("2326509471271448", 2));
		guesses.add(new Pair<String, Integer>("5251583379644322", 2));
		guesses.add(new Pair<String, Integer>("2659862637316867", 2));
		guesses.add(new Pair<String, Integer>("5855462940810587", 3));
		guesses.add(new Pair<String, Integer>("9742855507068353", 3));
		guesses.add(new Pair<String, Integer>("4296849643607543", 3));
		guesses.add(new Pair<String, Integer>("7890971548908067", 3));
		guesses.add(new Pair<String, Integer>("8690095851526254", 3));
		guesses.add(new Pair<String, Integer>("1748270476758276", 3));
		guesses.add(new Pair<String, Integer>("3041631117224635", 3));
		guesses.add(new Pair<String, Integer>("1841236454324589", 3));
		return solve(16, guesses);
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(ans());
		t.end();
	}
}
