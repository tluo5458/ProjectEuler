package problems0xx;
import fractions.BCDFraction;

public class Problem57 {
	private static BCDFraction nextConvergent(BCDFraction prevConv) {
		BCDFraction one = new BCDFraction(1, 1);
		return BCDFraction.addFractions(one, BCDFraction.addFractions(one, prevConv).reciprocal());
	}
	
	private static boolean numMoreDigDen(BCDFraction f) {
		return f.num().numDigs() > f.den().numDigs();
	}
	
	public static int numConvNMMD(int numConvs) {
		BCDFraction curr = new BCDFraction(1, 1);
		int count = 0;
		for (int i = 0; i < numConvs; i++) {
			curr = nextConvergent(curr);
			if (numMoreDigDen(curr)) {
				count++;
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		System.out.println(numConvNMMD(1000));
	}
}
