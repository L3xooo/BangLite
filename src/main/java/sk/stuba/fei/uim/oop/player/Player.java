package sk.stuba.fei.uim.oop.player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.blue.Barrel;
import sk.stuba.fei.uim.oop.cards.blue.Dynamite;
import sk.stuba.fei.uim.oop.cards.blue.Prison;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;


public class Player {
    private final String name;
    private int health;
    private boolean death;
    private List<Card> playerCards;
    private List<Card> blueCards;
    private List<Player> enemyPlayers;
    private List<Card> discardCardDeck;
    public Player(String name,List<Card> discardCardDeck){
        this.death = false;
        this.name = name;
        this.health = 4;
        this.playerCards = new ArrayList<>();
        this.blueCards = new ArrayList<>();
        this.enemyPlayers = new ArrayList<>();
        this.discardCardDeck = discardCardDeck;
    }
    public String getName() { return this.name; }
    public int getHealth() {
        return this.health;
    }
    public boolean getDeath() { return this.death; }
    public List<Card> getPlayerCards() { return this.playerCards; }
    public List<Player> getEnemyPlayers() { return this.enemyPlayers; }
    public List<Card> getBlueCards() { return this.blueCards; }
    public List<Card> getDiscardCardDeck() { return this.discardCardDeck; }
    @Override
    public String toString() {
        return this.name;
    }
    public void addEnemies(List<Player> players) {
        for (Player player : players) {
            if (!this.getName().equals(player.getName())) {
                this.getEnemyPlayers().add(player);
            }
        }
    }
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
        if (cardDeck.size() < numberOfCards) {
            cardDeck.addAll(this.getDiscardCardDeck());
            Collections.shuffle(cardDeck);
            this.getDiscardCardDeck().clear();
        }
        for (int a = 0; a < numberOfCards; a++) {
            this.getPlayerCards().add(cardDeck.get(0));
            cardDeck.remove(0);
        }
    }
    public int checkCard(List<Card> cards,Class className) {
        for (int a = 0; a < cards.size(); a++) {
            if (className.isInstance(cards.get(a))) {
                return a;
            }
        }
        return -1;
    }
    public void checkCardCount() {
        while (this.getPlayerCards().size() > this.getHealth()) {
            System.out.println("You have more cards than health, throw some card away!");
            this.printCards();
            while(true) {
                int index = KeyboardInput.readInt("Enter index of Card");
                if (index < 0 || index >= this.getPlayerCards().size()) {
                    System.out.println("Index out of bounds. Please enter correct index!");
                } else {
                    Card removedCard = this.getPlayerCards().get(index);
                    System.out.println("Player: " + this.getName() + " threw away " + removedCard.getName());
                    this.getDiscardCardDeck().add(removedCard);
                    this.getPlayerCards().remove(index);
                    break;
                }
            }
        }
    }

    public int cardChoose() {
        //this.printCards();
        int cardIndex;
        while (true) {
            cardIndex = KeyboardInput.readInt("Enter card index");
            if (cardIndex < 0 || cardIndex >=this.getPlayerCards().size()) {
                System.out.println("Index out of bounds. Please enter correct index!");
            } else {
                break;
            }
        }
        return cardIndex;
    }

    public int getPlayableCardsCount() {
        int count = 0;
        for (Card card : this.getPlayerCards()) {
            if(card.canPlay(this)) {
                count++;
            }
        }
        return count;
    }

    public void playCard() {
        while (true) {
            int cardIndex = cardChoose();
            Card card = this.getPlayerCards().get(cardIndex);
            if (card.canPlay(this)) {
                card.playCard(this);
                if (card.getCardType().equals("Brown")) {
                    this.getDiscardCardDeck().add(card);
                }
                this.getPlayerCards().remove(card);
                break;
            } else {
                System.out.println("You cannot play " + card.getName() + " card! Choose another card!");
            }
        }
    }

    public void playerDied() {
        System.out.println("Player: " + this.getName() + " was eliminated from game!");
        this.getDiscardCardDeck().addAll(this.getPlayerCards());
        this.getDiscardCardDeck().addAll(this.getBlueCards());
        this.getBlueCards().clear();
        this.getPlayerCards().clear();
        for(int a = 0; a < this.getEnemyPlayers().size(); a++) {
            this.getEnemyPlayers().get(a).getEnemyPlayers().remove(this);
        }
        this.getEnemyPlayers().clear();
    }
    public boolean checkBlueCards() {
        int prisonIndex = this.checkCard(this.getBlueCards(), Prison.class);
        int dynamiteIndex = this.checkCard(this.getBlueCards(), Dynamite.class);
        if (prisonIndex != -1 || dynamiteIndex != -1) {
            System.out.println("Checking Player: " + this.getName() + " blue cards!");
        } else {
            return false;
        }
        if (prisonIndex != -1 && dynamiteIndex != -1) {
            if (prisonIndex < dynamiteIndex) {
                Collections.swap(this.getBlueCards(), prisonIndex, dynamiteIndex);
            }
        }

        for (int a = 0; a < this.getBlueCards().size(); a++) {
            Card card = this.getBlueCards().get(a);
            if (card instanceof Barrel) {
                continue;
            }
            boolean result = card.blueCardAbility(this);
            if (!result) {
                if (this.getDeath()) {
                    this.playerDied();
                }
                return true;
            }
        }
        return false;
    }
    public void printEnemyPlayers() {
        for (int a = 0; a < this.getEnemyPlayers().size(); a++) {
            Player player = this.getEnemyPlayers().get(a);
            System.out.printf("(%d) - ",a);
            player.printPlayerStats();
        }
    }
    public void printPlayerStats() {
        System.out.printf("Player: %s    Health: %d    Cards: %d    BlueCards: %d",
        this.getName(),this.getHealth(),this.getPlayerCards().size(),this.getBlueCards().size());
        if (this.getBlueCards().size() != 0) {
            System.out.print(" --- ");
            for(int b = 0; b < this.getBlueCards().size(); b++) {
                System.out.printf("%s ",this.getBlueCards().get(b).getName());
            }
        }
        System.out.println();
    }
    public void printCards() {
        for (int a = 0; a < this.getPlayerCards().size(); a++) {
            System.out.printf("(%d)-%s     ",a,this.getPlayerCards().get(a).getName());
        }
        System.out.println();
    }
    public void printBlueCards() {
        for (int a = 0; a < this.getBlueCards().size(); a++) {
            System.out.printf("(%d)-%s     ",a,this.getBlueCards().get(a).getName());
        }
        System.out.println();
    }
}



