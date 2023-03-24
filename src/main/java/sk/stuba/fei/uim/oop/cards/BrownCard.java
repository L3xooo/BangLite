package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

public class BrownCard extends Card {

    public BrownCard(String name) {
        super(name);
    }

    @Override
    public boolean canPlay(Player playerOnTurn) {
        return false;
    }
}
