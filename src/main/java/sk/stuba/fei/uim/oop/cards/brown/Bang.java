package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import java.util.List;

public class Bang extends Card {
    int damage;
    public Bang(String name, String type) {
        super(name,type);
        this.damage = 1;
    }

    public int getDamage() { return this.damage; }

    @Override
    public void cardAbility(Player playerOnTurn, List<Card> cardsInStack) {
        System.out.println("Bang!");
        playerOnTurn.printEnemyPlayers();
        int index = KeyboardInput.readInt("Enter target player index");
        Player targerPlayer = playerOnTurn.getEnemyPlayers().get(index);
        int cardIndex = targerPlayer.checkCard("Missed",cardsInStack);
        if(cardIndex == -1) {
            targerPlayer.decreaseHealth(this.getDamage());
        } else {
            targerPlayer.removeCard(cardIndex);
        }
    }
}
