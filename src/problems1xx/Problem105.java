package problems1xx;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

import commonMethods.CMath;

public class Problem105 {
	public static boolean satisfied(int[] list) {
		TreeMap<Integer, TreeSet<Integer>> cond2 = new TreeMap<Integer, TreeSet<Integer>>();
		HashSet<Integer> cond1 = new HashSet<Integer>();
		for (int i = 1; i < Math.pow(2, list.length); i++) {
			String bin = CMath.decToBin(i, list.length);
			int numOnes = 0;
			int currSum = 0;
			for (int j = 0; j < list.length; j++) {
				if (bin.charAt(j) == '1') {
					numOnes++;
					currSum += list[j];
				}
			}
			if (cond2.containsKey(numOnes)) {
				if (cond2.get(numOnes).contains(currSum)) {
					return false;
				} else {
					cond2.get(numOnes).add(currSum);
				}
			} else {
				TreeSet<Integer> next = new TreeSet<Integer>();
				next.add(currSum);
				cond2.put(numOnes, next);
			}
			if (cond1.contains(currSum)) {
				return false;
			} else {
				cond1.add(currSum);
			}
		}
		int prev = -1;
		for (int i : cond2.keySet()) {
			if (prev == -1) {
				prev = i;
				continue;
			} 
			if (cond2.get(i).first() <= cond2.get(prev).last()) {
				return false;
			}
			prev = i;
		}
		return true;
	}
	
	public static int totSum() {
		int total = 0;
		try {
			File txt = new File("MiscFiles/p105_sets.txt");
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(txt);
			while (scan.hasNextLine()) {
				String[] line = scan.nextLine().split(",");
				int[] nums = new int[line.length];
				for (int i = 0; i < line.length; i++) {
					nums[i] = Integer.parseInt(line[i]);
				}
				if (satisfied(nums)) {
					for (int i = 0; i < line.length; i++) {
						total += nums[i];
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("oops");
		}
		return total;
	}
	
	public static void main(String[] args) {
		System.out.println(totSum());
	}
}
