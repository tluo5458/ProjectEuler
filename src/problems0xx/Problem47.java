package problems0xx;
import commonMethods.CMath;

public class Problem47 {
	public static int firstHasDPFacts(int numFacts) {
		int curr = 2;
		int numConsec = 0;
		while (true) {
			if (CMath.primeCount(curr).keySet().size() >= numFacts) {
				numConsec++;
				if (numConsec == numFacts) {
					return curr - numFacts + 1;
				}
			} else {
				numConsec = 0;
			}
			curr++;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(firstHasDPFacts(2));
		System.out.println(firstHasDPFacts(3));
		System.out.println(firstHasDPFacts(4));
	}
}
