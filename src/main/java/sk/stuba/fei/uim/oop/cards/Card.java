package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.Player;

import java.util.List;

public class Card {
    String name;
    String type;
    public Card(String name, String type) {
        this.name = name;
        this.type = type;
    }
    public Card(){
        this.name = "Undefined";
        this.type = "Undefined";
    }
    public void cardAbility(Player playerOnTurn, List<Card> cardsInStack){
        System.out.println("This is cardAbility");
    }
    public String getName() {
        return this.name;
    }
}
