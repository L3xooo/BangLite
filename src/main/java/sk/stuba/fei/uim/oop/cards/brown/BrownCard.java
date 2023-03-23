package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

public class BrownCard extends Card {

    public BrownCard(String name, String cardType) {
        super(name, cardType);
    }

    @Override
    public boolean canPlay(Player playerOnTurn) {
        return false;
    }
}
