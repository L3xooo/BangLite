package sk.stuba.fei.uim.oop.cards.blue;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.cards.Card;

import java.util.Random;

public class Barrel extends Card{
    private static final String CARD_NAME = "Barrel";
    private static final String CARD_TYPE = "Blue";
    private final Random rand;

    //Constructor Start
    public Barrel() {
        super(CARD_NAME,CARD_TYPE);
        this.rand = new Random();
    }
    //Constructor End

    //Getters Start
    public Random getRand() { return this.rand; }
    //Getter End

    //Methods Start
    @Override
    public boolean canPlay(Player playerOnTurn) {
        return playerOnTurn.checkCard(playerOnTurn.getBlueCards(), Barrel.class) == -1;
    }

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
    //Methods End
}
