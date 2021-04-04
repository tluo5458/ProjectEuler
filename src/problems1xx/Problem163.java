package problems1xx;

public class Problem163 {
	public static long t(int n) {
		long nL = (long) n;
		long numVerts = (7 * nL * nL + 3 * nL + 4) / 2;
		long trips = (numVerts * (numVerts - 1) * (numVerts - 2)) / 6;
		System.out.println(trips);
		long inLines = 0;
		for (long i = 1; i <= n; i++) {
			inLines += 8 * i * i * i - 2 * i;
		}
		return trips - inLines;
	}
	
	// doesn't work
	public static void main(String[] args) {
		System.out.println(t(1));
		System.out.println(t(2));
	}
}
