package graphs;

public class Pair<L, R> {
	L l;
	R r;
	
	public Pair(L left, R right) {
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
}
