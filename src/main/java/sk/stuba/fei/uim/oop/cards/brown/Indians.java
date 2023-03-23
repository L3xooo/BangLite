package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.cards.Card;

public class Indians extends Card {
    private static final String CARD_NAME = "Indians";
    private static final String CARD_TYPE = "Brown";
    private static final int DAMAGE = 1;


    public Indians() {
        super(CARD_NAME,CARD_TYPE);
    }
    public int getDamage() { return DAMAGE; }
    @Override
    public boolean canPlay(Player playerOnTurn) {
        return true;
    }
    @Override
    public void playCard(Player playerOnTurn) {
        super.playCard(playerOnTurn);
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
                    targetPlayer.playerDied();
                }
            } else {
                System.out.println("Player: " + targetPlayer.getName() + " used Bang and killed the Indians!");
                targetPlayer.getDiscardCardDeck().add(targetPlayer.getPlayerCards().get(cardIndex));
                targetPlayer.getPlayerCards().remove(cardIndex);
            }
        }
    }
}
