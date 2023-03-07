package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.cards.Card;

public class Bang extends Card {
    int damage;
    public Bang(String name, String type) {
        super(name,type);
        this.damage = 1;
    }

    public void cardAbility(Player targetPlayer){
        targetPlayer.decreaseHealth();
    }
}
