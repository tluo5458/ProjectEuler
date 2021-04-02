package cards;

public class Card {
	int val;
	Suit suit;
	
	public Card(int num, Suit suit) {
		val = num;
		this.suit = suit;
	}
	
	public Card(String numsuit) {
		// numsuit formatted [num][suit], num is A 2-9 T J Q K, suit is S H D C
		val = parseVal(numsuit.charAt(0));
		suit = parseSuit(numsuit.charAt(1));
	}
	
	private Suit parseSuit(char c) {
		if (c == 'S') {
			return Suit.SPADES;
		} else if (c == 'H') {
			return Suit.HEARTS;
		} else if (c == 'D') {
			return Suit.DIAMONDS;
		} else {
			return Suit.CLUBS;
		}
	}
	
	private int parseVal(char c) {
		if (c == 'A') {
			return 14;
		} else if (c == 'T') {
			return 10;
		} else if (c == 'J') {
			return 11;
		} else if (c == 'Q') {
			return 12;
		} else if (c == 'K') {
			return 13;
		} else {
			return Integer.parseInt(Character.toString(c));
		}
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	public int getVal() {
		return val;
	}
	
	@Override
	public String toString() {
		return val + " " + suit;
	}
}
