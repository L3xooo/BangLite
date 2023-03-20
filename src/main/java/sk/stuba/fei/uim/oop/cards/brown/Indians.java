package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.cards.Card;

import java.util.List;

public class Indians extends Card {
    private static final String CARD_NAME = "Indians";
    private static final String CARD_TYPE = "Brown";
    private static final int DAMAGE = 1;
    private List<Card> cardDeck;
    private List<Player> players;

    //Constructors Start
    public Indians(List<Card> cardDeck, List<Player> players) {
        super(CARD_NAME,CARD_TYPE);
        this.cardDeck = cardDeck;
        this.players = players;
    }
    //Constructors End

    //Getters Start
    public int getDamage() { return DAMAGE; }
    public List<Card> getCardDeck() { return  this.cardDeck; }
    public List<Player> getPlayers() { return this.players; }
    //Getters End

    //Methods Start
    @Override
    public boolean canPlay(Player playerOnTurn) {
        return true;
    }

    public void playCard(Player playerOnTurn, List<Card> cardDeck) {
        super.playCard(playerOnTurn,cardDeck);
        cardAbility(playerOnTurn);
    }
    @Override
    public void cardAbility(Player playerOnTurn) {
        int cardIndex;
        for(int a = 0; a < playerOnTurn.getEnemyPlayers().size(); a++) {
            Player targetPlayer = playerOnTurn.getEnemyPlayers().get(a);
            cardIndex = targetPlayer.checkCard(targetPlayer.getPlayerCards(),Bang.class);
            if(cardIndex == -1 ) {
                targetPlayer.decreaseHealth(this.getDamage());
                System.out.println("Player: " + targetPlayer.getName() +
                " received damage from Indians! Player health dropped to " + targetPlayer.getHealth());
                targetPlayer.checkDeath();
                if (targetPlayer.getDeath()) {
                    targetPlayer.playerDied(this.getCardDeck());
                }
            } else {
                System.out.println("Player: " + targetPlayer.getName() + " used Bang and killed the Indians!");
                targetPlayer.getPlayerCards().remove(cardIndex);
                this.getCardDeck().add(targetPlayer.getPlayerCards().get(cardIndex));
            }
        }
    }
    //Methods End
}
