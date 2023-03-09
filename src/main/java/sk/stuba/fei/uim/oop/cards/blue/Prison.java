package sk.stuba.fei.uim.oop.cards.blue;
import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.cards.Card;

import java.util.List;

public class Prison extends Card{
    public Prison(String name,String type) {
        super(name,type);
    }

    @Override
    public void cardAbility(Player playerOnTurn, List<Card> cardsInStack) {
        System.out.println("Prison!");

    }
}
