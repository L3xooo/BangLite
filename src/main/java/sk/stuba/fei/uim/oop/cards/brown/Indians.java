package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.cards.Card;

import java.util.List;

public class Indians extends Card {
    private static final String CARD_NAME = "Indians";

    private static final int DAMAGE = 1;

    //Constructors Start
    public Indians() {
        super(CARD_NAME);
    }
    //Constructors End

    //Getters Start
    public int getDamage() { return DAMAGE; }
    //Getters End

    //Methods Start
    @Override
    public void cardAbility(Player playerOnTurn, List<Card> cardDeck,List<Player> players) {
        for(int a = 0; a < playerOnTurn.getEnemyPlayers().size(); a++) {
            Player targetPlayer = playerOnTurn.getEnemyPlayers().get(a);
            int cardIndex = targetPlayer.checkCard(targetPlayer.getPlayerCards(),Indians.class);
            if(cardIndex == -1 ) {
                System.out.println(targetPlayer.getName() + " received damage from Indians!");
                targetPlayer.decreaseHealth(this.getDamage());
                targetPlayer.checkDeath();
                if (targetPlayer.getDeath()) {
                    targetPlayer.playerDied(cardDeck,players);
                }
            } else {
                System.out.println(targetPlayer.getName() + " used Bang and killed the Indians!");
                targetPlayer.removeCard(cardIndex);
            }
        }
        playerOnTurn.getPlayerCards().remove(this);
        cardDeck.add(this);
    }
    @Override
    public void blueCardAbility(Player playerOnTurn, List<Card> cardDeck, List<Player> players) {}
    //Methods End
}
