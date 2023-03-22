package sk.stuba.fei.uim.oop.cards.blue;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.cards.Card;

import java.util.Random;

public class Prison extends Card{

    private static final String CARD_NAME = "Prison";
    private static final String CARD_TYPE = "Blue";
    private final Random rand;

    //Constructors Start
    public Prison() {
        super(CARD_NAME,CARD_TYPE);
        this.rand = new Random();
    }
    //Constructors End

    //Getters Start
    public Random getRand() { return this.rand; }
    //Getters End

    //Methods Start
    @Override
    public boolean canPlay(Player playerOnTurn) {
        for (int a = 0; a < playerOnTurn.getEnemyPlayers().size(); a++){
            Player player = playerOnTurn.getEnemyPlayers().get(a);
            if (player.checkCard(player.getBlueCards(),Prison.class) == -1) {
                return true;
            }
        }
        return false;
    }
    @Override
    public void playCard(Player playerOnTurn) {
        playerOnTurn.printEnemyPlayers();
        while(true) {
            int playerIndex = choosingPlayer(playerOnTurn);
            Player targetPlayer = playerOnTurn.getEnemyPlayers().get(playerIndex);
            if (targetPlayer.checkCard(targetPlayer.getBlueCards(),Prison.class) != -1) {
                System.out.println("Player: " + targetPlayer.getName() + " has already prison in blue cards!");
            } else {
                super.playCard(playerOnTurn);
                System.out.println("Player: " + targetPlayer.getName() +" was arrested to the prison!");
                targetPlayer.getBlueCards().add(this);
                break;
            }
        }
    }

    public boolean blueCardAbility(Player playerOnTurn) {
        playerOnTurn.getBlueCards().remove(this);
        playerOnTurn.getDiscardCardDeck().add(this);
        if (this.getRand().nextInt(4) == 0) {
            System.out.printf("Player: %s escaped the prison!\n",playerOnTurn.getName());
            return true;
        } else {
            System.out.printf("Player: %s didn't escaped the prison! You lost your turn!\n",playerOnTurn.getName());
            return false;
        }
    }
    //Methods End
}
