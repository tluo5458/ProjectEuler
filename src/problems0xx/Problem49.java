package problems0xx;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

import commonMethods.CMath;

public class Problem49 {
	private static ArrayList<ArrayList<Integer>> findPrimeAriths(int num) {
		HashSet<Integer> perms = CMath.permutations(num);
		TreeSet<Integer> primePerms = new TreeSet<Integer>();
		for (Integer i : perms) {
			if (CMath.isPrime(i)) {
				primePerms.add(i);
			}
		}
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> asArray = new ArrayList<Integer>(primePerms);
		for (int i = 0; i < asArray.size(); i++) {
			for (int j = i + 1; j < asArray.size(); j++) {
				if (asArray.contains(2 * asArray.get(j) - asArray.get(i))) {
					ArrayList<Integer> add = new ArrayList<Integer>();
					add.add(asArray.get(i));
					add.add(asArray.get(j));
					add.add(2 * asArray.get(j) - asArray.get(i));
					ret.add(add);
				}
			}
		}
		return ret;
	}
	
	private static boolean isEqual(ArrayList<Integer> a, ArrayList<Integer> b) {
		if (a.size() != b.size()) {
			return false;
		}
		for (int i = 0; i < a.size(); i++) {
			if (!a.get(i).equals(b.get(i))) {
				return false;
			}
		}
		return true;
	}
	
	public static ArrayList<ArrayList<Integer>> findAllPrimeAriths(int min, int max) {
		ArrayList<ArrayList<Integer>> ariths = new ArrayList<ArrayList<Integer>>();
		for (int i = min; i <= max; i++) {
			if (CMath.isPrime(i)) {
				ArrayList<ArrayList<Integer>> curr = findPrimeAriths(i);
				for (ArrayList<Integer> arith : curr) {
					boolean isIden = false;
					for (ArrayList<Integer> check : ariths) {
						isIden = (isIden || isEqual(arith, check));
					}
					if (!isIden) {
						ariths.add(arith);
					}
				}
			}
		}
		return ariths;
	}
	
	public static void main(String[] args) {
		System.out.println(findAllPrimeAriths(1000, 99999));
	}
}
