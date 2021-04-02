package problems1xx;

import java.util.Arrays;

public class Problem109 {
	public static int getAnswer() {
		// 1 to 60
		int[] possible = new int[60];
		for (int i = 1; i <= 20; i++) {
			possible[i - 1]++;
			possible[2*i - 1]++;
			possible[3*i - 1]++;
		}
		possible[24]++;
		possible[49]++;
		// 2 to 97
		int[] pairsums = new int[96];
		for (int i = 0; i < 96; i++) {
			for (int j = 0; j < (i + 1) / 2; j++) {
				try {
					pairsums[i] += possible[j] * possible[i - j];
				} catch (ArrayIndexOutOfBoundsException e) {
					
				}
			}
			if (i % 2 == 0) {
				pairsums[i] += possible[i / 2] * (possible[i / 2] + 1) / 2;
			}
		}
		System.out.println(Arrays.toString(pairsums));
		
		int tot = 0;
		// 2 throws and then double to win
		for (int i = 0; i < 96; i++) {
			tot += pairsums[i] * Math.min(20, (97 - i) / 2);
		}
		for (int i = 0; i < 48; i++) {
			tot += pairsums[i];
		}
		// double and instant win
		tot += 21;
		// one dart and then double
		for (int i = 0; i < 60; i++) {
			tot += possible[i] * Math.min(20, (98 - i) / 2);
		}
		for (int i = 0; i < 49; i++) {
			tot += possible[i];
		}
		
		
		// for visualization and checking
		// 2 to 99
		int[] checkouts = new int[98];
		for (int i = 0; i < 48; i++) {
			checkouts[i + 50] += pairsums[i];
		}
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 96 - 2 * i; j++) {
				checkouts[j + 2*i + 2] += pairsums[j];
			}
		}
		for (int i = 0; i < 20; i++) {
			checkouts[2 * i] += 1;
		}
		checkouts[48] += 1;
		for (int i = 0; i < 60; i++) {
			for (int j = 0; j < Math.min(20, (98 - i) / 2); j++) {
				checkouts[i + 1 + 2*j] += possible[i];
			}
		}
		for (int i = 0; i < 49; i++) {
			checkouts[i + 49] += possible[i];
		}
		System.out.println(Arrays.toString(checkouts));
		
		int checkTot = 0;
		for (int i = 0; i < 98; i++) {
			checkTot += checkouts[i];
		}
		System.out.println(checkTot);
		return tot;
	}
	
	public static void main(String[] args) {
		System.out.println(getAnswer());
	}
}
