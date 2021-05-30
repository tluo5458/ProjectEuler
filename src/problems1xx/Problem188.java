package problems1xx;

import java.math.BigInteger;

import commonMethods.CMath;
import commonMethods.Timing;

public class Problem188 {
	public static BigInteger answer() {
		BigInteger base = new BigInteger("1777");
		BigInteger curr = BigInteger.ONE;
		int[] mods = {32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 65536, 163840, 409600, 1024000, 2560000, 6400000, 16000000, 40000000, 100000000};
		for (int i : mods) {
			curr = base.modPow(curr, CMath.intToBI(i));
		}
		return curr;
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(answer());
		t.end();
	}
}
