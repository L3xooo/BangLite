package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.Random;

public class BlueCard extends Card {

    private final Random rand;
    public BlueCard(String name, String cardType) {
        super(name, cardType);
        this.rand = new Random();
    }

    public Random getRand() { return this.rand; }

    @Override
    public boolean canPlay(Player playerOnTurn) {
        return false;
    }
}
