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
    List <Player> enemyPlayers;
    public Player(String name){
        this.name = name;
        this.health = 4;
        this.playerCards = new ArrayList<>();
        this.blueCards = new ArrayList<>();
        this.enemyPlayers = new ArrayList<>();
    }


    //Getters Start
    public String getName() { return this.name; }
    public int getHealth() {
        return this.health;
    }
    public List<Card> getPlayerCards() { return this.playerCards; }
    public List<Player> getEnemyPlayers() { return this.enemyPlayers; }
    public List<Card> getBlueCards() { return this.blueCards; }
    @Override
    public String toString() {
        return this.name;
    }
    //Getters End


    public void decreaseHealth(int damage) {
        this.health -= damage;
    }
    public void addHealth(int healAmount) {
        this.health += healAmount;
    }
    public void addEnemies(List<Player> players) {
        for(int a = 0; a < players.size(); a++) {
            if(this.getName().equals(players.get(a).getName())){
                continue;
            } else {
                this.enemyPlayers.add(players.get(a));
            }
        }
    }
    public void printCards() {
        for (int a = 0; a < this.playerCards.size(); a++) {
            System.out.printf("%s ",this.playerCards.get(a).getName());
        }
        System.out.println();
    } //funguje v pohode
    public void drawCard(int numberOfCards,List<Card> cardsInStack) {
        for(int a = 0; a < numberOfCards; a++) {
            this.playerCards.add(cardsInStack.get(0));
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
    } //funguje v poriadku
    public int checkCard(String cardName,List<Card> cards){
        for(int a = 0; a < cards.size(); a++){
            if(cards.get(a).getName().equals(cardName)) {
                return a;
            }
        }
        return -1;
    }
    public void removeCard(int index) {
        this.getPlayerCards().remove(index);
    }
    public void removeBlueCard(int index) {
        this.getBlueCards().remove(index);
    }
    public void cardChoose(List<Card> cardDeck) {
        this.printCards();
        int index = KeyboardInput.readInt("Enter card index");
        Card card = this.playerCards.get(index);
        card.cardAbility(this,cardDeck);
        this.playerCards.remove(card);
        cardDeck.add(card);
    }
    public void printEnemyPlayers() {
        for (int a = 0; a < this.getEnemyPlayers().size(); a++) {
            Player player = this.enemyPlayers.get(a);
            System.out.printf("(%d) - %s    Health: %d    Cards: %d \n    BlueCards: %d",a,
                    player.getName(), player.getHealth(),
                    player.getPlayerCards().size(),player.getBlueCards().size());
            if (player.getBlueCards().size() != 0) {
                for(int b = 0; b < player.getBlueCards().size(); b++) {
                    System.out.printf("%s ",player.getBlueCards().get(a).getName());
                }
            }
        }
        System.out.println();
    }
    public void printPlayerStats() {
        System.out.printf("Player: %s   Health: %d",this.getName(),this.getHealth());
    }



}



