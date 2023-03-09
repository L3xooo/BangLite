package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.Player;

import java.util.List;
import java.util.Random;

public class Card {
    String name;
    String type;
    Random rand;
    public Card(String name, String type) {
        this.name = name;
        this.type = type;
        this.rand = new Random();
    }
    public Card(){
        this.name = "Undefined";
        this.type = "Undefined";
        this.rand = new Random();
    }
    public String getName() {
        return this.name;
    }
    public Random getRand() { return this.rand; }
    public void cardAbility(Player playerOnTurn, List<Card> cardsInStack){
        System.out.println("This is cardAbility");
    }
    public boolean cardProbabilityOfSuccess(double probability) {
        if(Math.random() < probability) {
            return true;
        } else {
            return false;
        }
    }

    public boolean blueCardAbility(Player playerOnTurn, List<Card> cardsInStack) {
        System.out.println("blueCardAbility");
        return false;
    }
}
