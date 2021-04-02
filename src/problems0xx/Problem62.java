package problems0xx;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;

import graphs.Pair;

public class Problem62 {
	public static BigInteger findPerms(int numPerms) {
		HashMap<String, Pair<Integer, Integer>> perms = new HashMap<String, Pair<Integer, Integer>>(); 
		int curr = 1;
		int attained = 0;
		while (true) {
			BigInteger cube = new BigInteger(Integer.toString(curr));
			cube = cube.multiply(cube).multiply(cube);
			char[] cubeArr = cube.toString().toCharArray();
			Arrays.sort(cubeArr);
			String str = String.valueOf(cubeArr);
			if (perms.containsKey(str)) {
				Pair<Integer, Integer> assoc = perms.get(str);
				if (assoc.getRight() == numPerms - 1) {
					attained = assoc.getLeft();
					break;
				}
				perms.put(str, new Pair<Integer, Integer>(assoc.getLeft(), assoc.getRight() + 1));
			} else {
				perms.put(str, new Pair<Integer, Integer>(curr, 1));
			}
			curr++;
		}
		BigInteger ret = new BigInteger(Integer.toString(attained));
		System.out.println(attained);
		return ret.multiply(ret).multiply(ret);
	}
	
	public static void main(String[] args) {
		System.out.println(findPerms(5));
		System.out.println(findPerms(20));
	}
}
