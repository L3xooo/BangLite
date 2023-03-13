package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.Player;

import java.util.List;
import java.util.Random;

public abstract class Card {
    String name;
    Random rand;

    //Constructor Start
    public Card(String name) {
        this.name = name;
        this.rand = new Random();
    }
    //Constructor End

    //Getters Start
    public String getName() {
        return this.name;
    }
    public Random getRand() { return this.rand; }
    //Getters End

    //Methods Start
    public abstract void cardAbility(Player playerOnTurn, List<Card> cardDeck, List<Player> players);
    public boolean cardProbabilityOfSuccess(double probability) {
        return Math.random() < probability;
    }
    public abstract void blueCardAbility(Player playerOnTurn, List<Card> cardDeck, List<Player> players);
    //Methods End
}
