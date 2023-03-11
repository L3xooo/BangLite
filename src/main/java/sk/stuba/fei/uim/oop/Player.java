package sk.stuba.fei.uim.oop;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.blue.Dynamite;
import sk.stuba.fei.uim.oop.cards.blue.Prison;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;
import sk.stuba.fei.uim.oop.utility.Turn;


public class Player {
    String name;
    int health;
    boolean death;
    List<Card> playerCards;
    List<Card> blueCards;
    List <Player> enemyPlayers;
    boolean isInPrison;

    //Constructor Start
    public Player(String name){
        this.death = false;
        this.name = name;
        this.health = 4;
        this.playerCards = new ArrayList<>();
        this.blueCards = new ArrayList<>();
        this.enemyPlayers = new ArrayList<>();
        this.isInPrison = false;
    }
    //Constructor End

    //Getters Start
    public boolean getIsInPrison() { return this.isInPrison; }
    public String getName() { return this.name; }
    public int getHealth() {
        return this.health;
    }
    public boolean getDeath() { return this.death; }
    public List<Card> getPlayerCards() { return this.playerCards; }
    public List<Player> getEnemyPlayers() { return this.enemyPlayers; }
    public List<Card> getBlueCards() { return this.blueCards; }
    @Override
    public String toString() {
        return this.name;
    }
    //Getters End

    //GameInitialization Start
    public void addEnemies(List<Player> players) {
        for (Player player : players) {
            if (this.getName().equals(player.getName())) {
                continue;
            } else {
                this.getEnemyPlayers().add(player);
            }
        }
    }
    //GameInitialization End

    //Methods Start
    public void setIsInPrison(boolean status) { this.isInPrison = status; }
    public void checkDeath() {
        if (this.getHealth() <= 0) {
            this.death = true;
        }
    }
    public void decreaseHealth(int damage) {
        this.health -= damage;
    }
    public void addHealth(int healAmount) {
        this.health += healAmount;
    }
    public void drawCard(int numberOfCards,List<Card> cardDeck) {
        for(int a = 0; a < numberOfCards; a++) {
            if (cardDeck.isEmpty()) {
                System.out.println("There are no more cards in deck.");
                break;
            } else {
                this.getPlayerCards().add(cardDeck.get(0));
                cardDeck.remove(0);
            }
        }
    }
    public int checkCard(List<Card> cards,Class<?> className) {
        for (int a = 0; a < cards.size(); a++) {
            if (className.isInstance(cards.get(a))) {
                return a;
            }
        }
        return -1;
    }
    public void checkCardCount(List<Card> cardDeck) {
        while (this.getPlayerCards().size() > this.getHealth()) {
            System.out.println("You have more cards than health, throw some card away!");
            this.printCards();
            while(true) {
                int index = KeyboardInput.readInt("Enter index of Card");
                if (index < 0 || index >= this.getPlayerCards().size()) {
                    System.out.println("Index out of bounds. Please enter correct index!");
                } else {
                    Card removedCard = this.getPlayerCards().get(index);
                    cardDeck.add(removedCard);
                    this.getPlayerCards().remove(index);
                    break;
                }
            }
        }
    }
    public void removeCard(int index) {
        this.getPlayerCards().remove(index);
    }
    public void cardChoose(List<Card> cardDeck,List<Player> players) {
        if (this.getPlayerCards().size() == 0) {
            System.out.println("You dont have any cards in hand! End your turn!");
            return;
        }
        while(true) {
            int index = KeyboardInput.readInt("Enter card index");
            if (index < 0 || index >= this.getPlayerCards().size()) {
                System.out.println("Index out ouf bounds. Please enter correct index!");
            } else {
                Card card = this.getPlayerCards().get(index);
                card.cardAbility(this,cardDeck,players);
                break;
            }
        }
    }
    public void playerDied(List<Card> cardDeck, List<Player> players) {
        System.out.println(this.getName() + " was eliminated from game!");
        cardDeck.addAll(this.getPlayerCards());
        cardDeck.addAll(this.getBlueCards());
        this.getBlueCards().clear();
        this.getPlayerCards().clear();
        for(int a = 0; a < this.getEnemyPlayers().size(); a++) {
            this.getEnemyPlayers().get(a).getEnemyPlayers().remove(this);
        }
        this.getEnemyPlayers().clear();
        players.remove(this);
    }
    public boolean checkBlueCards(List<Card> cardDeck, List<Player> players, Turn turn) {
        int prisonIndex = this.getBlueCards().indexOf(new Prison("Prison","blue"));
        int dynamiteIndex = this.getBlueCards().indexOf(new Dynamite("Dynamite","blue"));
        if (prisonIndex != -1 && dynamiteIndex !=-1) {
            if (prisonIndex < dynamiteIndex) {
                Collections.swap(this.getBlueCards(),prisonIndex,dynamiteIndex);
            }
        }
        for (int a = 0; a < this.getBlueCards().size(); a++) {
            Card card = this.getBlueCards().get(a);
            card.blueCardAbility(this,cardDeck,players);
            if (this.getDeath()) {
                int newIndex = turn.getNextPlayer(players);
                this.playerDied(cardDeck,players);
                turn.setPlayerOnTurn(players.get(newIndex));
                return true;
            }
            if (this.getIsInPrison()) {
                int newIndex = turn.getNextPlayer(players);
                this.setIsInPrison(false);
                turn.setPlayerOnTurn(players.get(newIndex));
                return true;
            }
        }
        return false;
    }
    //Methods End

    //PrintMethods Start
    public void printEnemyPlayers() {
        for (int a = 0; a < this.getEnemyPlayers().size(); a++) {
            Player player = this.getEnemyPlayers().get(a);
            System.out.printf("(%d) - %s    Health: %d    Cards: %d    BlueCards: %d",a,
                    player.getName(), player.getHealth(),
                    player.getPlayerCards().size(),player.getBlueCards().size());
            if (player.getBlueCards().size() != 0) {
                System.out.print(" --- ");
                for(int b = 0; b < player.getBlueCards().size(); b++) {
                    System.out.printf("%s ",player.getBlueCards().get(b).getName());
                }
            }
            System.out.println();
        }
    }
    public void printPlayerStats() {
        System.out.printf("Player: %s   Health: %d \n",this.getName(),this.getHealth());
    }
    public void printCards() {
        for (int a = 0; a < this.getPlayerCards().size(); a++) {
            System.out.printf("(%d)-%s     ",a,this.getPlayerCards().get(a).getName());
        }
        System.out.println();
    }
    //PrintMethods End

}



