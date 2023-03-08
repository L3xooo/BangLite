package sk.stuba.fei.uim.oop;
import java.util.ArrayList;
import java.util.List;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.brown.Bang;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;
public class Player {
    String name;
    int health;
    List<Card> playerCards;
    List<Card> blueCards;

    public Player(String name){
        this.name = name;
        this.health = 4;
        this.playerCards = new ArrayList<Card>();
        this.blueCards = new ArrayList<Card>();
    }

    public String getName() { return this.name; }
    public int getHealth() {
        return this.health;
    }
    public void decreaseHealth(int damage) {
        this.health -= damage;
    }
    public void addHealth() {
        this.health += 1;
    }
    @Override
    public String toString() {
        return this.name;
    }

    public void printCards() {
        for (int a = 0; a < this.playerCards.size(); a++) {
            System.out.printf(" %s ",this.playerCards.get(a).getName());
        }
        System.out.println();
    }

    public void drawCard(int numberOfCards,List<Card> cardsInStack) {
        for(int a = 0; a < numberOfCards; a++) {
            this.playerCards.add(cardsInStack.get(0));
            //cardsInGame.add(cardsInStack.get(0));
            cardsInStack.remove(0);
        }
    }

    public void checkCardCount(List<Card> cardStack) {
        while (this.playerCards.size() > this.getHealth()) {
            System.out.println("You have more cards than health, throw some card away!");
            this.printCards();
            int index = KeyboardInput.readInt("Enter index of Card");
            Card removedCard = this.playerCards.get(index);
            cardStack.add(removedCard);
            this.playerCards.remove(index);
        }
    }

    public boolean checkCard(String cardName,List<Card> cardDeck){
        for(int a = 0; a < this.playerCards.size(); a++){
            if(this.playerCards.get(a).getName().equals(cardName)) {
                cardDeck.add(this.playerCards.get(a));
                this.playerCards.remove(this.playerCards.get(a));
                return true;
            }
        }
        return false;
    }

    public void cardChoose(List<Card> cardDeck) {
        this.printCards();
        int index = KeyboardInput.readInt("Enter card index");
        Card card = this.playerCards.get(index);

    }
}



