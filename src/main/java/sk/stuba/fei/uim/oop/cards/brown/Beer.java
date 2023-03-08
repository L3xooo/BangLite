package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.cards.Card;

import java.util.List;

public class Beer extends Card {
    int healAmount;
    public Beer(String name, String type) {
        super(name,type);
        this.healAmount = 1;
    }

    public int getHealAmount() { return this.healAmount; }

    @Override
    public void cardAbility(Player playerOnTurn, List<Card> cardsInStack) {
        System.out.println("Beer!");
        playerOnTurn.addHealth(this.getHealAmount());
    }
}
