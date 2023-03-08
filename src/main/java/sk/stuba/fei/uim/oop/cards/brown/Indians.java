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

}
