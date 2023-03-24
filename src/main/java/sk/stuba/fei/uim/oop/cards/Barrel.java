package sk.stuba.fei.uim.oop.cards;
import sk.stuba.fei.uim.oop.player.Player;

public class Barrel extends BlueCard{
    private static final String CARD_NAME = "Barrel";
    public Barrel() {
        super(CARD_NAME);
    }


    @Override
    public boolean canPlay(Player playerOnTurn) {
        return playerOnTurn.checkBlueCard(Barrel.class) == -1;
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
}
