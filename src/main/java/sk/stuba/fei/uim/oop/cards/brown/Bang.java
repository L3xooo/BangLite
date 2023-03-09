package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.blue.Barrel;
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
    public void cardAbility(Player playerOnTurn, List<Card> cardsInStack) { //pridat barrel
        System.out.println("Bang!");
        playerOnTurn.printEnemyPlayers();
        while(true) {
            try{
                int index = KeyboardInput.readInt("Enter target player index");
                if (index < 0 || index >= playerOnTurn.getEnemyPlayers().size()) {
                    throw new IndexOutOfBoundsException();
                }
                Player targetPlayer = playerOnTurn.getEnemyPlayers().get(index);
                int cardIndex = targetPlayer.checkCard("Barrel",targetPlayer.getBlueCards());
                if(cardIndex != - 1) {
                    Card card = targetPlayer.getBlueCards().get(cardIndex);
                    boolean result = card.blueCardAbility(targetPlayer,cardsInStack);
                    if (result) {   //Barrel zabral
                        return;
                    }
                }
                cardIndex = targetPlayer.checkCard("Missed",targetPlayer.getPlayerCards());
                if(cardIndex == -1) {
                    targetPlayer.decreaseHealth(this.getDamage());
                } else {
                    targetPlayer.removeCard(cardIndex);
                }
                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index is out of bounds. Please enter correct index!");
            }
        }
    }
}
