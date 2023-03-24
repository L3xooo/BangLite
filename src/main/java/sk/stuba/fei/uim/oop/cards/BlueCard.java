package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.Random;

public abstract class BlueCard extends Card {

    private final Random rand;
    public BlueCard(String name) {
        super(name);
        this.rand = new Random();
    }

    public Random getRand() { return this.rand; }

    @Override
    public boolean canPlay(Player playerOnTurn) {
        return false;
    }

    public abstract boolean blueCardAbility(Player playerOnTurn);
}
