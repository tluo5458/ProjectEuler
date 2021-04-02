package problems0xx;
import fractions.Fraction;

public class Problem57 {
	private static Fraction nextConvergent(Fraction prevConv) {
		Fraction one = new Fraction(1, 1);
		return Fraction.addFractions(one, Fraction.addFractions(one, prevConv).reciprocal());
	}
	
	private static boolean numMoreDigDen(Fraction f) {
		return f.num().numDigs() > f.den().numDigs();
	}
	
	public static int numConvNMMD(int numConvs) {
		Fraction curr = new Fraction(1, 1);
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
