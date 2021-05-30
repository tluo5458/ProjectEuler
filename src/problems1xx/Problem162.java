package problems1xx;

import java.math.BigInteger;

import commonMethods.Timing;

public class Problem162 {
	public static String answer(int digLim) {
		// nums[num digits - 1][contains 0?][contains 1?][contains A?]
		BigInteger[][][][] nums = new BigInteger[digLim][2][2][2];
		BigInteger th = new BigInteger("13");
		BigInteger fo = new BigInteger("14");
		BigInteger fi = new BigInteger("15");
		BigInteger si = new BigInteger("16");
		nums[0][0][0][0] = th;
		nums[0][0][0][1] = BigInteger.ONE;
		nums[0][0][1][0] = BigInteger.ONE;
		nums[0][0][1][1] = BigInteger.ZERO;
		nums[0][1][0][0] = BigInteger.ZERO;
		nums[0][1][0][1] = BigInteger.ZERO;
		nums[0][1][1][0] = BigInteger.ZERO;
		nums[0][1][1][1] = BigInteger.ZERO;
		for (int i = 1; i < digLim; i++) {
			nums[i][0][0][0] = th.multiply(nums[i - 1][0][0][0]);
			nums[i][0][0][1] = nums[i - 1][0][0][0].add(fo.multiply(nums[i - 1][0][0][1]));
			nums[i][0][1][0] = nums[i - 1][0][0][0].add(fo.multiply(nums[i - 1][0][1][0]));
			nums[i][0][1][1] = nums[i - 1][0][1][0].add(nums[i - 1][0][0][1]).add(fi.multiply(nums[i - 1][0][1][1]));
			nums[i][1][0][0] = nums[i - 1][0][0][0].add(fo.multiply(nums[i - 1][1][0][0]));
			nums[i][1][0][1] = nums[i - 1][1][0][0].add(nums[i - 1][0][0][1]).add(fi.multiply(nums[i - 1][1][0][1]));
			nums[i][1][1][0] = nums[i - 1][1][0][0].add(nums[i - 1][0][1][0]).add(fi.multiply(nums[i - 1][1][1][0]));
			nums[i][1][1][1] = nums[i - 1][1][1][0].add(nums[i - 1][1][0][1]).add(nums[i - 1][0][1][1]).add(si.multiply(nums[i - 1][1][1][1]));
		}
		BigInteger ret = BigInteger.ZERO;
		for (int i = 0; i < digLim; i++) {
			ret = ret.add(nums[i][1][1][1]);
		}
		return ret.toString(16).toUpperCase();
	}
	
	public static void main(String[] args) {
		Timing t = new Timing();
		t.start();
		System.out.println(answer(16));
		t.end();
	}
}
