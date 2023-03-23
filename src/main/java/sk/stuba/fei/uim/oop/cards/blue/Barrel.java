package sk.stuba.fei.uim.oop.cards.blue;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.cards.Card;

import java.util.Random;

public class Barrel extends Card{
    private static final String CARD_NAME = "Barrel";
    private static final String CARD_TYPE = "Blue";
    private final Random rand;

    public Barrel() {
        super(CARD_NAME,CARD_TYPE);
        this.rand = new Random();
    }

    public Random getRand() { return this.rand; }

    @Override
    public boolean canPlay(Player playerOnTurn) {
        return playerOnTurn.checkCard(playerOnTurn.getBlueCards(), Barrel.class) == -1;
    }

    @Override
    public void playCard(Player playerOnTurn) {
        super.playCard(playerOnTurn);
        playerOnTurn.getBlueCards().add(this);
    }

    public boolean blueCardAbility(Player playerOnTurn) {
        if (this.getRand().nextInt(4) == 0) {
            System.out.println("Barrel worked!");
            return true;
        } else {
            System.out.println("Barrel didn't worked!");
            return false;
        }
    }
}
