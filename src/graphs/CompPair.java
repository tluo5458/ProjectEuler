package graphs;

public class CompPair<L extends Comparable<L>, R extends Comparable<R>> implements Comparable<CompPair<L, R>> {
	L l;
	R r;
	
	public CompPair(L left, R right) {
		l = left;
		r = right;
	}
	
	public L getLeft() {
		return l;
	}
	
	public R getRight() {
		return r;
	}
	
	public void setLeft(L left) {
		l = left;
	}
	
	public void setRight(R right) {
		r = right;
	}
	
	@Override
	public String toString() {
		return "L is " + l.toString() + " and R is " + r.toString();
	}
	
	@Override
	public int compareTo(CompPair<L, R> other) {
		if (l.compareTo(other.getLeft()) == 0) {
			return r.compareTo(other.getRight());
		}
		return l.compareTo(other.getLeft());
	}
}
