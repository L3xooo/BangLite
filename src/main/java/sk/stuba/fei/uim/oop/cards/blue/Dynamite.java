package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.cards.Card;

import java.util.List;

public class Dynamite extends Card {
    int damage;
    public Dynamite(String name,String type) {

        super(name,type);
        this.damage = 3;
    }

    public int getDamage() { return this.damage; }
    @Override
    public void cardAbility(Player playerOnTurn, List<Card> cardsInStack) {
        System.out.println("Dynamite!");
        playerOnTurn.getBlueCards().add(this);
        playerOnTurn.getPlayerCards().remove(this);
    }


    @Override
    public void blueCardAbility(Player playerOnTurn, List<Card> cardsInStack,List<Player> players) {
        boolean result = this.cardProbabilityOfSuccess(0.125);
        if (result) {
            int activeIndex = players.indexOf(playerOnTurn);
            int newIndex = activeIndex-1;
            if(newIndex < 0){
                newIndex = players.size()-1;
            }
            Player newTargetPlayer = players.get(newIndex);
            newTargetPlayer.getBlueCards().add(this);

        } else {
            playerOnTurn.decreaseHealth(this.getDamage());
            cardsInStack.add(this);
        }
        playerOnTurn.getBlueCards().remove(this);
    }
}
