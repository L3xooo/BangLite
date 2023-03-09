package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.cards.Card;

import java.util.List;

public class Indians extends Card {
    int damage;

    public int getDamage() { return this.damage; }
    public Indians(String name,String type) {
        super(name,type);
        this.damage = 1;
    }

    @Override
    public void cardAbility(Player playerOnTurn, List<Card> cardsInStack) {
        System.out.println("Indians!");
        for(int a = 0; a < playerOnTurn.getEnemyPlayers().size(); a++) {
            Player targetPlayer = playerOnTurn.getEnemyPlayers().get(a);
            int cardIndex = targetPlayer.checkCard("Bang",cardsInStack);
            if(cardIndex == -1 ) {
                targetPlayer.decreaseHealth(this.getDamage());
            } else {
                targetPlayer.removeCard(cardIndex);
            }
        }
    }
}
