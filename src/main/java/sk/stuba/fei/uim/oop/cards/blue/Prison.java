package sk.stuba.fei.uim.oop.cards.blue;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.cards.Card;

import java.util.List;

public class Prison extends Card{

    private static final String CARD_NAME = "Prison";
    private static final String CARD_TYPE = "Blue";
    private List<Card> cardDeck;

    //Constructors Start
    public Prison(List<Card> cardDeck) {
        super(CARD_NAME,CARD_TYPE);
        this.cardDeck = cardDeck;
    }
    //Constructors End

    //Getters Start
    public List<Card> getCardDeck() { return this.cardDeck; }
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

    public void playCard(Player playerOnTurn, List<Card> cardDeck) {
        playerOnTurn.printEnemyPlayers();
        while(true) {
            int playerIndex = choosingPlayer(playerOnTurn);
            Player targetPlayer = playerOnTurn.getEnemyPlayers().get(playerIndex);
            if (targetPlayer.checkCard(targetPlayer.getBlueCards(),Prison.class) != -1) {
                System.out.println("Player: " + targetPlayer.getName() + " has already prison in blue cards!");
            } else {
                super.playCard(playerOnTurn,cardDeck);
                System.out.println("Player: " + targetPlayer.getName() +" was arrested to the prison!");
                targetPlayer.getBlueCards().add(this);
                break;
            }
        }
    }

    public boolean blueCardAbility(Player playerOnTurn) {
        this.getCardDeck().add(this);
        playerOnTurn.getBlueCards().remove(this);
        if (this.getRand().nextInt(4) == 0) {
            System.out.println("Player escaped the prison!");
            return true;
        } else {
            System.out.println("Player didn't escaped the prison! You lost your turn!");
            return false;
        }
    }
    //Methods End
}
