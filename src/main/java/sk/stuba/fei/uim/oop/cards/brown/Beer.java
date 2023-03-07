package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.cards.Card;

public class Beer extends Card {

    public Beer(String name, String type) {
        super(name,type);
    }
    public void cardAbility(Player targetPlayer){
        targetPlayer.addHealth();
    }

}
