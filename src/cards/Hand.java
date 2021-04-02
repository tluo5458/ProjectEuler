package cards;

import java.util.ArrayList;
import java.util.Arrays;

import graphs.Pair;

public class Hand implements Comparable<Hand> {
	Card[] hand;
	int[] vals;
	
	public Hand(Card[] cards) {
		hand = new Card[5];
		vals = new int[5];
		for (int i = 0; i < 5; i++) {
			hand[i] = cards[i];
			vals[i] = cards[i].getVal();
		}
		Arrays.sort(vals);
	}
	
	private boolean isFlush() {
		Suit s = hand[0].getSuit();
		for (int i = 1; i < 5; i++) {
			if (hand[i].getSuit() != s) {
				return false;
			}
		}
		return true;
	}
	
	private int isStraight() {
		// check if a 2 3 4 5
		if (Arrays.equals(vals, new int[] {2, 3, 4, 5, 14})) {
			return 5;
		} else {
			for (int i = 1; i < 5; i++) {
				if (vals[i] - vals[i - 1] != 1) {
					return 0;
				}
			}
		}
		return vals[4];
	}
	
	private ArrayList<Integer> pairs() {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for (int i = 0; i < 4; i++) {
			for (int j = i + 1; j < 5; j++) {
				if (vals[i] == vals[j]) {
					ret.add(vals[i]);
				}
			}
		}
		return ret;
	}
	
	public Pair<PokerHand, Integer> pokerValue() {
		int i = isStraight();
		boolean b = isFlush();
		ArrayList<Integer> p = pairs();
		if (b && (i == 14)) {
			return new Pair<PokerHand, Integer>(PokerHand.ROYALFLUSH, 14);
		} else if (b && (i != 0)) {
			return new Pair<PokerHand, Integer>(PokerHand.STRAIGHTFLUSH, i);
		} else if (p.size() == 6) {
			return new Pair<PokerHand, Integer>(PokerHand.QUAD, p.get(0));
		} else if (p.size() == 4) {
			return new Pair<PokerHand, Integer>(PokerHand.FULLHOUSE, p.get(2));
		} else if (b) {
			return new Pair<PokerHand, Integer>(PokerHand.FLUSH, vals[4]);
		} else if (i != 0) {
			return new Pair<PokerHand, Integer>(PokerHand.STRAIGHT, i);
		} else if (p.size() == 3) {
			return new Pair<PokerHand, Integer>(PokerHand.TRIPLE, p.get(0));
		} else if (p.size() == 2) {
			return new Pair<PokerHand, Integer>(PokerHand.TWOPAIR, p.get(1));
		} else if (p.size() == 1) {
			return new Pair<PokerHand, Integer>(PokerHand.ONEPAIR, p.get(0));
		} else {
			return new Pair<PokerHand, Integer>(PokerHand.HIGHCARD, vals[4]);
		}
	}
	
	@Override
	public int compareTo(Hand other) {
		Pair<PokerHand, Integer> self = pokerValue();
		Pair<PokerHand, Integer> comp = other.pokerValue();
		if (self.getLeft().getVal() != comp.getLeft().getVal()) {
			return self.getLeft().getVal() - comp.getLeft().getVal();
		}
		return self.getRight() - comp.getRight();
	}
}
