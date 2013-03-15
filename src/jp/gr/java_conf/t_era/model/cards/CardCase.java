package jp.gr.java_conf.t_era.model.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardCase {
	List<Card> cardsInCase;

	CardCase(List<Card> defaultCards) {
		cardsInCase = new ArrayList<Card>(defaultCards);
	}

	void shuffle() {
		Collections.shuffle(cardsInCase);
	}

	Card pullACard() {
		return cardsInCase.remove(0);
	}

	void pushACard(Card card) {
		cardsInCase.add(card);
	}
}
