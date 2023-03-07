package sk.stuba.fei.uim.oop.cards;

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

    public String getName() {
        return this.name;
    }
}
