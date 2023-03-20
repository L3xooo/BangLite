package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.blue.Barrel;

import java.util.List;

public class Bang extends Card {
    private static final String CARD_NAME = "Bang";
    private static final String CARD_TYPE = "Brown";
    private static final int DAMAGE = 1;
    private List<Card> cardDeck;
    private List<Player> players;

    //Constructors Start
    public Bang(List<Card> cardDeck, List<Player> players) {
        super(CARD_NAME,CARD_TYPE);
        this.cardDeck = cardDeck;
        this.players = players;
    }
    //Constructors End

    //Getters Start
    public int getDamage() { return DAMAGE; }
    public List<Card> getCardDeck() {
        return this.cardDeck;
    }
    public List<Player> getPlayers() {
        return this.players;
    }
    //Getters End

    //Methods Start
    @Override
    public boolean canPlay(Player playerOnTurn) {
        return true;
    }

    public void playCard(Player playerOnTurn, List<Card> cardDeck) {
        playerOnTurn.printEnemyPlayers();
        int playerIndex = choosingPlayer(playerOnTurn);
        Player targetPlayer = playerOnTurn.getEnemyPlayers().get(playerIndex);
        super.playCard(playerOnTurn,cardDeck);
        this.cardAbility(targetPlayer);
    }

    @Override
    public void cardAbility(Player targetPlayer) {
        int cardIndex = targetPlayer.checkCard(targetPlayer.getBlueCards(),Barrel.class);
        if (cardIndex != -1) {
            if (targetPlayer.getBlueCards().get(cardIndex).blueCardAbility(targetPlayer)) {
                return;
            }
        }
        cardIndex = targetPlayer.checkCard(targetPlayer.getPlayerCards(),Missed.class);
        if(cardIndex == -1) {
            targetPlayer.decreaseHealth(this.getDamage());
            System.out.println("Player: " + targetPlayer.getName() + " received damage from Bang! Player health dropped to " + targetPlayer.getHealth());
            targetPlayer.checkDeath();
            if (targetPlayer.getDeath()) {
                targetPlayer.playerDied(this.getCardDeck());
            }
        } else {
            targetPlayer.getPlayerCards().get(cardIndex).playCard(targetPlayer,this.getCardDeck());
        }
    }
    //Methods End
}
