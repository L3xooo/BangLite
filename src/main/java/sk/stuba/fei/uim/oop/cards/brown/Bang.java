package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.blue.Barrel;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import java.util.List;

public class Bang extends Card {
    int damage;

    //Constructors Start
    public Bang(String name, String type) {
        super(name,type);
        this.damage = 1;
    }
    //Constructors End

    //Getters Start
    public int getDamage() { return this.damage; }
    //Getters End

    //Methods Start
    @Override
    public void cardAbility(Player playerOnTurn, List<Card> cardDeck,List<Player> players) { //pridat barrel
        System.out.println("Bang!");
        playerOnTurn.printEnemyPlayers();
        while(true) {
            int index = KeyboardInput.readInt("Enter target player index");
            if (index < 0 || index >= playerOnTurn.getEnemyPlayers().size()) {
                System.out.println("Index is out of bounds. Please enter correct index!");
                continue;
            }
            Player targetPlayer = playerOnTurn.getEnemyPlayers().get(index);
            targetPlayer.printCards();
            int cardIndex = targetPlayer.checkCard(targetPlayer.getPlayerCards(),Barrel.class);
            if (cardIndex != -1) {
                boolean result = targetPlayer.getBlueCards().get(cardIndex).cardProbabilityOfSuccess(0.25);
                if (result) {
                    System.out.println("Barrel worked");
                    return;
                } else {
                    System.out.println("Barrel didnt work");
                }
            }
            cardIndex = targetPlayer.checkCard(targetPlayer.getPlayerCards(),Missed.class);
            if(cardIndex == -1) {
                System.out.println(targetPlayer.getName() + " received damage from Bang!");
                targetPlayer.decreaseHealth(this.getDamage());
                targetPlayer.checkDeath();
                if (targetPlayer.getDeath()) {
                    targetPlayer.playerDied(cardDeck,players);
                }
            } else {
                System.out.println("Missed");
                targetPlayer.removeCard(cardIndex);
            }
            playerOnTurn.getPlayerCards().remove(this);
            cardDeck.add(this);
            break;
        }
    }
    @Override
    public void blueCardAbility(Player playerOnTurn, List<Card> cardDeck, List<Player> players) {

    }
    //Methods End
}
