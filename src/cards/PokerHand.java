package cards;

public enum PokerHand {
	HIGHCARD (0),
	ONEPAIR (1),
	TWOPAIR (2),
	TRIPLE (3),
	STRAIGHT (4),
	FLUSH (5),
	FULLHOUSE (6),
	QUAD (7),
	STRAIGHTFLUSH (8),
	ROYALFLUSH (9);
	
	int val;
	
	private PokerHand(int i) {
		val = i;
	}
	
	public int getVal() {
		return val;
	}
}
